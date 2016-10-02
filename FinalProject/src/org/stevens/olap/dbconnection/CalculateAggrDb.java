package org.stevens.olap.dbconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.stevens.olap.datastructure.AggregateResultType;
import org.stevens.olap.datastructure.AttributeType;
import org.stevens.olap.datastructure.GrpAttrDS;
import org.stevens.olap.datastructure.MFStructure;

public class CalculateAggrDb extends DatabaseConnection {

	public void test(MFStructure mf) {

		List<AttributeType> gt = mf.getGrpAttr();

		for (AttributeType at : gt) {
			System.out.println(at.getColName() + " " + at.getDataType());
		}

		// mf.getAggrFn();

	}

	public void compareNCalculate(MFStructure mf) {
		Connection c = createConnection();
		Statement sm;
		try {
			sm = c.createStatement();

			Map<String, List<AggregateResultType>> resultMap = new HashMap<String, List<AggregateResultType>>();

			for (AttributeType predicates : mf.getPredicates()) {
				
				System.out.println("predicate-"+predicates.getColVal());
				
				// Aggregate function on Column name
				String aggColumn = mf.getAggrFn().get(0).getColName();
				System.out.println("Aggregate function on Column="+aggColumn);

				//ResultSet rs = sm.executeQuery("select * from sales where state='NY' and cust='Bloom' ");
				ResultSet rs = sm.executeQuery("select * from sales ");
				List<AggregateResultType> result = new ArrayList<AggregateResultType>();

				while (rs.next()) {

					//check if predicates matching result before populating quant values - ex state='NY' OR NJ OR CT
					if(!predicates.getColVal().equals(rs.getString(predicates.getColName()))){
						continue;
					}

					// this holds existing object ref
					AggregateResultType matched = null;

					for (AggregateResultType rst : result) {
						boolean flag = true;
						for (GrpAttrDS gds : rst.getGd()) {

							if (gds.getColVal().equals(rs.getString(gds.getColName())))
								flag = flag && true;
							else
								flag = false;
						}

						if (flag) {
							// if found in list, assign matched object
							matched = rst;
							break;
						}

					}

					if (matched != null) {
						// update existing object
						//System.out.println("Updating matched data");
						matched.incrementQuantCount();
						matched.setSumQuant(rs.getInt(aggColumn));

						if (rs.getInt(aggColumn) > matched.getMaxQuant()) {
							matched.setMaxQuant(rs.getInt(aggColumn));
						}
						if (rs.getInt(aggColumn) < matched.getMinQuant()) {
							matched.setMinQuant(rs.getInt(aggColumn));
						}

					} else {

						//System.out.println("Add new object");
						AggregateResultType newAggr = new AggregateResultType();
						for (AttributeType i : mf.getGrpAttr()) {
							String type = i.getDataType();
							if (type.equals("String")) {
								GrpAttrDS g = new GrpAttrDS(i.getColName(), rs.getString(i.getColName()));
								newAggr.getGd().add(g);
							}
						}
						newAggr.incrementQuantCount();
						newAggr.setSumQuant(rs.getInt(aggColumn));

						if (rs.getInt(aggColumn) > newAggr.getMaxQuant()) {
							newAggr.setMaxQuant(rs.getInt(aggColumn));
						}
						if (rs.getInt(aggColumn) < newAggr.getMinQuant()) {
							newAggr.setMinQuant(rs.getInt(aggColumn));
						}

						result.add(newAggr);
					}
				}

				resultMap.put(predicates.getColVal(), result);
				
				for (AggregateResultType ag : result) {

					System.out.print(ag.getMaxQuant() + "\t" + ag.getMinQuant() + "\t" + ag.getCountQuant() + "\t"
							+ ag.getSumQuant() + "\t");
					for (GrpAttrDS a : ag.getGd()) {
						System.out.print(a.getColName() + "=" + a.getColVal() + "\t");
					}
					System.out.println("");
				}

			}
			
			// apply having condition 
			
			String myString= mf.getHavingClause();
			//String[] result = myString.split("(?=[-+*/><||])");
			String[] result = myString.split("or");
			System.out.println(Arrays.toString(result));
			result = result[0].split("([-+*/><||])");
			System.out.println(Arrays.toString(result));
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

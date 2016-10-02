package jdbc.assignment.vo;

import java.sql.*;

public class StateMax {

	public static void main(String[] args) {
		String usr = "postgres";
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa";

		StateSales[] sarray = new StateSales[200];

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try {
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");
			int first = 0;
			while (rs.next()) {

				if (first == 0) {
					StateSales sb = new StateSales();
					sb.setCustomer(rs.getString("cust"));
					sb.setProduct(rs.getString("prod"));
					sb.setState(rs.getString("state"));
					sb.setMax_sales(rs.getInt("quant"));
					sb.setMin_sales(rs.getInt("quant"));
					sb.setDate(rs.getString("month") + "/"
							+ rs.getString("day") + "/" + rs.getString("year"));

					sarray[first++] = sb;
					continue;
				}
				boolean flag = false;
				for (int i = 0; i < sarray.length; i++) {

					StateSales s = sarray[i];
					if (s == null)
						break;
					if (s.getCustomer().equals(rs.getString("cust"))
							&& s.getProduct().equals(rs.getString("prod"))
							&& s.getState().equals(rs.getString("state"))) {

						if (s.getMin_sales() > rs.getInt("quant")) {
							s.setMin_sales(rs.getInt("quant"));
						}
						if (rs.getInt("quant") > s.getMax_sales()) {
							s.setMax_sales(rs.getInt("quant"));
						}

						flag = true;
					}

				}

				if (!flag) {

					StateSales sb = new StateSales();

					sb.setCustomer(rs.getString("cust"));
					sb.setProduct(rs.getString("prod"));
					sb.setState(rs.getString("state"));
					sb.setMax_sales(rs.getInt("quant"));
					sb.setMin_sales(rs.getInt("quant"));
					sb.setDate(rs.getString("month") + "/"
							+ rs.getString("day") + "/" + rs.getString("year"));

					sarray[first++] = sb;

				}

			}

			//System.out.println("Customer \t\t product \t\t state \t\t max-sales \t\tmin_sales \t\t date");
			System.out.format("%1s%12s%12s%12s%12s%13s%12s%13s%14s",
					"CUSTOMER", "PRODUCT", "ST", "MAX_SALES","MIN_SALES","DATE",
					"DATE", "ST", "AVG\n");
			System.out.format("%1s%12s%12s%14s%11s%13s%13s%11s%15s",
					"========", "=======", "======", "=========", "====",
					"======", "=========", "====", "======\n");
			for (int i = 0; i < sarray.length; i++) {

				StateSales s = sarray[i];
				if (s == null)
					break;
				if (s.getState().equals("NY") || s.getState().equals("NJ")
						|| s.getState().equals("CT")) {
					System.out.println(s.getCustomer() + " \t\t\t"
							+ s.getProduct() + "\t\t\t  " + s.getState()
							+ "\t\t  " + s.getMax_sales() + "\t\t\t"
							+ s.getMin_sales() + "\t\t\t" + s.getDate());
				}
			}

		}

		catch (SQLException e) {
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}
}

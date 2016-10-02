package edu.stevens.generated;


public class MFStructure {

	String			prod;
	Integer			quant;

	int			max_quant_1;


	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + (this.prod == null ? 0 : this.prod.hashCode());
		hash = hash * 31 + (this.quant == null ? 0 : this.quant.hashCode());
		return hash;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MFStructure) {
			MFStructure mf = (MFStructure) obj;
			boolean flag = true;
			flag = (flag && mf.prod.equals(this.prod)? true : false);
			flag = (flag && mf.quant.equals(this.quant)? true : false);
			return flag;
		}
		return false;
	}


}

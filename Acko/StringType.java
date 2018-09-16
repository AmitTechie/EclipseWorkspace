package Acko;

public class StringType extends DataType  {
	
	String val;

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return this.val;
	}

	@Override
	public String toString() {
		return "StringType [val=" + val + "]";
	}
	
}

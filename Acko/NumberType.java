package Acko;

public class NumberType extends DataType  {

	int val;

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return this.val;
	}

	@Override
	public String toString() {
		return "NumberType [val=" + val + "]";
	}
	
}

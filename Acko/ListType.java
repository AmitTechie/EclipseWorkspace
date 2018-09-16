package Acko;

import java.util.ArrayList;
import java.util.List;

public class ListType extends DataType {
	
	List<DataType> list;

	
	
	public ListType() {
		super();
		this.list = new ArrayList<DataType>();
	}

	public List<DataType> getList() {
		return list;
	}

	public void setList(List<DataType> list) {
		this.list = list;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public String toString() {
		return "ListType [list=" + list + "]";
	}
}

package Acko;

import java.util.HashMap;
import java.util.Map;

public class DictionaryType extends DataType {
	
	Map<DataType,DataType> dictionary;
	
	public DictionaryType() {
		super();
		dictionary = new HashMap<>();
	}

	public void putValue(DataType key, DataType value) {
		dictionary.put(key, value);
	}


	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "DictionaryType [dictionary=" + dictionary + "]";
	}

}

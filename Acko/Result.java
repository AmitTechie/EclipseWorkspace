package Acko;
import java.util.*;

public class Result {
	
}

/*enum TYPES{
	LIST_TYPE,
	DICTIONARY_TYPE,
	STRING_TYPE,
	NUMBER_TYPE,
	NONE
}

class Arguments{
	private TYPES types = TYPES.NONE;
	private String parsedString = "";
	private String unparsedString = "''";
	private int openListCount = 0;
	private int closeListCount = 0;
	private int openDicCount = 0;
	private int closeDicCount = 0;
	
	public TYPES getTypes() {
		return types;
	}

	public void setTypes(TYPES types) {
		this.types = types;
	}

	public String getParsedString() {
		return parsedString;
	}

	public void setParsedString(String parsedString) {
		this.parsedString = parsedString;
	}

	public void appendStringInParsedString(String string) {
		this.parsedString += string;
	}
	
	public String getUnparsedString() {
		return unparsedString;
	}

	public void setUnparsedString(String unparsedString) {
		this.unparsedString = "'"+unparsedString+"'";
	}

	public int getOpenListCount() {
		return openListCount;
	}

	public void setOpenListCount(int openListCount) {
		this.openListCount = openListCount;
	}

	public int getCloseListCount() {
		return closeListCount;
	}

	public void setCloseListCount(int closeListCount) {
		this.closeListCount = closeListCount;
	}

	public int getOpenDicCount() {
		return openDicCount;
	}

	public void setOpenDicCount(int openDicCount) {
		this.openDicCount = openDicCount;
	}

	public int getCloseDicCount() {
		return closeDicCount;
	}

	public void setCloseDicCount(int closeDicCount) {
		this.closeDicCount = closeDicCount;
	}
}

public class JsonParser {

	public String[] parseJSONString(String input) {
		Arguments arguments = new Arguments();
		parseJSON(input.substring(1, input.length()-1), 0, arguments);
		String[] result = {arguments.getParsedString(), arguments.getUnparsedString()};
		return result;
	}

	public void parseJSON(String input, int i, Arguments arguments) {
		
		if(i >= input.length()) {
			return;
		}

		if(input.charAt(i) == '\"') { //string case..
			
			String s = "\"";
			while(++i < input.length() && input.charAt(i) != '\"') s +=input.charAt(i);
			arguments.appendStringInParsedString(s+input.charAt(i++));
			if(arguments.getTypes() == TYPES.NONE) {
				arguments.setTypes(TYPES.STRING_TYPE);
				arguments.setUnparsedString(input.substring(i));
			}else
				parseJSON(input, i, arguments);
			
		}else if(input.charAt(i) >= '0' && input.charAt(i) <= '9') { //number case..
			
			String s = "";
			while(i < input.length() && input.charAt(i) >= '0' && input.charAt(i) <= '9') s +=input.charAt(i++);
			arguments.appendStringInParsedString(s);
			if(arguments.getTypes() == TYPES.NONE) {
				arguments.setTypes(TYPES.NUMBER_TYPE);
				arguments.setUnparsedString(input.substring(i));
			}else 
				parseJSON(input, i, arguments);
			
		}else if(input.charAt(i) == '[') { //List open case..
			
			if(arguments.getTypes() == TYPES.NONE) 
				arguments.setTypes(TYPES.LIST_TYPE);
			if(arguments.getOpenListCount() > 0 && arguments.getOpenListCount() == arguments.getCloseListCount()) {
				arguments.setUnparsedString(input.substring(i));
				return;
			}
			arguments.setOpenListCount(arguments.getOpenListCount()+1);
			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
			
		}else if(input.charAt(i) == ']') { //List close case..
			
			if(arguments.getOpenListCount() > 0) 
				arguments.setCloseListCount(arguments.getCloseListCount()+1);
			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
			
		}else if(input.charAt(i) == '{') { //Dictionary open case..
			
			if(arguments.getTypes() == TYPES.NONE) 
				arguments.setTypes(TYPES.DICTIONARY_TYPE);
			if(arguments.getOpenDicCount()> 0 && arguments.getOpenDicCount() == arguments.getCloseDicCount()) {
				arguments.setUnparsedString(input.substring(i));
				return;
			}
			arguments.setOpenDicCount(arguments.getOpenDicCount()+1);
			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
			
		}else if(input.charAt(i) == '}') { //Dictionary close case..
		
			if(arguments.getOpenDicCount() > 0) 
				arguments.setCloseDicCount(arguments.getCloseDicCount()+1);
			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
		
		}else if(input.charAt(i) == ',' || input.charAt(i) == ':') { //New element delimeter..

			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
			
		}else {
			
			arguments.setUnparsedString(input.substring(i));
		}
	}
}
*/


package Acko;

import java.util.*;


enum TYPES{
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

		System.out.println("Original JSON to parse: "+ input);
		//invalid data..
		Arguments arguments = new Arguments();
		parseJSON(input.substring(1, input.length()-1), 0, arguments);
		String[] result = new String[2];
		result[0] = arguments.getParsedString();
		result[1] = arguments.getUnparsedString();
		return result;
	}

	public void parseJSON(String input, int i, Arguments arguments) {
		
		if(i >= input.length()) {
			return;
		}

		
		switch(input.charAt(i)) {

		case '\"':{
			//case string..
			String s = "\"";
			while(++i < input.length() && input.charAt(i) != '\"') s +=input.charAt(i);
			arguments.appendStringInParsedString(s+input.charAt(i++));
			if(arguments.getTypes() == TYPES.NONE) {
				arguments.setTypes(TYPES.STRING_TYPE);
				arguments.setUnparsedString(input.substring(i));
			}else
				parseJSON(input, i, arguments);
		}
		break;

		case ',': //fallback..
		case ':':{
			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
		}
		break;

		case '[':{ //List..
			if(arguments.getTypes() == TYPES.NONE) 
				arguments.setTypes(TYPES.LIST_TYPE);
			if(arguments.getOpenListCount() > 0 && arguments.getOpenListCount() == arguments.getCloseListCount()) {
				arguments.setUnparsedString(input.substring(i));
				return;
			}
			arguments.setOpenListCount(arguments.getOpenListCount()+1);
			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
		}
		break;

		case ']':{
			//case list..
			if(arguments.getOpenListCount() > 0) 
				arguments.setCloseListCount(arguments.getCloseListCount()+1);
			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
		}
		break;

		
		case '{':{ //Open Dictionary..
			if(arguments.getTypes() == TYPES.NONE) 
				arguments.setTypes(TYPES.DICTIONARY_TYPE);
			if(arguments.getOpenDicCount()> 0 && arguments.getOpenDicCount() == arguments.getCloseDicCount()) {
				arguments.setUnparsedString(input.substring(i));
				return;
			}
			arguments.setOpenDicCount(arguments.getOpenDicCount()+1);
			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
		}
		break;

		case '}':{ //Close Dictionary..
			if(arguments.getOpenDicCount() > 0) 
				arguments.setCloseDicCount(arguments.getCloseDicCount()+1);
			arguments.appendStringInParsedString(""+input.charAt(i));
			parseJSON(input, i+1, arguments);
		}
		break;

		
		case '0': //case number fallback..
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':{
			String s = "";
			while(i < input.length() && input.charAt(i) >= '0' && input.charAt(i) <= '9') s +=input.charAt(i++);
			arguments.appendStringInParsedString(s);
			if(arguments.getTypes() == TYPES.NONE) {
				arguments.setTypes(TYPES.NUMBER_TYPE);
				arguments.setUnparsedString(input.substring(i));
			}else 
				parseJSON(input, i, arguments);
		}
		break;

		default:{
			arguments.setUnparsedString(input.substring(i));
		}
		break;
		}
	}
/*
	private Result ParseDictionaryType(String input, int i) {
		Result result = new Result();

		if(input.length() < 2 || input.charAt(input.length()-1) != '}') {
			if(!input.contains("}")) {
				//invalid List..
				result.setDataType(null);
				result.setString(input);
				return result;
			}

			int i = input.lastiOf("}");
			if(i != -1) {
				result.setString(input.substring(input.iOf("}")));
				input = input.substring(0, i);
				System.out.println(input);
				//System.out.println(input.substring(input.iOf("}")));
			}

		}

		DictionaryType dictionaryType = new DictionaryType();
		if(input.length() == 2) {
			System.out.println("EMpty Dic: "+ input);
			result.setDataType(dictionaryType);
			result.setString(null);
			return result;
		}

		System.out.println("List to slipt: "+input.substring(1, input.length()-1));
		String[] tokens = input.substring(1, input.length()-1).split(",");

		System.out.println("tokens length: "+ tokens.length);

		Stack<String> stk = new Stack<>();
		int openCount = 0;

		for(String token: tokens) {
			System.out.println("Dic token: "+token);
			System.out.println("isValidParenthesisString: "+isValidParenthesisString(token));
			String[] keyVal;

			if (token.contains(":") && (token.contains("[") || token.contains("{")) && isValidParenthesisString(token)) {
				//don't do anything to token
				keyVal = new String[2];
				keyVal[0] = token.substring(0, token.iOf(":")+1);
				keyVal[1] = token.substring(token.iOf(":")+1);
				System.out.println("Dic token: keyVal[0]: "+keyVal[0]);
				System.out.println("Dic token: keyVal[1]: "+keyVal[1]);

			}else {
				if(token.contains("[") || token.contains("{")) {
					if(!stk.isEmpty()) {
						String str = stk.pop();
						str += token;
						stk.push(str);
					}else {
						stk.push(token);	
					}
					openCount++;
					continue;
				}

				if(token.contains("]") || token.contains("}")) {
					if(stk.isEmpty()) {
						result.setDataType(null);
						result.setString(input);
						return result;
					}

					String str = stk.pop();
					if((token.contains("]") && !str.contains("[")) || (token.contains("}") && !str.contains("}") )) {
						result.setDataType(null);
						result.setString(input);
						return result;
					}
					str += ","+token;
					stk.push(str);
					openCount--;
				}

				if(openCount == 0) {
					if(!stk.empty()) {
						token = stk.pop();
					}

				}

				if(openCount > 0) {
					String str = stk.pop()+","+token;
					stk.push(str);
					continue;
				}

				System.out.println("Dictionary token to split: "+token);

				keyVal = token.split(":");
				System.out.println("Dictionary token key: "+keyVal[0]);

				if(keyVal.length != 2) {
					result.setDataType(null);
					result.setString(input);
					return result;
				}
				System.out.println("Dictionary token value: "+keyVal[1]);


			}




			Result res = parseJSON(keyVal[0]);
			if( res.getDataType().getClass() != StringType.class && res.getDataType().getClass() != NumberType.class) {
				System.out.println("Dictionary :"+res.getDataType().getClass());
				result.setDataType(null);
				result.setString(input);
			}else {
				Result resValue = parseJSON(keyVal[1]);
				if(resValue.dataType != null) {
					System.out.println("Dictionary value is not null");
					dictionaryType.putValue(res.getDataType(), resValue.getDataType());
					result.setDataType(dictionaryType);
				}else {
					System.out.println("Dictionary value is null for "+keyVal[1]);
					result.setDataType(null);
					result.setString(input);
					return result;
				}
			}
		}

		return result;
	}

	private Result ParseNumberType(String input, int i) {
		Result result = new Result();
		while(i < input.length() && input.charAt(i) >= '0' && input.charAt(i) <= '9') {
			i++;
		}

		if(i == input.length()) {
			//done integer parsing..
			NumberType numberType = new NumberType();
			numberType.setVal(Integer.parseInt(input.substring(0, input.length())));
			//System.out.println("Pure NumberType: "+numberType.getVal());
			result.setDataType(numberType);

		}else {
			NumberType numberType = new NumberType();
			numberType.setVal(Integer.parseInt(input.substring(0, i)));
			//System.out.println("Pure NumberType: "+numberType.getVal());
			StringType stringType = new StringType();
			stringType.setVal(input.substring(i, input.length()));
			//System.out.println("Mixed NumberType: "+numberType.getVal() + " => Unparsed String:"+stringType.getVal());
			result.setDataType(numberType);
			result.setString(stringType.getVal());
		}

		return result;

	}

	private Result ParseListType(String input, int i) {
		Result result = new Result();

		if(input.length() < 2 || input.charAt(input.length()-1) != ']') {
			//invalid List..
			if(!input.contains("]")) {
				//invalid List..
				result.setDataType(null);
				result.setString(input);
				return result;
			}
			input = input.substring(0, input.lastiOf("]"+1));
			result.setString(input.substring(input.lastiOf("")+1));
		}


		ListType listType = new ListType();

		if(input.length() == 2) {
			//System.out.println("EMpty List: "+ input);
			StringType  stringType = new StringType();
			stringType.setVal(input);
			listType.getList().add(stringType);
			result.setDataType(listType);
			result.setString(null);
			return result;
		}

		//System.out.println("List to slipt: "+input.substring(1, input.length()-1));
		String[] tokens = input.substring(1, input.length()-1).split(",");

		//		for(String token: tokens) {
		//			System.out.println("token: "+token);
		//		}
		//		System.out.println("tokens length: "+ tokens.length);

		if(tokens.length == 1) {
			result.setDataType(null);
			result.setString(input.substring(0, input.length()));
			return result;
		}else {
			//invalid case..
			String s = tokens[tokens.length - 1];
			if(s.equals("")) {
				//invalid condition..

			}else {
				Stack<String> stk = new Stack<>();
				int openCount = 0;

				for(String token: tokens) {
					//					System.out.println("token: "+token);
					if(token.startsWith("[") || token.startsWith("{")) {
						if(!stk.isEmpty()) {
							String str = stk.pop();
							str += token;
							stk.push(str);
						}else {
							stk.push(token);	
						}
						openCount++;
						continue;
					}

					if(token.endsWith("]") || token.endsWith("}")) {
						if(stk.isEmpty()) {
							result.setDataType(null);
							result.setString(input);
							return result;
						}

						String str = stk.pop();
						if((token.endsWith("]") && !str.startsWith("[")) || (token.endsWith("}") && !str.startsWith("}") )) {
							result.setDataType(null);
							result.setString(input);
							return result;
						}
						str += ","+token;
						stk.push(str);
						openCount--;
					}

					if(openCount == 0) {
						if(!stk.empty()) {
							token = stk.pop();
						}
					}

					Result res = parseJSON(token);
					if(res.dataType != null) {
						listType.getList().add(res.getDataType());
					}else {
						result.setDataType(null);
						result.setString(input);
						return result;
					}
				}

				result.setDataType(listType);
				//result.setString(null);
			}

		}

		return result;

	}

	private Result ParseStringType(String input, int i) {
		Result result = new Result();

		while(i < input.length() && input.charAt(i) != '\"') {
			i++;
		}

		if(i == input.length()-1) {
			//done integer parsing..
			StringType stringType = new StringType();
			stringType.setVal(input);
			//			System.out.println("Pure StringType: "+stringType.getVal());
			result.setDataType(stringType);

		}else {
			StringType stringType = new StringType();
			stringType.setVal(input.substring(0, i+1));
			//			System.out.println("Pure StringType: "+stringType.getVal());
			StringType stringType2 = new StringType();
			stringType2.setVal(input.substring(i+1, input.length()));
			//			System.out.println("Mixed StringType: "+stringType2.getVal() + " => Unparsed String:"+stringType.getVal());
			result.setDataType(stringType);
			result.setString(stringType2.getVal());
		}

		return result;
	}

	public boolean isValidParenthesisString(String token) {
		Stack<Character> stack = new Stack<>();

		for(int i=0; i<token.length(); i++) {

			if(token.charAt(i) == '[' || token.charAt(i) == '{') {
				stack.push(token.charAt(i));

				continue;
			}
			if(token.charAt(i) == '}' || token.charAt(i) == ']') {

				if(token.charAt(i) == ']' && (stack.empty() || stack.peek() != '[')) {
					return false;
				}
				if(token.charAt(i) == '}' && (stack.empty() || stack.peek() != '{')) {
					return false;
				}
				stack.pop();
			}
		}


		return stack.empty();
	}

*/	/*	public boolean isValidNestedListOrDict(String s) {

		String[] tokens = s.split(",");
		for(String t : tokens) {

		}

	}
	 */	
}

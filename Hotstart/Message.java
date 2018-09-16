package Hotstart;

import java.text.SimpleDateFormat;
import java.util.Date;

class Message {
	private String content;
	private String namespace;
	private LOG_LEVEL log_level;
	private String timestamp;
	
	Message(){
		
	}

	Message(LOG_LEVEL log_level, String content, String namespace){
		this.log_level = log_level;
		this.content = content;
		this.namespace = namespace;
	}

	String getContent() {
		return content;
	}

	void setContent(String content) {
		this.content = content;
	}

	String getNamespace() {
		return namespace;
	}

	void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	LOG_LEVEL getLogLevel() {
		return log_level;
	}

	void setLogLevel(LOG_LEVEL logLevel) {
		this.log_level = logLevel;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		
		if (namespace != null) {
			return "[ "+timestamp +"] ["+ log_level.toString() + "] ["+ namespace + "] " + content+"\n";
		}
		return "[ "+timestamp +"] ["+ log_level.toString() + "] " + content +"\n";
	}
}

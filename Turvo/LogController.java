package Turvo;

import java.util.Map;

public class LogController {
	
	private Sink sink;
	private Map<String, String> config;
	
	public LogController() {
		
	}
	
	public LogController(Sink sink) {
		this.sink = sink;
	}
	
	public LogController(Map<String, String> config){
		this.config = config;
	}
	
	public void setSinkConfig(Sink sink, Map<String, String> config) {
		if(sink != null) {
			this.sink = sink;
		}
		
		if(config != null) {
			this.config = config;
		}
	}

	public void setSink(Sink sink) {
		if(sink != null) {
			this.sink = sink;
		}
	}

	public void setConfig(Map<String, String> config) {
		if(config != null) {
			this.config = config;
		}
	}
	
	public void writeLog(LOG_LEVEL log_level, String msg, Object object) {
		Message message = new Message(log_level, msg, object.getClass().getName());
		
		sink.writeLog(message.toString());
	}
}

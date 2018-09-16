package Hotstart;

import java.util.Map;

public class LogController {
	
	private Sink sink;
	private static LogController logControllerInstance = new LogController(); //early initialization
	
	private LogController() {
		
	}
	
	public static LogController getInstance() {
		return logControllerInstance;
	}
	
	public void setLogConfig(Map<CONFIG_KEY, String> config){
		if(config == null) {
			//get default sink..
			this.sink = SinkFactory.getSink(SINK_TYPE.CONSOLE, null);
			return;
		}
		this.sink = SinkFactory.getSink(SINK_TYPE.valueOf(config.get(CONFIG_KEY.sinkType)), config);
	}

	public void setSink(Sink sink) {
		if(sink != null) {
			this.sink = sink;
		}
	}

	public void writeLog(LOG_LEVEL log_level, String msg, String namespace) {
		Message message = new Message(log_level, msg, namespace);
		
		sink.writeLog(message);
	}

	public void writeLog(LOG_LEVEL log_level, String msg) {
		
		if(this.sink == null) {
			sink = SinkFactory.getSink(SINK_TYPE.CONSOLE, null);
		}
		
		
		Message message = new Message(log_level, msg, null);
		
		sink.writeLog(message);
	}

}

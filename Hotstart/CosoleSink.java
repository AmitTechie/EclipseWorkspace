package Hotstart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

public class CosoleSink extends Sink{

	@Override
	public boolean configSink(Map<CONFIG_KEY, String> config) {
		sink_type = SINK_TYPE.CONSOLE;
		logLevelSet = new HashSet<>();
		
		if(config == null) {
			//default config..
			logLevelSet.add(LOG_LEVEL.DEBUG);
			logLevelSet.add(LOG_LEVEL.INFO);
			logLevelSet.add(LOG_LEVEL.WARNING);
			logLevelSet.add(LOG_LEVEL.ERROR);
			logLevelSet.add(LOG_LEVEL.FATAL);
		}else {
			//get the log levels from config map and put into the set, key = "LogLevel" => "DEBUG,INFO,WARNING,ERROR,FATAL"
		}
		
		
		return true;
	}
	
	@Override
	public void writeLog(Message message) {
	
		if(message == null || !logLevelSet.contains(message.getLogLevel())){
			return;
		}
		
		message.setTimestamp(new SimpleDateFormat(date_format.toString()).format(new Date()));
		
		
		System.out.println(message.toString());
	}
}

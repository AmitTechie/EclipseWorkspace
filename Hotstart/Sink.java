package Hotstart;

import java.util.Map;
import java.util.Set;

public abstract class Sink {
	
	protected SINK_TYPE sink_type;
	protected Set<LOG_LEVEL> logLevelSet;
	protected DATE_FORMAT date_format = DATE_FORMAT.yyMMddHHmmss;

	public abstract boolean configSink(Map<CONFIG_KEY, String> config);
	public abstract void writeLog(Message message);
}

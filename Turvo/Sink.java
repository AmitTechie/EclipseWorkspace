package Turvo;

public abstract class Sink {
	
	protected SINK_TYPE sink_type;
	protected LOG_LEVEL log_level;
	protected DATE_FORMAT date_format;

	public abstract void configSink();
	public abstract void writeLog(String message);
}

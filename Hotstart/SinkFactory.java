package Hotstart;

import java.util.Map;

public class SinkFactory {
	
	public static Sink getSink(SINK_TYPE sink_TYPE, Map<CONFIG_KEY, String> config) {
		
		Sink sink = null;
		switch(sink_TYPE) {
		
		case DB:
			break;
		case FILE:
			sink = new FileSink();
			if(sink.configSink(config)) {
				return sink;
			}
			break;
		case CONSOLE:
		default:
			sink = new CosoleSink();
			sink.configSink(config);
			return sink;
		}
		
		return sink;
	}
}

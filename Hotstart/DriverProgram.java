package Hotstart;

import java.util.HashMap;
import java.util.Map;

public class DriverProgram {

	public static void main(String[] args) {

		LogController.getInstance().writeLog(LOG_LEVEL.INFO, "Hotstar info logs --");
		LogController.getInstance().writeLog(LOG_LEVEL.INFO, "Hotstar info logs --", "Hoststar-NameSpace");

		Map<CONFIG_KEY, String> logConfig = new HashMap<>();
		logConfig.put(CONFIG_KEY.sinkType, SINK_TYPE.FILE.toString());
		logConfig.put(CONFIG_KEY.fileName, "/home/preeti/eclipse-workspace/Practice/src/Hotstart/logFileHotStar.log");
		logConfig.put(CONFIG_KEY.logLevel, "INFO,WARNING,DEBUG");
		LogController.getInstance().setLogConfig(logConfig);

		LogController.getInstance().writeLog(LOG_LEVEL.INFO, "Hotstar info logs -- file", "HOTSTART");
		LogController.getInstance().writeLog(LOG_LEVEL.WARNING, "Hotstar warning logs -- file");
		LogController.getInstance().writeLog(LOG_LEVEL.DEBUG, "Hotstar debug logs -- file");
		LogController.getInstance().writeLog(LOG_LEVEL.DEBUG, "Hotstar FATAL logs -- file");
	
	}
}

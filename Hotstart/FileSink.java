package Hotstart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

public class FileSink extends Sink {

	private String file;

	@Override
	public boolean configSink(Map<CONFIG_KEY, String> config) {

		if(config == null) {
			return false;
		}

		String logFIle = config.get(CONFIG_KEY.fileName);
		if(logFIle == null || Paths.get(logFIle) == null) {
			return false;
		}
		
		this.file = logFIle;

		sink_type = SINK_TYPE.FILE;
		logLevelSet = new HashSet<>();

		String logLevel = config.get(CONFIG_KEY.logLevel);
		if(logLevel == null) {
			//set all logs levels by default..
			logLevelSet.add(LOG_LEVEL.DEBUG);
			logLevelSet.add(LOG_LEVEL.INFO);
			logLevelSet.add(LOG_LEVEL.WARNING);
			logLevelSet.add(LOG_LEVEL.ERROR);
			logLevelSet.add(LOG_LEVEL.FATAL);
		}else {
			for(String loglvl: logLevel.split(",")) {
				try {
					logLevelSet.add(LOG_LEVEL.valueOf(loglvl));
				}catch (Exception e){
					System.out.println(loglvl + " : " + e.getMessage());
				}
			}
		}

		return true;
	}

	private void writeLogFile(String log) {
		System.out.println("Writing file data: "+ log);
		try {
        	Files.write(Paths.get(this.file), log.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void writeLog(Message message) {

		if(message == null || !logLevelSet.contains(message.getLogLevel())){
			return;
		}

		message.setTimestamp(new SimpleDateFormat(date_format.toString()).format(new Date()));

		writeLogFile(message.toString());

	}

}

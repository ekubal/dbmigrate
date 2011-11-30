package dbmigrate.logging;

import org.apache.log4j.Priority;

public class LoggerImpl implements ILogger {

	public void log(String message, Level level) {
		Priority prio = org.apache.log4j.Level.INFO;
		switch (level) {
		case Error:
			prio = org.apache.log4j.Level.ERROR;
			break;
		case Info:
			prio = org.apache.log4j.Level.INFO;
			break;
		case Warning:
			prio = org.apache.log4j.Level.WARN;
			break;
		}
		
		org.apache.log4j.Logger.getLogger("dbmigrate").log(prio, message);
	}

}

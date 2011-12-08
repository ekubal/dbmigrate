package dbmigrate.logging;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Priority;

public class LoggerImpl implements ILogger {
	private static List<IListener> listeners = new ArrayList<IListener>();

	public static void register(IListener listener) {
		LoggerImpl.listeners.add(listener);
	}

	
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

		for(IListener l : LoggerImpl.listeners) {
			l.log(message, level);
		}
	}

}

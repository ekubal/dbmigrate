package dbmigrate.logging;

public class LoggerFactory {

	private static Logger logger = new LoggerImpl(); 
	
	public static Logger getLogger() {
		return logger;
	}
	
}

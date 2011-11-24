package dbmigrate.logging;

public class LoggerFactory {

	private static ILogger logger = new LoggerImpl(); 
	
	public static ILogger getLogger() {
		return logger;
	}
	
}

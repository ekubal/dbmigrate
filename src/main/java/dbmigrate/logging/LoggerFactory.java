package dbmigrate.logging;

import org.apache.log4j.PropertyConfigurator;

public class LoggerFactory {

	private static ILogger logger = new LoggerImpl(); 
	
	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public static ILogger getLogger() {
		return LoggerFactory.logger;
	}
	
}

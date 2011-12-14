package dbmigrate.exceptions;

public class ConnectException extends DbmigrateException{
	public ConnectException() {
		super();
	}
	
	public ConnectException(String message) {
		super(message);
	}
}

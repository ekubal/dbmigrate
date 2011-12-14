package dbmigrate.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;
import dbmigrate.exceptions.ConnectException;

public class DbConnector {

	public final static String DB_TYPE = "postgresql";
	
	private String dbType;
	private String dbHost;
	private String dbName;
	private String dbUser;
	private String dbPass;
	private boolean hasParams = false;
	
	private Connection connection;
	
	public void setConnectionParams(String dbType, String host, String dbName, String user, String password) {
		this.dbType = dbType;
		this.dbHost = host;
		this.dbName = dbName;
		this.dbUser = user;
		this.dbPass = password;
		this.hasParams = true;
		this.connection = null;
	}
	
	public boolean hasParams() {
		return this.hasParams;
	}
	
	public Connection getConnection() throws ConnectException {
		if(!this.hasParams) {
			return null;
		}
		return this.getConnection(this.dbType, this.dbHost, this.dbName, this.dbUser, this.dbPass);
	}
	
	public Connection getConnection(String databaseType, String host,String dbName, String user, String password) throws ConnectException {
		if(null != this.connection) {
			return this.connection;
		}
		Driver driver = new Driver();
		try {
			DriverManager.registerDriver(driver);
		} catch (SQLException e) {		
			throw new ConnectException("Cannot connect to \""+host+"\": "+e.getMessage());
		}
		Connection conn = null;
		if(databaseType.equals(DbConnector.DB_TYPE)){
			try {
				conn = DriverManager.getConnection("jdbc:postgresql://"+host+"/"+dbName,
					user, password);
			} catch (SQLException se) {
				throw new ConnectException("Cannot connect to \""+host+"\": "+se.getMessage());
			}
		}
		return this.connection = conn;
	}
}

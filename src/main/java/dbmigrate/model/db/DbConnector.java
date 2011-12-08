package dbmigrate.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class DbConnector {

	public final static String DB_TYPE = "postgresql";
	
	private String dbType;
	private String dbHost;
	private String dbName;
	private String dbUser;
	private String dbPass;
	private boolean hasParams = false;
	
	public void setConnectionParams(String dbType, String host, String dbName, String user, String password) {
		this.dbType = dbType;
		this.dbHost = host;
		this.dbName = dbName;
		this.dbUser = user;
		this.dbPass = password;
		this.hasParams = true;
	}
	
	public boolean hasParams() {
		return this.hasParams;
	}
	
	public Connection getConnection() {
		return this.getConnection(this.dbType, this.dbHost, this.dbName, this.dbUser, this.dbPass);
	}
	
	public Connection getConnection(String databaseType, String host,String dbName, String user, String password){
		Driver driver = new Driver();
		try {
			DriverManager.registerDriver(driver);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		Connection connection = null;
		if(databaseType.equals(DbConnector.DB_TYPE)){
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://"+host+"/"+dbName,
					user, password);
			} catch (SQLException se) {
				System.out.println("Couldn't connect.");
				se.printStackTrace();
			}
		}
		return connection;
	}
}

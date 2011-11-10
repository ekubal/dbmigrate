package dbmigrate.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class DbConnector {
	public Connection getConnection(String databaseType, String host,String dbName, String user, String password){
		Driver driver = new Driver();
		try {
			DriverManager.registerDriver(driver);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		Connection connection = null;
		if(databaseType=="postgresql"){
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

package dbmigrate.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

import org.postgresql.Driver;
import dbmigrate.exceptions.ConnectException;

public class DbConnector {

	public final static String DB_TYPE = "postgresql";

	private int dbType;
	private String dbHost;
	private String dbName;
	private String dbUser;
	private String dbPass;
	private String dbSid;
	private boolean hasParams = false;

	public static final int POSTGRESQL_DB = 1;
	public static final int ORACLE_DB = 2;

	private static DbConnector connectorInstance = new DbConnector();
	private DbConnector() {
		
	}
	
	public static DbConnector instance() {
		return connectorInstance;
	}
	private Connection connection;

	public void setConnectionParams(int dbType, String host, String dbName,
			String user, String password, String dbSid) {
		setConnectionParams(dbType, host, dbName, user, password);
		this.dbSid = dbSid;
	}

	public void setConnectionParams(int dbType, String host, String dbName,
			String user, String password) {
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
		if (!this.hasParams) {
			return null;
		}
		return this.getConnection(this.dbType, this.dbHost, this.dbName,
				this.dbUser, this.dbPass);
	}

	public Connection getConnection(int databaseType, String host,
			String dbName, String user, String password)
			throws ConnectException {
		if (null != this.connection) {
			return this.connection;
		}
		Connection conn = null;
		try {

			if (databaseType == POSTGRESQL_DB) {
				Driver driver = new Driver();
				DriverManager.registerDriver(driver);
				conn = DriverManager.getConnection("jdbc:postgresql://" + host
						+ "/" + dbName, user, password);
			} else {
				// String driverName = "oracle.jdbc.driver.OracleDriver";
				// Class.forName(driverName);
				System.out.println("Oracle");
				OracleDriver driver = new OracleDriver();
				DriverManager.registerDriver(driver);
				System.out.println(dbSid);
				String url = "jdbc:oracle:thin:@" + host + ":" + dbSid;
				conn = DriverManager.getConnection(url, user, password);
				System.out.println(conn);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new ConnectException("Cannot connect to \"" + host + "\": "
					+ e.getMessage());
			// } catch (ClassNotFoundException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
		}

		// try {
		// if (databaseType == DbConnector.POSTGRESQL_DB) {
		//
		// conn = DriverManager.getConnection("jdbc:postgresql://"
		// + host + "/" + dbName, user, password);
		//
		// } else if (databaseType == DbConnector.ORACLE_DB) {
		// String driverName = "oracle.jdbc.driver.OracleDriver";
		// Class.forName(driverName);
		//
		// String sid = "mydatabase";
		// String url = "jdbc:oracle:thin:@" + host + ":" + sid;
		// conn = DriverManager.getConnection(url, user, password);
		// }
		// } catch (SQLException se) {
		// throw new ConnectException("Cannot connect to \"" + host
		// + "\": " + se.getMessage());
		// } catch (ClassNotFoundException e) {
		// throw new ConnectException(
		// "Cannot instantiate class oracle.jdbc.driver.OracleDriver");
		// }
		//
		return this.connection = conn;
	}

	public int getDbType() {
		return dbType;
	}
	public String getDbName() {
		return dbName;
	}
}

package dbmigrate.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;
import dbmigrate.exceptions.ConnectException;

public class DbConnector {

	public final static String DB_TYPE = "postgresql";

	private int dbType;
	private String dbHost;
	private String dbName;
	private String dbUser;
	private String dbPass;
	private boolean hasParams = false;

	public static final int POSTGRESQL_DB = 1;
	public static final int ORACLE_DB = 2;

	private Connection connection;

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
		if (databaseType == POSTGRESQL_DB) {
			Driver driver = new Driver();
			try {
				DriverManager.registerDriver(driver);
			} catch (SQLException e) {
				throw new ConnectException("Cannot connect to \"" + host
						+ "\": " + e.getMessage());
			}
			Connection conn = null;
			try {

				if (databaseType == DbConnector.POSTGRESQL_DB) {

					conn = DriverManager.getConnection("jdbc:postgresql://"
							+ host + "/" + dbName, user, password);

				} else if (databaseType == DbConnector.ORACLE_DB) {
					String driverName = "oracle.jdbc.driver.OracleDriver";
					Class.forName(driverName);

					String sid = "mydatabase";
					String url = "jdbc:oracle:thin:@" + host + ":" + sid;
					connection = DriverManager.getConnection(url, user,
							password);

				}
			} catch (SQLException se) {
				throw new ConnectException("Cannot connect to \"" + host
						+ "\": " + se.getMessage());
			} catch (ClassNotFoundException e) {
				throw new ConnectException(
						"Cannot instantiate class oracle.jdbc.driver.OracleDriver");
			}
			return this.connection = conn;
		}
		return null;
	}

	public int getDbType() {
		return dbType;
	}
}

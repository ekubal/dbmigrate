package dbmigrate.model.db;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.TestCase;

public class DbConnectorTest extends TestCase {
	
	public void testGetConnection() {
		DbConnector db = new DbConnector();
		Connection connection = db.getConnection("postgresql", "149.156.205.250:13833", "dbmigrate", "dbmigrate", "dbmigrate");
		try {
			assertFalse(connection.isClosed());
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

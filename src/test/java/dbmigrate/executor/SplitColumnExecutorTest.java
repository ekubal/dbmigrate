package dbmigrate.executor;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import dbmigrate.model.db.DbConnector;

public class SplitColumnExecutorTest {
	
	SplitColumnExecutor spe;

	@Test
	public void testSplitColumnExecutor() {
		DbConnector db = new DbConnector();
		Connection connection = db.getConnection("postgresql",
				"149.156.205.250:13833", "dbmigrate",
				"dbmigrate", "dbmigrate");
		spe = new SplitColumnExecutor(connection);
		assertNotNull(spe);
		try {
			connection.close();
		} catch (SQLException e) {
		}
	}

	@Test
	public void testExecuteSplitColumnOperationDescriptor() {
		
	}

	@Test
	public void testGetConnection() {
		DbConnector db = new DbConnector();
		Connection connection = db.getConnection("postgresql",
				"149.156.205.250:13833", "dbmigrate",
				"dbmigrate", "dbmigrate");
		spe = new SplitColumnExecutor(connection);
		assertNotNull(spe.getConnection());
		try {
			connection.close();
		} catch (SQLException e) {
		}
	}

	@Test
	public void testSetConnection() {
		DbConnector db = new DbConnector();
		Connection connection = db.getConnection("postgresql",
				"149.156.205.250:13833", "dbmigrate",
				"dbmigrate", "dbmigrate");
		spe = new SplitColumnExecutor(connection);
		try {
			spe.getConnection().close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		connection = db.getConnection("postgresql",
				"149.156.205.250:13833", "dbmigrate",
				"dbmigrate", "dbmigrate");
		spe.setConnection(connection);
		try {
			assertFalse(spe.getConnection().isClosed());
		} catch (SQLException e1) {
		}
		try {
			spe.getConnection().close();
		} catch (SQLException e) {
		}
	}

	@Test
	public void testExecuteString() {
		
	}

	@Test
	public void testExecuteT() {
		
	}

	@Test
	public void testValidate() {
		
	}

}

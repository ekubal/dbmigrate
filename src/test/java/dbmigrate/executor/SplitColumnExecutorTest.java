package dbmigrate.executor;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.operation.AddColumnOperationDescriptor;
import dbmigrate.model.operation.ColumnOperationDescriptor;
import dbmigrate.model.operation.SplitColumnOperationDescriptor;
import dbmigrate.parser.model.SplitColumn;

public class SplitColumnExecutorTest {
	
	SplitColumnExecutor spe;
	
	SplitColumnOperationDescriptor operation;
	@Test
	public void testSplitColumnExecutor() {
		DbConnector db = new DbConnector();
		Connection connection = null;
		try {
			connection = db.getConnection("postgresql",
				"149.156.205.250:13833", "dbmigrate",
				"dbmigrate", "dbmigrate");
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
		spe = new SplitColumnExecutor(connection);
		assertNotNull(spe);
		try {
			connection.close();
		} catch (SQLException e) {
		}
	}

	@Test
	public void testExecuteSplitColumnOperationDescriptor() {
		String tableName = "ala";
		List<IColumn> columns = new ArrayList<IColumn>();
		Column column = new Column();
		column.setLength(255);
		column.setName("ola");
		column.setNullable(false);
		columns.add(column);
		
		Column column1 = new Column();
		column1.setLength(255);
		column1.setName("column1");
		column1.setNullable(true);
		
		Column column2 = new Column();
		column2.setLength(255);
		column2.setName("column2");
		column2.setNullable(true);
		
		AddColumnOperationDescriptor newColumnDescriptor1 = new AddColumnOperationDescriptor(tableName, column1);
		AddColumnOperationDescriptor newColumnDescriptor2 = new AddColumnOperationDescriptor(tableName, column2);
		operation = new SplitColumnOperationDescriptor(column, "ala", ".a.*", newColumnDescriptor1, newColumnDescriptor2);
		spe = new SplitColumnExecutor(null);
		String sql = spe.createSql(operation);
		assertEquals("UPDATE \"ala\" SET column1 = regexp_split_to_array(ola, E'.a.*')[1], column2 = regexp_split_to_array(ola, E'.a.*')[2]"
, sql);
		
		
		
	}
	

	@Test
	public void testGetConnection() {
		try {
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
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}

	@Test
	public void testSetConnection() {
		try {
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
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}

}

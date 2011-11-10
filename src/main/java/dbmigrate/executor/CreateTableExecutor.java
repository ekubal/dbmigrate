package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.db.IColumn;
import dbmigrate.model.operation.CreateTableOperationDescriptor;

public class CreateTableExecutor extends GeneralExecutor<CreateTableOperationDescriptor> {

	public CreateTableExecutor(Connection connection) {
		this.setConnection(connection);
	}
	
	public String createSql(CreateTableOperationDescriptor operation) {
		String sql = "CREATE TABLE " + operation.getTable().getName() + " (";
		for (IColumn c : operation.getTable().getColumns()) {
			sql += c.getSqlDescription() + ",";
		}
		sql = sql.substring(0, sql.length() - 1) + ");";
		
		return sql;
	}
	
	public void execute(CreateTableOperationDescriptor operation) {
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(createSql(operation));
		} catch (SQLException e) {
			// TODO Throw project-specific exception.
			e.printStackTrace();
		}
	}
	
}

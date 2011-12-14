package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.operation.DropTableOperationDescriptor;

public class DropTableExecutor extends GeneralExecutor<DropTableOperationDescriptor> {
	public DropTableExecutor(Connection connection) {
		this.setConnection(connection);
	}
	
	public String createSql(DropTableOperationDescriptor operation) {
		return "DROP TABLE \"" + operation.getTable().getName() + "\";";
	}
	
	@Override
	public void execute(DropTableOperationDescriptor operation) throws SQLException {
		Statement stmt = this.getConnection().createStatement();
		stmt.executeUpdate(this.createSql(operation));
	}

}

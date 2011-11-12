package dbmigrate.executor;

import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.operation.DropColumnOperationDescriptor;
import java.sql.Connection;


public class DropColumnExecutor extends GeneralExecutor<DropColumnOperationDescriptor> {

	public DropColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public String createSql(DropColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE \"").append(operation.getTable().getName()).append("\" ").append("DROP ").append(operation.getColumn().getName());
		return buf.toString();
	}
	
	public void execute(DropColumnOperationDescriptor operation) throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.executeUpdate(createSql(operation));
	}
}
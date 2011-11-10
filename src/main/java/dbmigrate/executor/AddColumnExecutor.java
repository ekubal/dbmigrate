package dbmigrate.executor;

import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.operation.AddColumnOperationDescriptor;
import java.sql.Connection;

public class AddColumnExecutor extends GeneralExecutor<AddColumnOperationDescriptor> {

	public AddColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public String createSql(AddColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE \"").append(operation.getTableName()).append("\" ").append("ADD ").append(operation.getColumn().getSqlDescription());
		return buf.toString();
	}
	
	public void execute(AddColumnOperationDescriptor operation) throws SQLException {
            Statement stmt = getConnection().createStatement();
            stmt.executeUpdate(createSql(operation));
	}
}

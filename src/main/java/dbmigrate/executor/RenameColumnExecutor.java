package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.operation.RenameColumnOperationDescriptor;

public class RenameColumnExecutor extends GeneralExecutor<RenameColumnOperationDescriptor> {
	public RenameColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public String createSql(RenameColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE \"").append(operation.getTable().getName()).append("\" ").append("RENAME COLUMN ").
			append(operation.getOldColumn().getName()).append(" TO ").append(operation.getNewColumn().getName());
		return buf.toString();
	}
	
	public void execute(RenameColumnOperationDescriptor operation) throws SQLException {
		Statement stmt = this.getConnection().createStatement();
		stmt.executeUpdate(this.createSql(operation));
	}
}
package dbmigrate.executor;

import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.operation.ChangeColumnOperationDescriptor;
import java.sql.Connection;


public class ChangeColumnExecutor extends GeneralExecutor<ChangeColumnOperationDescriptor> {

	public ChangeColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public String createSql(ChangeColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE ").append(operation.getTableName()).append(' ').append("CHANGE ").append(operation.getOldColumnName()).append(" ");
		buf.append(operation.getColumn().getSqlDescription());
		buf.append(";");
		return buf.toString();
	}
	
	public void execute(ChangeColumnOperationDescriptor operation) {
	        try {
	                Statement stmt = getConnection().createStatement();
	                stmt.executeUpdate(createSql(operation));
	        } catch (SQLException e) {
	                // TODO Throw project-specific exception.
	                e.printStackTrace();
	        }
	}
}

package dbmigrate.executor;

import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.operation.AddColumnOperationDescriptor;

public class AddColumnExecutor extends GeneralExecutor<AddColumnOperationDescriptor> {


    public String createSql(AddColumnOperationDescriptor operation) {
    	StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE ").append(operation.getTableName()).append(' ').append("ADD ").append(operation.getColumn().getSqlDescription());

        return buf.toString();
	}
	
	public void execute(AddColumnOperationDescriptor operation) {
	        try {
	                Statement stmt = getConnection().createStatement();
	                stmt.executeUpdate(createSql(operation));
	        } catch (SQLException e) {
	                // TODO Throw project-specific exception.
	                e.printStackTrace();
	        }
	}
}

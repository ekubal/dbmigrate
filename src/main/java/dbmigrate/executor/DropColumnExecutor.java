package dbmigrate.executor;

import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.operation.DropColumnOperationDescriptor;


public class DropColumnExecutor extends GeneralExecutor<DropColumnOperationDescriptor> {


    public String createSql(DropColumnOperationDescriptor operation) {
    	StringBuffer buf = new StringBuffer();
    	buf.append("ALTER TABLE ").append(operation.getTable().getName()).append(' ').append("DROP ").append(operation.getColumn());
        return buf.toString();
	}
	
	public void execute(DropColumnOperationDescriptor operation) {
	        try {
	                Statement stmt = getConnection().createStatement();
	                stmt.executeUpdate(createSql(operation));
	        } catch (SQLException e) {
	                // TODO Throw project-specific exception.
	                e.printStackTrace();
	        }
	}
}
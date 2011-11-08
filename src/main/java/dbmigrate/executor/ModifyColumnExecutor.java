package dbmigrate.executor;

import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.operation.ModifyColumnOperationDescriptor;


public class ModifyColumnExecutor extends GeneralExecutor<ModifyColumnOperationDescriptor> {


    public String createSql(ModifyColumnOperationDescriptor operation) {
    	StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE ").append(operation.getTableName()).append(' ').append("MODIFY ").append(operation.getColumn().getSqlDescription());
        return buf.toString();
	}
	
	public void execute(ModifyColumnOperationDescriptor operation) {
	        try {
	                Statement stmt = getConnection().createStatement();
	                stmt.executeUpdate(createSql(operation));
	        } catch (SQLException e) {
	                // TODO Throw project-specific exception.
	                e.printStackTrace();
	        }
	}
}
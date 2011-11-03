package dbmigrate.executor;

import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.operation.DropTableOperationDescriptor;

public class DropTableExecutor extends GeneralExecutor<DropTableOperationDescriptor> {
	
	public String createSql(DropTableOperationDescriptor operation) {
		return "DROP TABLE " + operation.getTable().getName() + ";";
	}
	
	@Override
	public void execute(DropTableOperationDescriptor operation) {
		try {
			Statement stmt = getConnection().createStatement();
			stmt.executeUpdate(createSql(operation));
		} catch (SQLException e) {
			// TODO Throw project-specific exception.
			e.printStackTrace();
		}
	}

}

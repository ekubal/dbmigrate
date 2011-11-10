package dbmigrate.executor;

import dbmigrate.model.operation.SplitColumnOperationDescriptor;
import java.sql.Connection;
import java.sql.SQLException;

public class SplitColumnExecutor extends GeneralExecutor<SplitColumnOperationDescriptor> {
	public SplitColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public void execute(SplitColumnOperationDescriptor operation) throws SQLException {
		(new AddColumnExecutor(this.getConnection())).execute(operation.getNewColumnDescriptor1());
		(new AddColumnExecutor(this.getConnection())).execute(operation.getNewColumnDescriptor2());
		
		// ToDo:
		// split data
		
		// drop column

	}

}

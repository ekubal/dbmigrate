package dbmigrate.executor;

import dbmigrate.model.operation.SplitColumnOperationDescriptor;
import java.sql.Connection;

public class SplitColumnExecutor extends GeneralExecutor<SplitColumnOperationDescriptor> {
	public SplitColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public void execute(SplitColumnOperationDescriptor operation) {
		(new AddColumnExecutor(this.getConnection())).execute(operation.getNewColumnDescriptor1());
		(new AddColumnExecutor(this.getConnection())).execute(operation.getNewColumnDescriptor2());
		
		// ToDo:
		// split data
		
		// drop column

	}

}

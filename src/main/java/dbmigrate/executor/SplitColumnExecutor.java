package dbmigrate.executor;

import dbmigrate.model.operation.SplitColumnOperationDescriptor;

public class SplitColumnExecutor implements
		IExecutor<SplitColumnOperationDescriptor> {
	public void execute(SplitColumnOperationDescriptor operation) {
		(new AddColumnExecutor()).execute(operation.getNewColumnDescriptor1());
		(new AddColumnExecutor()).execute(operation.getNewColumnDescriptor2());
		
		// ToDo:
		// split data
		
		// drop column

	}

}

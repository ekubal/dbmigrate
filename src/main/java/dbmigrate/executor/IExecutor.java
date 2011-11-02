package dbmigrate.executor;

import dbmigrate.model.operation.IOperationDescriptor;

public interface IExecutor<T extends IOperationDescriptor> {

	public void execute(T operation);
	
}

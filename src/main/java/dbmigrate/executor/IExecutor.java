package dbmigrate.executor;

import java.sql.Connection;

import dbmigrate.model.operation.IOperationDescriptor;

public interface IExecutor<T extends IOperationDescriptor> {

	public void execute(T operation);
	
	public Connection getConnection();
	
}

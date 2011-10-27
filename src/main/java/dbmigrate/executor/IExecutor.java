package dbmigrate.executor;

public interface IExecutor<T extends IOperationDescriptor> {

	public void execute(T operation);
	
}

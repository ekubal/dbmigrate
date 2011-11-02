package dbmigrate.executor;

import dbmigrate.model.operation.IOperationDescriptor;

public class GeneralExecutor<T extends IOperationDescriptor> implements IExecutor<T>{ 
	protected boolean execute(String s) {
		System.out.println(s);
		return true;
	}

	public void execute(T operation) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

package dbmigrate.executor;

public class GeneralExecutor implements IExecutor<T extends IOperationDescriptor>{ 
	protected boolean execute(String s) {
		System.out.println(s);
		return true;
	}
}

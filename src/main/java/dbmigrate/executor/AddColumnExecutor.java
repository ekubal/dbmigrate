package dbmigrate.executor;

public class AddColumnExecutor extends GeneralExecutor implements IExecutor<AddColumnOperationDescriptor> {
	public boolean execute(AddColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE ").append(operation.get)
		
	}

}

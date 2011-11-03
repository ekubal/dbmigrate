package dbmigrate.executor;


public class AddColumnExecutor extends GeneralExecutor<AddColumnOperationDescriptor> {
	@Override
	public void execute(AddColumnOperationDescriptor operation) {
		StringBuilder buf = new StringBuilder();
		buf.append("ALTER TABLE ").append(operation.getTableName());
	}

}

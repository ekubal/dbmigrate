package dbmigrate.operation;

public class CreateTableOperationDescriptor extends TableOperationDescriptor
		implements IOperationDescriptor {

	public CreateTableOperationDescriptor() {
		super();
	}

	public CreateTableOperationDescriptor(ITable table) {
		super(table);
	}

}

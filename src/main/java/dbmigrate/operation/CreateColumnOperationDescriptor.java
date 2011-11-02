package dbmigrate.operation;

public class CreateColumnOperationDescriptor extends ColumnOperationDescriptor
		implements IOperationDescriptor {

	public CreateColumnOperationDescriptor() {
		super();
	}

	public CreateColumnOperationDescriptor(ITable table, IColumn column) {
		super(table, column);
	}

}

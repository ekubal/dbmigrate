package dbmigrate.operation;

public class DropColumnOperationDescriptor extends ColumnOperationDescriptor
		implements IOperationDescriptor {

	public DropColumnOperationDescriptor() {
		super();
	}

	public DropColumnOperationDescriptor(ITable table, IColumn column) {
		super(table, column);
	}

}

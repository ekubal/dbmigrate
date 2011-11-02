package dbmigrate.operation;

public class UpdateColumnOperationDescriptor extends ColumnOperationDescriptor
		implements IOperationDescriptor {

	public UpdateColumnOperationDescriptor() {
		super();
	}

	public UpdateColumnOperationDescriptor(ITable table, IColumn column) {
		super(table, column);
	}

}

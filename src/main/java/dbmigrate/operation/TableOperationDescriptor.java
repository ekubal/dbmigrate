package dbmigrate.operation;

public abstract class TableOperationDescriptor implements IOperationDescriptor {

	private ITable table;

	public TableOperationDescriptor() {
	}

	public TableOperationDescriptor(ITable table) {
		this.table = table;
	}

	public void setTable(ITable table) {
		this.table = table;
	}

	public ITable getTable() {
		return table;
	}

}

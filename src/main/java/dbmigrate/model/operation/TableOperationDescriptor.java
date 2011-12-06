package dbmigrate.model.operation;

import dbmigrate.model.db.ITable;

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
		return this.table;
	}

}

package dbmigrate.model.operation;

import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.ITable;

public abstract class ColumnOperationDescriptor {
	
	private ITable table;
	private IColumn column;

	public ColumnOperationDescriptor() {
	}

	public ColumnOperationDescriptor(ITable table, IColumn column) {
		this.table = table;
	}

	public void setTable(ITable table) {
		this.table = table;
	}

	public ITable getTable() {
		return table;
	}

	public IColumn getColumn() {
		return column;
	}

	public void setColumn(IColumn column) {
		this.column = column;
	}

}

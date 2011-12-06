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
		this.column = column;
	}

	public void setTable(ITable table) {
		this.table = table;
	}

	public ITable getTable() {
		return this.table;
	}

	public IColumn getColumn() {
		return this.column;
	}

	public void setColumn(IColumn column) {
		this.column = column;
	}

}

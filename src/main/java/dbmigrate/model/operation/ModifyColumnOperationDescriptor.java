package dbmigrate.model.operation;

import dbmigrate.model.db.IColumn;

public class ModifyColumnOperationDescriptor implements IOperationDescriptor {
	private IColumn column;
	private String tableName;
	public ModifyColumnOperationDescriptor(String tableName, IColumn column) {
		this.column = column;
		this.tableName = tableName;
	}
	public IColumn getColumn() {
		return this.column;
	}
	public void setColumn(IColumn column) {
		this.column = column;
	}
	public String getTableName() {
		return this.tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean execute() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

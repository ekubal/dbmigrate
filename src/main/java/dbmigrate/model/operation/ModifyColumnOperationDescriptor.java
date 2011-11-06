package dbmigrate.model.operation;

import dbmigrate.model.db.Column;

public class ModifyColumnOperationDescriptor implements IOperationDescriptor {
	private Column column;
	private String tableName;
	public ModifyColumnOperationDescriptor(String tableName, Column column) {
		this.column = column;
		this.tableName = tableName;
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean execute() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

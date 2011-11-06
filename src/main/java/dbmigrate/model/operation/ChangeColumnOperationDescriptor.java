package dbmigrate.model.operation;

import dbmigrate.model.db.Column;

public class ChangeColumnOperationDescriptor implements IOperationDescriptor {
	private Column column;
	private String tableName;
	private String newColumnName;
	public ChangeColumnOperationDescriptor(String tableName, Column column, String newColumnName) {
		this.column = column;
		this.tableName = tableName;
		this.newColumnName = newColumnName;
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
	public String getNewColumnName() {
		return newColumnName;
	}
	public void setNewColumnName(String newColumnName) {
		this.newColumnName = newColumnName;
	}

	public boolean execute() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

package dbmigrate.model.operation;

import dbmigrate.model.db.Column;

public class DropColumnOperationDescriptor implements IOperationDescriptor {
	private String columnName;
	private String tableName;
	public DropColumnOperationDescriptor(String tableName, String columnName) {
		this.columnName = columnName;
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
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

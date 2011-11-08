package dbmigrate.model.operation;

import dbmigrate.model.db.Column;

public class ChangeColumnOperationDescriptor implements IOperationDescriptor {
	private Column column;
	private String tableName;
	private String oldColumnName;
	public ChangeColumnOperationDescriptor(String tableName, Column column, String oldColumnName) {
		this.column = column;
		this.tableName = tableName;
		this.oldColumnName = oldColumnName;
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
	public String getOldColumnName() {
		return oldColumnName;
	}
	public void setOldColumnName(String oldColumnName) {
		this.oldColumnName = oldColumnName;
	}
	
	public boolean execute() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

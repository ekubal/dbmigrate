package dbmigrate.model.operation;

import dbmigrate.model.db.IColumn;

public class ChangeColumnOperationDescriptor implements IOperationDescriptor {
	private IColumn column;
	private String tableName;
	private String newColumnName;
	public ChangeColumnOperationDescriptor(String tableName, IColumn column, String newColumnName) {
		this.column = column;
		this.tableName = tableName;
		this.newColumnName = newColumnName;
	}
	public IColumn getColumn() {
		return column;
	}
	public void setColumn(IColumn column) {
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

package dbmigrate.model.operation;

import dbmigrate.model.db.IColumn;

public class ChangeColumnOperationDescriptor implements IOperationDescriptor {
	private IColumn column;
	private String tableName;
	private String oldColumnName;
	public ChangeColumnOperationDescriptor(String tableName, IColumn column, String oldColumnName) {
		this.column = column;
		this.tableName = tableName;
		this.oldColumnName = oldColumnName;
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

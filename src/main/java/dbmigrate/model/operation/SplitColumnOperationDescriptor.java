package dbmigrate.model.operation;

import dbmigrate.model.db.Column;

public class SplitColumnOperationDescriptor implements IOperationDescriptor {
	private Column column;
	private String tableName;
	private String regexp;
	private AddColumnOperationDescriptor newColumnDescriptor1;
	private AddColumnOperationDescriptor newColumnDescriptor2;

	public SplitColumnOperationDescriptor(Column column, String tableName,
			String regexp, AddColumnOperationDescriptor newColumnDescriptor1,
			AddColumnOperationDescriptor newColumnDescriptor2) {
		super();
		this.column = column;
		this.tableName = tableName;
		this.regexp = regexp;
		this.newColumnDescriptor1 = newColumnDescriptor1;
		this.newColumnDescriptor2 = newColumnDescriptor2;
	}

	public Column getColumn() {
		return this.column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRegexp() {
		return this.regexp;
	}

	public void setRegexp(String regexp) {
		this.regexp = regexp;
	}

	public AddColumnOperationDescriptor getNewColumnDescriptor1() {
		return this.newColumnDescriptor1;
	}

	public void setNewColumnDescriptor1(
			AddColumnOperationDescriptor newColumnDescriptor1) {
		this.newColumnDescriptor1 = newColumnDescriptor1;
	}

	public AddColumnOperationDescriptor getNewColumnDescriptor2() {
		return this.newColumnDescriptor2;
	}

	public void setNewColumnDescriptor2(
			AddColumnOperationDescriptor newColumnDescriptor2) {
		this.newColumnDescriptor2 = newColumnDescriptor2;
	}
}

package dbmigrate.model.operation;

import dbmigrate.model.db.Column;

public class MergeColumnOperationDescriptor implements IOperationDescriptor {
	private Column sourceColumn1;
	private Column sourceColumn2;
	private String tableName;
	private String delimiter;
	private AddColumnOperationDescriptor destinationColumn;

	public MergeColumnOperationDescriptor(Column sourceColumn1,
			Column sourceColumn2, String tableName, String delimiter,
			AddColumnOperationDescriptor destinationColumn) {
		super();
		this.sourceColumn1 = sourceColumn1;
		this.sourceColumn2 = sourceColumn2;
		this.tableName = tableName;
		this.delimiter = delimiter;
		this.destinationColumn = destinationColumn;
	}

	public Column getSourceColumn1() {
		return this.sourceColumn1;
	}

	public void setSourceColumn1(Column sourceColumn1) {
		this.sourceColumn1 = sourceColumn1;
	}

	public Column getSourceColumn2() {
		return this.sourceColumn2;
	}

	public void setSourceColumn2(Column sourceColumn2) {
		this.sourceColumn2 = sourceColumn2;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDelimiter() {
		return this.delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public AddColumnOperationDescriptor getDestinationColumnDescriptor() {
		return this.destinationColumn;
	}

	public void setDestinationColumn(
			AddColumnOperationDescriptor destinationColumn) {
		this.destinationColumn = destinationColumn;
	}

	
}

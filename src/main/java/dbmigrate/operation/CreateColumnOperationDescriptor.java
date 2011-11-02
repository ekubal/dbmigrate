package dbmigrate.operation;

import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.ITable;

public class CreateColumnOperationDescriptor extends ColumnOperationDescriptor
		implements IOperationDescriptor {

	public CreateColumnOperationDescriptor() {
		super();
	}

	public CreateColumnOperationDescriptor(ITable table, IColumn column) {
		super(table, column);
	}

}

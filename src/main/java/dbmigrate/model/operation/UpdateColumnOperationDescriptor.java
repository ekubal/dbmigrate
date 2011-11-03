package dbmigrate.model.operation;

import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.ITable;

public class UpdateColumnOperationDescriptor extends ColumnOperationDescriptor
		implements IOperationDescriptor {

	public UpdateColumnOperationDescriptor() {
		super();
	}

	public UpdateColumnOperationDescriptor(ITable table, IColumn column) {
		super(table, column);
	}

}

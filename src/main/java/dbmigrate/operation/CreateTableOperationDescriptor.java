package dbmigrate.operation;

import dbmigrate.model.db.ITable;

public class CreateTableOperationDescriptor extends TableOperationDescriptor
		implements IOperationDescriptor {

	public CreateTableOperationDescriptor() {
		super();
	}

	public CreateTableOperationDescriptor(ITable table) {
		super(table);
	}

}

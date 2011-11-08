package dbmigrate.executor;

import java.sql.Connection;

import dbmigrate.model.operation.DropTableOperationDescriptor;

public class DropTableExecutor implements IExecutor<DropTableOperationDescriptor> {
	public void execute(DropTableOperationDescriptor operation) {
		
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}

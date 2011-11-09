package dbmigrate.executor;

import java.sql.Connection;

import dbmigrate.model.operation.DropTableOperationDescriptor;

public class DropTableExecutor extends GeneralExecutor<DropTableOperationDescriptor> {
	public DropTableExecutor(Connection connection) {
		this.setConnection(connection);
	}
	
	public void execute(DropTableOperationDescriptor operation) {
		
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}

package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;

import dbmigrate.model.operation.IOperationDescriptor;

public interface IExecutor<T extends IOperationDescriptor> {

	public void setConnection(Connection connection);
	
	public void execute(T operation) throws SQLException;
	
	public Connection getConnection();
}

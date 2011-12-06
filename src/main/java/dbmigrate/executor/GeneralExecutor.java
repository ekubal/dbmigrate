package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;

import dbmigrate.exceptions.ValidationException;
import dbmigrate.model.operation.IOperationDescriptor;

public class GeneralExecutor<T extends IOperationDescriptor> implements IExecutor<T> {

	private Connection connection;

	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	protected boolean execute(String s) {
		System.out.println(s);
		return true;
	}

	public void execute(T operation) throws SQLException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void validate(T operation) throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}

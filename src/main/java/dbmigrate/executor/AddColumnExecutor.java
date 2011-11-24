package dbmigrate.executor;

import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.exception.ValidationException;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.AddColumnOperationDescriptor;

import java.sql.Connection;

public class AddColumnExecutor extends GeneralExecutor<AddColumnOperationDescriptor> {

	public AddColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public String createSql(AddColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE \"").append(operation.getTableName()).append("\" ").append("ADD ").append(operation.getColumn().getSqlDescription());
		System.out.println(buf);
		return buf.toString();
	}
	
	public void execute(AddColumnOperationDescriptor operation) throws SQLException {
		    Statement stmt = getConnection().createStatement();
            stmt.executeUpdate(createSql(operation));
	}
	
	public void validate(AddColumnOperationDescriptor operation) throws ValidationException {
		if(operation.getColumn().getType() == TypeEnum.VARCHAR && operation.getColumn().getLength() < 1) {
			throw new ValidationException("Varchar columns must have a positive length.");
		}
	}
}

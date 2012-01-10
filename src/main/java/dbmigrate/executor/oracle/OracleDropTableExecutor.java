package dbmigrate.executor.oracle;

import java.sql.Connection;

import dbmigrate.executor.DropTableExecutor;
import dbmigrate.model.operation.DropTableOperationDescriptor;

public class OracleDropTableExecutor extends DropTableExecutor {
	public OracleDropTableExecutor(Connection connection) {
		super(connection);
	}

	public String createSql(DropTableOperationDescriptor operation) {
		return "DROP TABLE \"" + operation.getTable().getName() + "\"";
	}
}

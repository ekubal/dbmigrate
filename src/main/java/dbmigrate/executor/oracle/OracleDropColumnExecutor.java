package dbmigrate.executor.oracle;

import java.sql.Connection;

import dbmigrate.executor.DropColumnExecutor;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.operation.DropColumnOperationDescriptor;

public class OracleDropColumnExecutor extends DropColumnExecutor {

	public OracleDropColumnExecutor(Connection connection) {
		super(connection);
	}

	public String createSql(DropColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE \"").append(DbConnector.instance().getDbName())
				.append("\".\"").append(operation.getTable().getName())
				.append("\" ").append("DROP COLUMN ")
				.append(operation.getColumn().getName());
		return buf.toString();
	}

}
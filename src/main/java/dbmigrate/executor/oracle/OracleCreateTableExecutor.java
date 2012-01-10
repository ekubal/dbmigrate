package dbmigrate.executor.oracle;

import java.sql.Connection;

import dbmigrate.executor.CreateTableExecutor;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.operation.CreateTableOperationDescriptor;

public class OracleCreateTableExecutor extends CreateTableExecutor {
	public OracleCreateTableExecutor(Connection connection) {
		super(connection);
	}

	public String createSql(CreateTableOperationDescriptor operation) {
		String sql = "CREATE TABLE \"" + DbConnector.instance().getDbName()
				+ "\".\"" + operation.getTable().getName() + "\" ( ";
		for (IColumn c : operation.getTable().getColumns()) {
			sql += c.getSqlDescription() + ",";
		}
		sql = sql.substring(0, sql.length() - 1) + ")";
		return sql;
	}
}

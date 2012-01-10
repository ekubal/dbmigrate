package dbmigrate.executor.oracle;

import java.sql.Connection;

import dbmigrate.executor.AddColumnExecutor;
import dbmigrate.model.db.Column;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.operation.AddColumnOperationDescriptor;

public class OracleAddColumnExecutor extends AddColumnExecutor {

	public OracleAddColumnExecutor(Connection connection) {
		super(connection);
	}

	public String createSql(AddColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE \"").append(DbConnector.instance().getDbName())
				.append("\".\"").append(operation.getTableName()).append("\" ")
				.append("ADD ")
				.append(operation.getColumn().getSqlDescription() + "");
System.out.println(buf.toString());
		return buf.toString();
	}
 
}

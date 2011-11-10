package dbmigrate.executor;

import dbmigrate.model.operation.DropColumnOperationDescriptor;
import dbmigrate.model.operation.MergeColumnOperationDescriptor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MergeColumnExecutor extends GeneralExecutor<MergeColumnOperationDescriptor> {
	public MergeColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}
	
	public void execute(MergeColumnOperationDescriptor operation) throws SQLException {
		(new AddColumnExecutor(this.getConnection())).execute(operation.getDestinationColumn());
		
		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE \"").append(operation.getTableName()).append("\" SET ")
				.append(operation.getDestinationColumn().getTableName())
				.append(" = ").append(operation.getSourceColumn1())
				.append(" || '").append(operation.getDelimiter())
				.append("' || ").append(operation.getSourceColumn2());
		
		Statement stmt = getConnection().createStatement();
        stmt.executeUpdate(buf.toString());
		
        DropColumnExecutor dcx = new DropColumnExecutor(getConnection());
        DropColumnOperationDescriptor dcod = new DropColumnOperationDescriptor(operation.getTableName(), operation.getSourceColumn1());
        dcx.execute(dcod);
	}

}

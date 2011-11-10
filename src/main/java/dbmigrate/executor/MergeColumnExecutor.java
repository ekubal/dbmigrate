package dbmigrate.executor;

import dbmigrate.model.operation.MergeColumnOperationDescriptor;
import java.sql.Connection;
import java.sql.SQLException;

public class MergeColumnExecutor extends GeneralExecutor<MergeColumnOperationDescriptor> {
	public MergeColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}
	
	public void execute(MergeColumnOperationDescriptor operation) throws SQLException {
		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE \"").append(operation.getTableName()).append("\" SET ")
				.append(operation.getDestinationColumn().getTableName())
				.append(" = ").append(operation.getSourceColumn1())
				.append(" || '").append(operation.getDelimiter())
				.append("' || ").append(operation.getSourceColumn2());
		(new AddColumnExecutor(this.getConnection())).execute(operation.getDestinationColumn());
		
		//connector.query
		
		// drop column
	}

}

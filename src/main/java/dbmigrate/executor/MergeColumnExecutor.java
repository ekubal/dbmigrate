package dbmigrate.executor;

import dbmigrate.model.operation.MergeColumnOperationDescriptor;

public class MergeColumnExecutor implements
		IExecutor<MergeColumnOperationDescriptor> {
	public void execute(MergeColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE ").append(operation.getTableName()).append(" SET ")
				.append(operation.getDestinationColumn().getTableName())
				.append(" = ").append(operation.getSourceColumn1())
				.append(" || '").append(operation.getDelimiter())
				.append("' || ").append(operation.getSourceColumn2());
		(new AddColumnExecutor()).execute(operation.getDestinationColumn());
		
		//connector.query
		
		// drop column
	}

}

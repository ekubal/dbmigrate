package dbmigrate.executor;

import dbmigrate.model.db.ITable;
import dbmigrate.model.db.Table;
import dbmigrate.model.operation.DropColumnOperationDescriptor;
import dbmigrate.model.operation.MergeColumnOperationDescriptor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MergeColumnExecutor extends
		GeneralExecutor<MergeColumnOperationDescriptor> {
	public MergeColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public void execute(MergeColumnOperationDescriptor operation)
			throws SQLException {
		AddColumnExecutor executor = new AddColumnExecutor(this.getConnection());
		executor.execute(operation.getDestinationColumnDescriptor());

		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE \"")
				.append(operation.getTableName())
				.append("\" SET ")
				.append(operation.getDestinationColumnDescriptor()
						.getTableName()).append(" = ")
				.append(operation.getSourceColumn1()).append(" || '")
				.append(operation.getDelimiter()).append("' || ")
				.append(operation.getSourceColumn2());

		Statement stmt = getConnection().createStatement();
		stmt.executeUpdate(buf.toString());

		DropColumnExecutor dcx = new DropColumnExecutor(getConnection());

		ITable table = new Table();
		table.setName(operation.getTableName());
		DropColumnOperationDescriptor dcod = new DropColumnOperationDescriptor(
				table, operation.getSourceColumn1());
		dcx.execute(dcod);

		dcod = new DropColumnOperationDescriptor(table,
				operation.getSourceColumn2());
		dcx.execute(dcod);
	}

}

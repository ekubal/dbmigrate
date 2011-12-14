package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.db.ITable;
import dbmigrate.model.db.Table;
import dbmigrate.model.operation.DropColumnOperationDescriptor;
import dbmigrate.model.operation.MergeColumnOperationDescriptor;

public class MergeColumnExecutor extends
		GeneralExecutor<MergeColumnOperationDescriptor> {
	public MergeColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

        public String createSql(MergeColumnOperationDescriptor operation) {
            StringBuffer buf = new StringBuffer();
            buf.append("UPDATE \"");
            buf.append(operation.getTableName());
            buf.append("\" SET ");
            buf.append(operation.getDestinationColumnDescriptor().getTableName()).append(" = ");
            buf.append(operation.getSourceColumn1().getName()).append(" || '");
            buf.append(operation.getDelimiter()).append("' || ");
            buf.append(operation.getSourceColumn2().getName());
            return buf.toString();
        }
        
	public void execute(MergeColumnOperationDescriptor operation)
			throws SQLException {
		AddColumnExecutor executor = new AddColumnExecutor(this.getConnection());
		executor.execute(operation.getDestinationColumnDescriptor());

		Statement stmt = this.getConnection().createStatement();
		stmt.executeUpdate(createSql(operation));

		DropColumnExecutor dcx = new DropColumnExecutor(this.getConnection());

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

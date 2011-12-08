package dbmigrate.executor;

import dbmigrate.model.db.ITable;
import dbmigrate.model.db.Table;
import dbmigrate.model.operation.AddColumnOperationDescriptor;
import dbmigrate.model.operation.DropColumnOperationDescriptor;
import dbmigrate.model.operation.SplitColumnOperationDescriptor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SplitColumnExecutor extends
		GeneralExecutor<SplitColumnOperationDescriptor> {
	public SplitColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public void execute(SplitColumnOperationDescriptor operation)
			throws SQLException {
		AddColumnExecutor ace = new AddColumnExecutor(this.getConnection());
		ace.execute(operation.getNewColumnDescriptor1());
		ace.execute(operation.getNewColumnDescriptor2());

		String sql = this.createSql(operation);

		Statement stmt = this.getConnection().createStatement();
		stmt.executeUpdate(sql);

		DropColumnExecutor dcx = new DropColumnExecutor(this.getConnection());

		ITable table = new Table();
		table.setName(operation.getTableName());
		DropColumnOperationDescriptor dcod = new DropColumnOperationDescriptor(
				table, operation.getColumn());
		dcx.execute(dcod);
	}
	
	public String createSql(SplitColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE \"");
				buf.append(operation.getTableName());
				buf.append("\" SET ");
				buf.append(operation.getNewColumnDescriptor1().getColumn()
						.getName());
				buf.append(" = regexp_split_to_array(");
				buf.append(operation.getColumn().getName());
				buf.append(", E'");
				buf.append(operation.getRegexp());
				buf.append("')[1], ");
				buf.append(operation.getNewColumnDescriptor2().getColumn()
						.getName()).append(" = regexp_split_to_array(");
				buf.append(operation.getColumn().getName()).append(", E'");
				buf.append(operation.getRegexp()).append("')[2]");
		return buf.toString();
	}
}

package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.operation.ChangeColumnOperationDescriptor;

public class ChangeColumnExecutor extends GeneralExecutor<ChangeColumnOperationDescriptor> {

	public ChangeColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public String createSql(ChangeColumnOperationDescriptor operation) {
		StringBuilder buf = new StringBuilder();
		buf.append("ALTER TABLE \"").append(operation.getTableName()).append("\" ").append("CHANGE ").append(operation.getOldColumnName()).append(" ");
		buf.append(operation.getColumn().getSqlDescription());
		buf.append(";");

		if (operation.getColumn().getValueToSet() != null) {
			boolean random = operation.getColumn().getValueToSet().equals(Column.RANDOM);
			IColumn column = operation.getColumn();
			buf.append("UPDATE \"");
			buf.append(operation.getTableName());
			buf.append("\" SET ");
			buf.append(column.getName());
			buf.append("=");

			switch (column.getType()) {
				case VARCHAR:
					if (!random) {
						buf.append("'").append(column.getValueToSet()).append("'");
					} else {
						buf.append("md5(random()::text)");
					}
					break;
				case TEXT:
					if (!random) {
						buf.append("'").append(column.getValueToSet()).append("'");
					} else {
						buf.append("md5(random()::text)");
					}
					break;
				case DATETIME:
					if (!random) {
						buf.append("'").append(column.getValueToSet()).append("'");
					} else {
						buf.append("NOW() - '1 day'::INTERVAL * ROUND(RANDOM() * 100*365)");
					}
					break;
				case DATE:
					if (!random) {
						buf.append("'").append(column.getValueToSet()).append("'");
					} else {
						buf.append("NOW() - '1 day'::INTERVAL * ROUND(RANDOM() * 100*365)");
					}
					break;
				case BINARY:
					if (!random) {
						buf.append("B'").append(column.getValueToSet()).append("'");
					} else {
						buf.append("B'10101010'");
					}
					break;
				default:
					if (!random) {
						buf.append(column.getValueToSet()).append("");
					} else {
						buf.append("random()");
					}
					break;
			}
			buf.append(";");
		}

		return buf.toString();
	}

	public void execute(ChangeColumnOperationDescriptor operation) {
		try {
			Statement stmt = this.getConnection().createStatement();
			stmt.executeUpdate(this.createSql(operation));
		} catch (SQLException e) {
			// TODO Throw project-specific exception.
			e.printStackTrace();
		}
	}
}

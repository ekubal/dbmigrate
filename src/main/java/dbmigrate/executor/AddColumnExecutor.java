package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dbmigrate.exceptions.ValidationException;
import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.AddColumnOperationDescriptor;

public class AddColumnExecutor extends GeneralExecutor<AddColumnOperationDescriptor> {

	public AddColumnExecutor(Connection connection) {
		this.setConnection(connection);
	}

	public String createSql(AddColumnOperationDescriptor operation) {
		StringBuffer buf = new StringBuffer();
		buf.append("ALTER TABLE \"").append(operation.getTableName()).append("\" ").append("ADD ").append(operation.getColumn().getSqlDescription() + "; ");

		if (operation.getColumn().getValueToSet() != null) {
			boolean R = operation.getColumn().getValueToSet().equals(Column.RANDOM);
			IColumn column = operation.getColumn();
			buf.append("UPDATE \"");
			buf.append(operation.getTableName());
			buf.append("\" SET ");
			buf.append(column.getName());
			buf.append("=");

			switch (column.getType()) {
				case VARCHAR:
					if (!R)
						buf.append("'" + column.getValueToSet() + "'");
					else
						buf.append("md5(random()::text)");
					break;
				case TEXT:
					if (!R)
						buf.append("'" + column.getValueToSet() + "'");
					else
						buf.append("md5(random()::text)");
					break;
				case DATETIME:
					if (!R)
						buf.append("'" + column.getValueToSet() + "'");
					else
						buf.append("NOW() - '1 day'::INTERVAL * ROUND(RANDOM() * 100*365)");
					break;
				case DATE:
					if (!R)
						buf.append("'" + column.getValueToSet() + "'");
					else
						buf.append("NOW() - '1 day'::INTERVAL * ROUND(RANDOM() * 100*365)");
					break;
				case BINARY:
					if (!R)
						buf.append("B'" + column.getValueToSet() + "'");
					else
						buf.append("B'10101010'");
					break;
				default:
					if (!R)
						buf.append(column.getValueToSet() + "");
					else
						buf.append("random()");
					break;
			}
			buf.append(";");
		}

		return buf.toString();
	}

	public void execute(AddColumnOperationDescriptor operation) throws SQLException {
		Statement stmt = this.getConnection().createStatement();
		stmt.executeUpdate(this.createSql(operation));
	}

	public void validate(AddColumnOperationDescriptor operation) throws ValidationException {
		if (operation.getColumn().getType() == TypeEnum.VARCHAR && operation.getColumn().getLength() < 1) {
			throw new ValidationException("Varchar columns must have a positive length.");
		}
	}
}

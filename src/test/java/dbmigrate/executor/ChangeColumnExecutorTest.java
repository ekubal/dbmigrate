package dbmigrate.executor;

import java.util.ArrayList;
import java.util.List;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.ChangeColumnOperationDescriptor;

import junit.framework.TestCase;

public class ChangeColumnExecutorTest extends TestCase {
	public void testCreateSql() {
		String tableName = "tableName";
		String oldColumnName = "oldColumnName";

		List<IColumn> columns = new ArrayList<IColumn>();

		Column column = new Column();
		column.setLength(255);
		column.setType(TypeEnum.VARCHAR);
		column.setName("ola_varchar_255");
		column.setNullable(false);
		columns.add(column);

		ChangeColumnOperationDescriptor operation = new ChangeColumnOperationDescriptor(tableName, column, oldColumnName);

		ChangeColumnExecutor executor = new ChangeColumnExecutor(null);
		System.out.println(executor.createSql(operation));
		assertEquals("ALTER TABLE \"tableName\" CHANGE oldColumnName ola_varchar_255 VARCHAR (255) NOT NULL;", executor.createSql(operation));

	}

	public void testSetValueText() {
		String tableName = "tableName";
		String oldColumnName = "oldColumnName";

		List<IColumn> columns = new ArrayList<IColumn>();

		Column column = new Column();
		column.setLength(255);
		column.setType(TypeEnum.VARCHAR);
		column.setName("ola_varchar_255");
		column.setNullable(false);
		column.setValueToSet("Kamil");
		columns.add(column);

		ChangeColumnOperationDescriptor operation = new ChangeColumnOperationDescriptor(tableName, column, oldColumnName);

		ChangeColumnExecutor executor = new ChangeColumnExecutor(null);
		System.out.println(executor.createSql(operation));
		assertEquals("ALTER TABLE \"tableName\" CHANGE oldColumnName ola_varchar_255 VARCHAR (255) NOT NULL;UPDATE \"tableName\" SET ola_varchar_255='Kamil' ;", executor.createSql(operation));

	}

	public void testSetValueBinary() {
		String tableName = "tableName";
		String oldColumnName = "oldColumnName";

		List<IColumn> columns = new ArrayList<IColumn>();

		Column column = new Column();
		column.setLength(255);
		column.setType(TypeEnum.BINARY);
		column.setName("ola_varchar_255");
		column.setNullable(false);
		column.setValueToSet("01010101");
		columns.add(column);

		ChangeColumnOperationDescriptor operation = new ChangeColumnOperationDescriptor(tableName, column, oldColumnName);

		ChangeColumnExecutor executor = new ChangeColumnExecutor(null);
		System.out.println(executor.createSql(operation));
		assertEquals("ALTER TABLE \"tableName\" CHANGE oldColumnName ola_varchar_255 BINARY NOT NULL;UPDATE \"tableName\" SET ola_varchar_255=B'01010101' ;", executor.createSql(operation).trim());

	}

	public void testSetValueInt() {
		String tableName = "tableName";
		String oldColumnName = "oldColumnName";

		List<IColumn> columns = new ArrayList<IColumn>();

		Column column = new Column();
		column.setLength(255);
		column.setType(TypeEnum.INT);
		column.setName("ola_varchar_255");
		column.setNullable(false);
		column.setValueToSet("69");
		columns.add(column);

		ChangeColumnOperationDescriptor operation = new ChangeColumnOperationDescriptor(tableName, column, oldColumnName);

		ChangeColumnExecutor executor = new ChangeColumnExecutor(null);
		System.out.println(executor.createSql(operation));
		assertEquals("ALTER TABLE \"tableName\" CHANGE oldColumnName ola_varchar_255 INT NOT NULL;UPDATE \"tableName\" SET ola_varchar_255=69 ;", executor.createSql(operation).trim());

	}
}

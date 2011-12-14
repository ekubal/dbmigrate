package dbmigrate.executor;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.AddColumnOperationDescriptor;

public class AddColumnExecutorTest extends TestCase {
	private AddColumnOperationDescriptor operation;

	private static final String DEFAULT_TEXT = "ala123";
	private static final String DEFAULT_INT = "123";
	private static final String DEFAULT_BYTE = "1010101010";
	private static final String DEFAULT_BOOLEAN = "TRUE";
	private static final String DEFAULT_DATE = "2011-12-01 18:17:03";

	public void setUp() {
		String tableName = "ala";
		List<IColumn> columns = new ArrayList<IColumn>();
		Column column = new Column();
		column.setLength(255);
		column.setName("ola");
		column.setNullable(false);
		columns.add(column);
		operation = new AddColumnOperationDescriptor(tableName, column);
	}

	public void tuneColumn(TypeEnum type, String defaultValue) {
		operation.getColumn().setDefault(defaultValue);
		operation.getColumn().setType(type);
	}

	public void setValue(String value) {
		operation.getColumn().setValueToSet(value);
	}

	public void testCreateSql() {
		String tableName = "ala";

		List<IColumn> columns = new ArrayList<IColumn>();

		Column column = new Column();
		column.setLength(255);
		column.setType(TypeEnum.VARCHAR);
		column.setName("ola_varchar_255");
		column.setNullable(false);
		column.setDefault("DOPE");
		columns.add(column);

		AddColumnOperationDescriptor operation = new AddColumnOperationDescriptor(tableName, column);

		AddColumnExecutor executor = new AddColumnExecutor(null);
		System.out.println(executor.createSql(operation));
		assertEquals("ALTER TABLE \"ala\" ADD ola_varchar_255 VARCHAR (255) DEFAULT 'DOPE' NOT NULL;", executor.createSql(operation).trim());
	}

	public void testCreateSqlWithDefaultText() {
		tuneColumn(TypeEnum.TEXT, DEFAULT_TEXT);
		AddColumnExecutor executor = new AddColumnExecutor(null);
		System.out.println(executor.createSql(operation).trim());
		assertEquals("ALTER TABLE \"ala\" ADD ola TEXT (255) DEFAULT '" + DEFAULT_TEXT + "' NOT NULL;", executor.createSql(operation).trim());
	}

	public void testCreateSqlWithDefaultInt() {
		tuneColumn(TypeEnum.INT, DEFAULT_INT);
		AddColumnExecutor executor = new AddColumnExecutor(null);
		System.out.println(executor.createSql(operation).trim());
		assertEquals("ALTER TABLE \"ala\" ADD ola INT DEFAULT " + DEFAULT_INT + " NOT NULL;", executor.createSql(operation).trim());
	}

	public void testCreateSqlWithDefaultDatetime() {
		tuneColumn(TypeEnum.DATETIME, DEFAULT_DATE);
		AddColumnExecutor executor = new AddColumnExecutor(null);
		System.out.println(executor.createSql(operation).trim());
		assertEquals("ALTER TABLE \"ala\" ADD ola DATETIME DEFAULT '" + DEFAULT_DATE + "' NOT NULL;", executor.createSql(operation).trim());
	}

	public void testCreateSqlWithDefaultBoolean() {
		tuneColumn(TypeEnum.BOOLEAN, DEFAULT_BOOLEAN);
		AddColumnExecutor executor = new AddColumnExecutor(null);
		System.out.println(executor.createSql(operation).trim());
		assertEquals("ALTER TABLE \"ala\" ADD ola BOOLEAN DEFAULT " + DEFAULT_BOOLEAN + " NOT NULL;", executor.createSql(operation).trim());
	}

	public void testCreateSqlWithDefaultByte() {
		tuneColumn(TypeEnum.BINARY, DEFAULT_BYTE);
		AddColumnExecutor executor = new AddColumnExecutor(null);
		System.out.println(executor.createSql(operation).trim());
		assertEquals("ALTER TABLE \"ala\" ADD ola BINARY DEFAULT B'" + DEFAULT_BYTE + "' NOT NULL;", executor.createSql(operation).trim());
	}

	public void testCreateSqlWithDefaultValueText() {
		tuneColumn(TypeEnum.TEXT, DEFAULT_TEXT);
		setValue("Kamil");
		AddColumnExecutor executor = new AddColumnExecutor(null);
		System.out.println(executor.createSql(operation).trim());
		assertEquals("ALTER TABLE \"ala\" ADD ola TEXT (255) DEFAULT 'ala123' NOT NULL; UPDATE \"ala\" SET ola='Kamil';", executor.createSql(operation).trim());
	}

	public void testCreateSqlWithDefaultValueTextRandom() {
		tuneColumn(TypeEnum.TEXT, DEFAULT_TEXT);
		setValue(Column.RANDOM);
		AddColumnExecutor executor = new AddColumnExecutor(null);
		System.out.println(executor.createSql(operation).trim());
		assertEquals("ALTER TABLE \"ala\" ADD ola TEXT (255) DEFAULT 'ala123' NOT NULL; UPDATE \"ala\" SET ola=md5(random()::text);", executor.createSql(operation).trim());
	}

}

package dbmigrate.executor;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.ChangeColumnOperationDescriptor;

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
}

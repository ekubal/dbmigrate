package dbmigrate.executor;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.AddColumnOperationDescriptor;

public class AddColumnExecutorTest extends TestCase {
	public void testCreateSql() {
		String tableName = "ala";
		
		List<IColumn> columns = new ArrayList<IColumn>();
		
		Column column = new Column();
		column.setLength(255);
		column.setType(TypeEnum.VARCHAR);
		column.setName("ola_varchar_255");
		column.setNullable(false);
		columns.add(column);
		
		AddColumnOperationDescriptor operation = new AddColumnOperationDescriptor(tableName, column);
		
		AddColumnExecutor executor = new AddColumnExecutor(null);
		System.out.println(executor.createSql(operation));
		assertEquals("ALTER TABLE \"ala\" ADD ola_varchar_255 VARCHAR (255) NOT NULL", executor.createSql(operation));
		
	}
}

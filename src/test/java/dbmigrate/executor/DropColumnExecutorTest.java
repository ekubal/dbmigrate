package dbmigrate.executor;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.ITable;
import dbmigrate.model.db.Table;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.DropColumnOperationDescriptor;

import junit.framework.TestCase;

public class DropColumnExecutorTest extends TestCase{
	
	public void testCreateSql() {
		ITable table = new Table();
		
		table.setName("ala");
		
		List<IColumn> columns= new LinkedList<IColumn>();
		
		IColumn column = new Column();
		column.setLength(255);
		column.setType(TypeEnum.VARCHAR);
		column.setName("ola_varchar_255");
		column.setNullable(false);
		columns.add(column);
		table.setColumns(columns);
		
		DropColumnOperationDescriptor operation = new DropColumnOperationDescriptor(table, column);
		Connection connection =null;
		DropColumnExecutor executor = new DropColumnExecutor(connection );
		assertEquals("ALTER TABLE \"ala\" DROP ola_varchar_255",executor.createSql(operation));
		
	}

}

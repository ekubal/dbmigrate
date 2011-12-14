package dbmigrate.executor;

import java.util.ArrayList;
import java.util.List;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.AddColumnOperationDescriptor;
import dbmigrate.model.operation.MergeColumnOperationDescriptor;

import junit.framework.TestCase;

public class MergeColumnExecutorTest extends TestCase {
	private MergeColumnOperationDescriptor operation;
        private AddColumnOperationDescriptor addOperation;

	private static final String DEFAULT_TEXT = "ala123";
	private static final String DEFAULT_INT = "123";
	private static final String DEFAULT_BYTE = "1010101010";
	private static final String DEFAULT_BOOLEAN = "TRUE";
	private static final String DEFAULT_DATE = "2011-12-01 18:17:03";

	public void setUp() {
		String tableName = "ala";
		List<IColumn> columns = new ArrayList<IColumn>();
                
		Column column1 = new Column();
		column1.setLength(127);
		column1.setName("ola1");
		column1.setNullable(false);
		columns.add(column1);
                
                Column column2 = new Column();
		column2.setLength(127);
		column2.setName("ola2");
		column2.setNullable(false);
		columns.add(column2);
                
                Column column = new Column();
		column.setLength(255);
		column.setName("ola");
		column.setNullable(false);
                
                addOperation = new AddColumnOperationDescriptor(tableName, column);
		operation = new MergeColumnOperationDescriptor(column1, column2, tableName, ",", addOperation);
	}

	public void tuneColumn(TypeEnum type, String defaultValue) {
		addOperation.getColumn().setDefault(defaultValue);
		addOperation.getColumn().setType(type);
	}

	public void testCreateSqlWithDefaultText() {
		tuneColumn(TypeEnum.TEXT, DEFAULT_TEXT);
		MergeColumnExecutor executor = new MergeColumnExecutor(null);
                String sql = executor.createSql(operation).trim();
		System.out.println(sql);
		assertEquals("UPDATE \"ala\" SET ala = ola1 || ',' || ola2", sql);
	}

	
}

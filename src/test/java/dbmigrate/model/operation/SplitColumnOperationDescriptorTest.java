package dbmigrate.model.operation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;


public class SplitColumnOperationDescriptorTest {
	
	SplitColumnOperationDescriptor descriptor;
	
	@Before
	public void setUp(){
		String tableName = "ala";
		List<IColumn> columns = new ArrayList<IColumn>();
		Column column = new Column();
		column.setLength(255);
		column.setName("ola");
		column.setNullable(false);
		columns.add(column);
		
		Column column1 = new Column();
		column1.setLength(255);
		column1.setName("column1");
		column1.setNullable(true);
		
		Column column2 = new Column();
		column2.setLength(255);
		column2.setName("column2");
		column2.setNullable(true);
		
		AddColumnOperationDescriptor newColumnDescriptor1 = new AddColumnOperationDescriptor(tableName, column1);
		AddColumnOperationDescriptor newColumnDescriptor2 = new AddColumnOperationDescriptor(tableName, column2);
		descriptor = new SplitColumnOperationDescriptor(column, "ala", ".a.*", newColumnDescriptor1, newColumnDescriptor2);
	}

	@Test
	public void testSplitColumnOperationDescriptor() {
		
		assertNotNull(descriptor);
	}

	@Test
	public void testGetColumn() {
		assertEquals("ola", descriptor.getColumn().getName());
	}

	@Test
	public void testSetColumn() {
		Column column = new Column();
		column.setLength(255);
		column.setName("ola");
		column.setNullable(false);
		descriptor.setColumn(column);
		assertNotNull(descriptor.getColumn());
	}

	@Test
	public void testGetTableName() {
		assertEquals("ala", descriptor.getTableName());
	}

	@Test
	public void testSetTableName() {
		descriptor.setTableName("ula");
		assertEquals("ula", descriptor.getTableName());
	}

	@Test
	public void testGetRegexp() {
		assertNotNull(descriptor.getRegexp());
	}

	@Test
	public void testGetNewColumnDescriptor1() {
		assertNotNull(descriptor.getNewColumnDescriptor1());
	}

	@Test
	public void testGetNewColumnDescriptor2() {
		assertNotNull(descriptor.getNewColumnDescriptor2());
	}

	

}

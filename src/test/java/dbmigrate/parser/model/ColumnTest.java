package dbmigrate.parser.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColumnTest {
	
	Column column;
	final static String name= "column_name";

	@Test
	public void testGetName() {
		column = new Column();
		column.setName(name);
		assertEquals(name, column.getName());
	}

	@Test
	public void testGetType() {
		column = new Column();
		column.setType(name);
		assertEquals(name, column.getType());
	}

	@Test
	public void testGetLength() {
		column = new Column();
		column.setLength(new Long(10));
		assertEquals(new Long(10), column.getLength());
	}

	@Test
	public void testGetNotnull() {
		column = new Column();
		column.setNotnull(true);
		assertEquals(true, column.getNotnull());
	}

	
	@Test
	public void testGetDefaultValue() {
		column = new Column();
		column.setDefaultValue("default");
		assertEquals("default", column.getDefaultValue());
	}


	@Test
	public void testGetSigned() {
		column = new Column();
		column.setSigned(true);
		assertEquals(true, column.getSigned());
	}

	

}

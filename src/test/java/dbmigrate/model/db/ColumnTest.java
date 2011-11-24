package dbmigrate.model.db;

import junit.framework.TestCase;

public class ColumnTest extends TestCase {
	public ColumnTest(String testName) {
		super(testName);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetAndSetName() {
		Column instance = new Column();
		instance.setName("foo");
		assertEquals("foo", instance.getName());
		instance.setName("bar");
		assertEquals("bar", instance.getName());
	}
	
	public void testGetAndSetLength() {
		IColumn instance = new Column();
		instance.setLength(15);
		assertEquals(15, instance.getLength());
		instance.setLength(7);
		assertEquals(7, instance.getLength());
	}
	
	public void testGetAndSetNullable(){
		IColumn instance = new Column();
		instance.setNullable(true);
		assertTrue(instance.getNullable());
	}
	
	public void testClumnDescriptionVarchar(){
		IColumn instance = new Column();
		instance.setLength(17);
		instance.setName("nazwa_kolumny");
		instance.setNullable(false);
		instance.setType(TypeEnum.VARCHAR);
		assertEquals("nazwa_kolumny VARCHAR (17) NOT NULL", instance.getSqlDescription());
	}
	
	public void testClumnDescriptionDate(){
		IColumn instance = new Column();
		instance.setName("nazwa_kolumny");
		instance.setNullable(false);
		instance.setType(TypeEnum.DATE);
		assertEquals("nazwa_kolumny DATE NOT NULL", instance.getSqlDescription());
	}
	
	public void testClumnDescriptionDouble(){
		IColumn instance = new Column();
		instance.setName("nazwa_kolumny");
		instance.setNullable(true);
		instance.setType(TypeEnum.DOUBLE);
		assertEquals("nazwa_kolumny DOUBLE NULL", instance.getSqlDescription());
	}
	
	public void testClumnDescriptionInt(){
		IColumn instance = new Column();
		instance.setLength(4);
		instance.setName("nazwa_kolumny");
		instance.setNullable(false);
		instance.setType(TypeEnum.INT);
		assertEquals("nazwa_kolumny INT NOT NULL", instance.getSqlDescription());
	}
}

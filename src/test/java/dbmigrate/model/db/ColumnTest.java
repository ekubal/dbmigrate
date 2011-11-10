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
}

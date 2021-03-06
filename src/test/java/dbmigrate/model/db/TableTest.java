package dbmigrate.model.db;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

/**
 *
 * @author zyxist
 */
public class TableTest extends TestCase {
	
	public TableTest(String testName){
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
		Table t = new Table();
		t.setName("foo");
		
		this.assertEquals("foo", t.getName());
		
		t.setName("bar");
		this.assertEquals("bar", t.getName());
	}
	
	public void testGetAndSetColumns() {
		Table t = new Table();
		
		List<IColumn> ll = new LinkedList<IColumn>();
		t.setColumns(ll);
		this.assertSame(ll, t.getColumns());
	}
}

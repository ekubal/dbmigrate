package dbmigrate.parser.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DestinationColumnTest {

	DestinationColumn dc;
	final static String test = "TEST";
	@Test
	public void testGetMergeExpression() {
		dc = new DestinationColumn();
		dc.setMergeExpression(test);
		assertEquals(dc.getMergeExpression(), test);
	}

	@Test
	public void testGetCreateColumn() {
		dc = new DestinationColumn();
		CreateColumn c = new CreateColumn();
		dc.setCreateColumn(c);
		assertEquals(dc.getCreateColumn(), c);
	}

	@Test
	public void testGetSplittedIndex() {
		dc = new DestinationColumn();
		dc.setSplittedIndex(1);
		assertEquals((int)dc.getSplittedIndex(), 1);
	}

}

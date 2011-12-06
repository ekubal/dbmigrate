package dbmigrate.parser.model;

import junit.framework.TestCase;

public class CreateColumnTest extends TestCase {

	private static final String TEST_STRING = "123";

	private CreateColumn cc = null;

	public void setUp() {
		this.cc = new CreateColumn();
	}

	public void testId() {
		this.cc.setId(TEST_STRING);
		assertEquals(this.cc.getId(), TEST_STRING);
	}

	public void testTable() {
		this.cc.setTable(TEST_STRING);
		assertEquals(this.cc.getTable(), TEST_STRING);
	}

	public void testName() {
		this.cc.setName(TEST_STRING);
		assertEquals(this.cc.getName(), TEST_STRING);
	}

	public void testType() {
		this.cc.setType(TEST_STRING);
		assertEquals(this.cc.getType(), TEST_STRING);
	}

	public void testLength() {
		this.cc.setLength((long) TEST_STRING.length());
		assertEquals((long) this.cc.getLength(), (long) TEST_STRING.length());
	}

	public void testNotnull() {
		this.cc.setNotnull(true);
		assertEquals((boolean) this.cc.getNotnull(), true);
	}

	public void testDefaultValue() {
		this.cc.setDefaultValue(TEST_STRING);
		assertEquals(this.cc.getDefaultValue(), TEST_STRING);
	}

	public void testValueToSet() {
		this.cc.setValueToSet(TEST_STRING);
		assertEquals(this.cc.getValueToSet(), TEST_STRING);
	}

	public void testSigned() {
		this.cc.setSigned(true);
		assertEquals((boolean) this.cc.getSigned(), true);
	}

}

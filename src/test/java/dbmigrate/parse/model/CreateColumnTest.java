package dbmigrate.parse.model;

import junit.framework.TestCase;
import dbmigrate.parser.model.CreateColumn;

public class CreateColumnTest extends TestCase {

    private static final String TEST_STRING = "123";

    private CreateColumn cc = null;

    public void setUp() {
	cc = new CreateColumn();
    }

    public void testId() {
	cc.setId(TEST_STRING);
	assertEquals(cc.getId(), TEST_STRING);
    }

    public void testTable() {
	cc.setTable(TEST_STRING);
	assertEquals(cc.getTable(), TEST_STRING);
    }

    public void testName() {
	cc.setName(TEST_STRING);
	assertEquals(cc.getName(), TEST_STRING);
    }

    public void testType() {
	cc.setType(TEST_STRING);
	assertEquals(cc.getType(), TEST_STRING);
    }

    public void testLength() {
	cc.setLength((long) TEST_STRING.length());
	assertEquals((long) cc.getLength(), (long) TEST_STRING.length());
    }

    public void testNotnull() {
	cc.setNotnull(true);
	assertEquals((boolean) cc.getNotnull(), true);
    }

    public void testDefaultValue() {
	cc.setDefaultValue(TEST_STRING);
	assertEquals(cc.getDefaultValue(), TEST_STRING);
    }

    public void testValueToSet() {
	cc.setValueToSet(TEST_STRING);
	assertEquals(cc.getValueToSet(), TEST_STRING);
    }

    public void testSigned() {
	cc.setSigned(true);
	assertEquals((boolean) cc.getSigned(), true);
    }

}

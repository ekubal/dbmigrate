package dbmigrate.parser;

import junit.framework.TestCase;
import dbmigrate.parser.model.CreateColumn;
import dbmigrate.parser.model.Migration;

public class CreateColumnParseTest extends TestCase {

	public void testCreateColumn() throws Exception {
		final String fname = "<migration version=\"testedit\"> " + "<do>"
			+ "<create-column id=\"first1\">" + "<table>users</table>"
			+ "<name>kolumna56</name>" + "<type>integer</type>"
			+ "<notnull>true</notnull>" + "<default>53</default>"
			+ "<values>-1</values>" + "</create-column>" + "</do>"
			+ "</migration>";
		Migration migration = MigrationParser.loadMigration(fname);
		assertEquals(migration.getDoList().get(0).getClass(),
			new CreateColumn().getClass());
		assertEquals(
			((CreateColumn) migration.getDoList().get(0)).getValueToSet(),
			"-1");
	}
}

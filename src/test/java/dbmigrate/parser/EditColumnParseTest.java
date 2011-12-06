package dbmigrate.parser;

import junit.framework.TestCase;
import dbmigrate.parser.model.EditColumn;
import dbmigrate.parser.model.Migration;

public class EditColumnParseTest extends TestCase {

    public void testEditColumn() throws Exception {
	final String fname = "<migration version=\"testedit\"> " + "<do>"
		+ "<edit-column id=\"first1\">" + "<table>users</table>"
		+ "<oldColumnName>old</oldColumnName>"
		+ "<newColumnName>new</newColumnName>" + "</edit-column>"
		+ "</do>" + "<undo>" + "<edit-column id=\"first1\">"
		+ "<table>users</table>" + "<oldColumnName>new</oldColumnName>"
		+ "<newColumnName>old</newColumnName>" + "</edit-column>"
		+ "</undo>" + "</migration>";
	final String testNewName = "new";
	final String testOldName = "old";
	final String tableName = "users";
	Migration migration = MigrationParser.loadMigration(fname);
	assertEquals(migration.getDoList().get(0).getClass(),
		new EditColumn().getClass());
	assertEquals(
		((EditColumn) migration.getDoList().get(0)).getNewColumnName(),
		testNewName);
	assertEquals(
		((EditColumn) migration.getDoList().get(0)).getOldColumnName(),
		testOldName);
	assertEquals(((EditColumn) migration.getDoList().get(0)).getTable(),
		tableName);
    }

}
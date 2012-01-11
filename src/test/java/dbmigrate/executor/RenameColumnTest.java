package dbmigrate.executor;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Random;

import junit.framework.TestCase;

import org.postgresql.util.PSQLException;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.ITable;
import dbmigrate.model.db.Table;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.AddColumnOperationDescriptor;
import dbmigrate.model.operation.DropColumnOperationDescriptor;
import dbmigrate.model.operation.DropTableOperationDescriptor;
import dbmigrate.model.operation.RenameColumnOperationDescriptor;

public class RenameColumnTest extends TestCase {
	private Connection dbcon = null;
	private String sampleTable = "sampleTable";
	private IColumn oldCol;
	private IColumn newCol;
	private ITable table;

	private String getColName() {
		Random rnd = new Random();
		int randNum = rnd.nextInt(999999);
		String colname = "sampleCol" + randNum;
		return colname;
	}

	@Override
	public void setUp() {
		try {
			dbcon = DbConnector.instance().getConnection("postgresql",
					"149.156.205.250:13833", "dbmigrate", "dbmigrate", "dbmigrate");
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
		String oldName = getColName();
		String newName = oldName + "new";

		oldCol = new Column();
		oldCol.setName(oldName);
		oldCol.setType(TypeEnum.INT);

		newCol = new Column();
		newCol.setName(newName);
		newCol.setType(TypeEnum.INT);

		table = new Table();
		table.setName(sampleTable);

		table.setColumns(Arrays.asList(new IColumn[] { oldCol }));

		AddColumnOperationDescriptor aco = new AddColumnOperationDescriptor(
				sampleTable, oldCol);
		AddColumnExecutor ace = new AddColumnExecutor(dbcon);
		System.out.println(ace.createSql(aco));
		try {
			ace.execute(aco);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	public void testRenameColumn() {

		RenameColumnOperationDescriptor rdesc = new RenameColumnOperationDescriptor(
				table, oldCol, newCol);
		RenameColumnExecutor rexec = new RenameColumnExecutor(dbcon);

		System.out.println(rexec.createSql(rdesc));
		try {
			rexec.execute(rdesc);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	public void tearDown() {
		DropColumnOperationDescriptor dco = new DropColumnOperationDescriptor(
				table, newCol);
		DropColumnExecutor dce = new DropColumnExecutor(dbcon);

		System.out.println(dce.createSql(dco));
		try {
			dce.execute(dco);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}

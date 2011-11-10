package dbmigrate.executor;

import java.sql.Connection;
import java.util.Random;

import junit.framework.TestCase;

import org.postgresql.util.PSQLException;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.AddColumnOperationDescriptor;

public class AddColumnTest extends TestCase {
	private Connection dbcon = null;
	private String sampleTable = "sampleTable";
	
	@Override
	public void setUp() {
		dbcon = new DbConnector().getConnection("postgresql", "149.156.205.250:13833", "dbmigrate", "dbmigrate", "dbmigrate");
	}
	
	private String getColName() {
		Random rnd = new Random();
		int randNum = rnd.nextInt(9999);
		String colname = "sampleCol" + randNum;
		return colname;
	}
	
	
	
	public void testAddIntColumn() {
		String colname = getColName();
		Column col = new Column();
		col.setName(colname);
		col.setType(TypeEnum.INT);
		//col.setSigned(false);
		AddColumnOperationDescriptor aco = new AddColumnOperationDescriptor(sampleTable, col);
		AddColumnExecutor ace = new AddColumnExecutor(dbcon);
		System.out.println(ace.createSql(aco));
		try {
			ace.execute(aco);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testAddVarcharColumn() {
		String colname = getColName();
		Column col = new Column();
		col.setName(colname);
		col.setType(TypeEnum.VARCHAR);
		col.setLength(231);
		AddColumnOperationDescriptor aco = new AddColumnOperationDescriptor(sampleTable, col);
		AddColumnExecutor ace = new AddColumnExecutor(dbcon);
		System.out.println(ace.createSql(aco));
		try {
			ace.execute(aco);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}

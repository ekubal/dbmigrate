package dbmigrate.executor;

import java.sql.Connection;
import java.util.Random;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.db.Table;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.AddColumnOperationDescriptor;
import dbmigrate.model.operation.DropColumnOperationDescriptor;

import junit.framework.TestCase;

public class AddDropColumnTest extends TestCase {
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
	
	public void testAddDropIntDefaultColumn() {
		String colname = getColName();
		Column col = new Column();
		col.setName(colname);
		col.setType(TypeEnum.INT);
		col.setDefault("5");
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
		Table t=new Table();
		t.setName(sampleTable);
		DropColumnOperationDescriptor dco = new DropColumnOperationDescriptor(t, col);
		DropColumnExecutor dce = new DropColumnExecutor(dbcon);
		
		System.out.println(dce.createSql(dco));
		try {
			dce.execute(dco);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
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
		Table tbl = new Table();
		tbl.setName(sampleTable);
		
		DropColumnOperationDescriptor dco = new DropColumnOperationDescriptor(tbl, col);
		DropColumnExecutor dce = new DropColumnExecutor(dbcon);
		
		System.out.println(dce.createSql(dco));
		try {
			dce.execute(dco);
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
		Table tbl = new Table();
		tbl.setName(sampleTable);
		
		DropColumnOperationDescriptor dco = new DropColumnOperationDescriptor(tbl, col);
		DropColumnExecutor dce = new DropColumnExecutor(dbcon);
		
		System.out.println(dce.createSql(dco));
		try {
			dce.execute(dco);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testAddDropIntColumn() {
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
		
		Table tbl = new Table();
		tbl.setName(sampleTable);
		
		
		
		DropColumnOperationDescriptor dco = new DropColumnOperationDescriptor(tbl, col);
		DropColumnExecutor dce = new DropColumnExecutor(dbcon);
		
		System.out.println(dce.createSql(dco));
		try {
			dce.execute(dco);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}

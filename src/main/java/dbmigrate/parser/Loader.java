package dbmigrate.parser;

import java.io.File;
import java.util.ArrayList;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.Table;
import dbmigrate.model.operation.CreateColumnOperationDescriptor;
import dbmigrate.model.operation.CreateTableOperationDescriptor;
import dbmigrate.model.operation.DropColumnOperationDescriptor;
import dbmigrate.model.operation.DropTableOperationDescriptor;
import dbmigrate.model.operation.IOperationDescriptor;
import dbmigrate.model.operation.MigrationConfiguration;
import dbmigrate.parser.model.CreateTable;
import dbmigrate.parser.model.IOperation;
import dbmigrate.parser.model.Migration;
import dbmigrate.parser.model.RemoveColumn;
import dbmigrate.parser.model.RemoveTable;

public class Loader {
	public static MigrationConfiguration load(File file)
	{
		Migration m = MigrationParser.loadMigration(file);
		return map(m);
	}
	
	public static MigrationConfiguration map(Migration m)
	{
		MigrationConfiguration mc = new MigrationConfiguration();

		for(IOperation op : m.getDoList())
		{
			IOperationDescriptor d=null;
			if(op instanceof RemoveColumn)
			{
				RemoveColumn rc=(RemoveColumn)op;
				Table t=new Table();
				t.setName(rc.getTable());
				Column c=new Column();
				c.setName(rc.getName());
				d = new DropColumnOperationDescriptor(t,c);
			}
			else if(op instanceof RemoveTable)
			{
				RemoveTable rt=(RemoveTable)op;
				Table t=new Table();
				t.setName(rt.getName());
				d = new DropTableOperationDescriptor(t);
			}
			else if(op instanceof CreateTable)
			{
				CreateTable ct=(CreateTable)op;
				Table t=new Table();
				t.setName(ct.getName());
				ArrayList<IColumn> columns = new ArrayList<IColumn>();
				for(dbmigrate.parser.model.Column c : ct.getColumns())
				{
					Column cc = new Column();
					cc.setLength((int)(long)c.getLength());
					cc.setName(c.getName());
					cc.setNullable(c.getNotnull());

				}
				t.setColumns(columns);
				d = new CreateTableOperationDescriptor(t);
			}
			mc.addOperation(d);
			
		}
		return mc;
	}
}

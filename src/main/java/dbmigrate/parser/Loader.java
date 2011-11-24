package dbmigrate.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.Table;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.AddColumnOperationDescriptor;
import dbmigrate.model.operation.CreateTableOperationDescriptor;
import dbmigrate.model.operation.DropColumnOperationDescriptor;
import dbmigrate.model.operation.DropTableOperationDescriptor;
import dbmigrate.model.operation.IOperationDescriptor;
import dbmigrate.model.operation.MigrationConfiguration;
import dbmigrate.parser.model.CreateColumn;
import dbmigrate.parser.model.CreateTable;
import dbmigrate.parser.model.IOperation;
import dbmigrate.parser.model.Migration;
import dbmigrate.parser.model.RemoveColumn;
import dbmigrate.parser.model.RemoveTable;

public class Loader {
	public static MigrationConfiguration load(File file) throws Exception {
		Migration m = MigrationParser.loadMigration(file);
		return map(m);
	}

	public static MigrationConfiguration map(Migration m) throws Exception {
		MigrationConfiguration mc = new MigrationConfiguration();

		for (IOperation op : m.getDoList()) {
			IOperationDescriptor d = null;
			if (op instanceof RemoveColumn) {
				RemoveColumn rc = (RemoveColumn) op;
				Table t = new Table();
				t.setName(rc.getTable());
				Column c = new Column();
				c.setName(rc.getName());
				d = new DropColumnOperationDescriptor(t, c);
			} else if (op instanceof RemoveTable) {
				RemoveTable rt = (RemoveTable) op;
				Table t = new Table();
				t.setName(rt.getName());
				d = new DropTableOperationDescriptor(t);
			} else if (op instanceof CreateTable) {
				CreateTable ct = (CreateTable) op;
				Table t = new Table();
				t.setName(ct.getName());
				ArrayList<IColumn> columns = new ArrayList<IColumn>();
				List<dbmigrate.parser.model.Column> col = ct.getColumns();
				if (col != null)
					for (dbmigrate.parser.model.Column c : col) {
						Column cc = new Column();
						System.out.println(c.getLength());
						cc.setLength((int) (long) c.getLength());
						cc.setName(c.getName());
						cc.setNullable(c.getNotnull());
						cc.setType(getType(c.getType()));
						cc.setLength((int) (long) c.getLength());
						cc.setNullable(!c.getNotnull());
						cc.setSigned(c.getSigned());
						cc.setDefault(c.getDefaultValue());
						columns.add(cc);
					}
				t.setColumns(columns);
				d = new CreateTableOperationDescriptor(t);
			} else if (op instanceof CreateColumn) {
				CreateColumn c = (CreateColumn) op;
				Table t = new Table();
				t.setName(c.getTable());
				Column cc = new Column();
				if (c.getLength() != null)
					cc.setLength((int) (long) c.getLength());
				cc.setName(c.getName());
				// cc.setNullable(c.getNotnull());
				cc.setType(getType(c.getType()));
				// cc.setLength((int)(long)c.getLength());
				cc.setNullable(!c.getNotnull());
				cc.setSigned(c.getSigned());
				cc.setDefault(c.getDefaultValue());

				d = new AddColumnOperationDescriptor(t.getName(), cc);
			} else
				throw new Exception("Nieznana operacja: "
						+ op.getClass().getName());
			mc.addOperation(d);
		}
		return mc;
	}

	private static TypeEnum getType(String type) throws Exception {
		if (type.equals("boolean"))
			return TypeEnum.BOOLEAN;
		else if (type.equals("short"))
			return TypeEnum.SHORTINT;
		else if (type.equals("int"))
			return TypeEnum.INT;
		else if (type.equals("bigint"))
			return TypeEnum.BIGINT;
		else if (type.equals("varchar"))
			return TypeEnum.VARCHAR;
		else if (type.equals("char"))
			return TypeEnum.CHAR;
		else if (type.equals("text"))
			return TypeEnum.TEXT;
		else if (type.equals("double"))
			return TypeEnum.DOUBLE;
		else if (type.equals("binary"))
			return TypeEnum.BINARY;
		else
			throw new Exception("Nieznany typ danych: " + type);
	}
}

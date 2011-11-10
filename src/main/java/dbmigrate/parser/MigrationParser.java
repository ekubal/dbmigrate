package dbmigrate.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.thoughtworks.xstream.XStream;

import dbmigrate.parser.model.Column;
import dbmigrate.parser.model.CreateColumn;
import dbmigrate.parser.model.CreateTable;
import dbmigrate.parser.model.Migration;
import dbmigrate.parser.model.RemoveColumn;
import dbmigrate.parser.model.RemoveTable;

public class MigrationParser {

	private final static XStream XSTREAM;
	
	static {
		XSTREAM = new XStream();
        
		XSTREAM.processAnnotations(Column.class);
		XSTREAM.processAnnotations(CreateColumn.class);
		XSTREAM.processAnnotations(CreateTable.class);
		XSTREAM.processAnnotations(Migration.class);
		XSTREAM.processAnnotations(RemoveColumn.class);
		XSTREAM.processAnnotations(RemoveTable.class);
	}
	
	public static Migration loadMigration(String filePath) throws FileNotFoundException {
		return (Migration) XSTREAM.fromXML(new FileReader(filePath));
	}
	
}

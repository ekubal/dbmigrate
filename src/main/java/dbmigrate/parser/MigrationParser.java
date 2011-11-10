package dbmigrate.parser;

import java.io.File;
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
	
	private MigrationParser(){
	}
	
	public static Migration loadMigration(File file) {
		return (Migration) XSTREAM.fromXML(file);
	}
	
	public static Migration loadMigration(FileReader fileReader) {
		return (Migration) XSTREAM.fromXML(fileReader);
	}
	
}

package dbmigrate.parser;

import java.io.File;
import java.io.FileReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;

import dbmigrate.logging.Level;
import dbmigrate.logging.LoggerFactory;
import dbmigrate.parser.model.Column;
import dbmigrate.parser.model.CreateColumn;
import dbmigrate.parser.model.CreateTable;
import dbmigrate.parser.model.DestinationColumn;
import dbmigrate.parser.model.EditColumn;
import dbmigrate.parser.model.MergeColumns;
import dbmigrate.parser.model.Migration;
import dbmigrate.parser.model.RemoveColumn;
import dbmigrate.parser.model.RemoveTable;
import dbmigrate.parser.model.SourceColumn;
import dbmigrate.parser.model.SplitColumn;

public class MigrationParser {

	private final static XStream XSTREAM = new XStream();

	static {
		MigrationParser.XSTREAM.processAnnotations(Column.class);
		MigrationParser.XSTREAM.processAnnotations(CreateColumn.class);
		MigrationParser.XSTREAM.processAnnotations(CreateTable.class);
		MigrationParser.XSTREAM.processAnnotations(DestinationColumn.class);
		MigrationParser.XSTREAM.processAnnotations(EditColumn.class);
		MigrationParser.XSTREAM.processAnnotations(MergeColumns.class);
		MigrationParser.XSTREAM.processAnnotations(Migration.class);
		MigrationParser.XSTREAM.processAnnotations(RemoveColumn.class);
		MigrationParser.XSTREAM.processAnnotations(RemoveTable.class);
		MigrationParser.XSTREAM.processAnnotations(SourceColumn.class);
		MigrationParser.XSTREAM.processAnnotations(SplitColumn.class);
	}

	private MigrationParser() {
	}

	public static Migration loadMigration(File file, boolean performValidation) throws Exception {
		if(performValidation) {
			XmlValidator.validate(file);
		}
		try {
			return (Migration) MigrationParser.XSTREAM.fromXML(file);
		} catch (ConversionException e) {
			LoggerFactory.getLogger().log(e.getShortMessage(), Level.Error);
			throw e;
		} catch (Exception e) {
			LoggerFactory.getLogger().log(e.getMessage(), Level.Error);
			throw e;
		}
	}

	public static Migration loadMigration(FileReader fileReader)
			throws Exception {
		XmlValidator.validate(fileReader);
		try {
			return (Migration) MigrationParser.XSTREAM.fromXML(fileReader);
		} catch (ConversionException e) {
			LoggerFactory.getLogger().log(e.getShortMessage(), Level.Error);
			throw e;
		} catch (Exception e) {
			LoggerFactory.getLogger().log(e.getMessage(), Level.Error);
			throw e;
		}
	}

	public static Migration loadMigration(String xmlFromString) {
		return (Migration) MigrationParser.XSTREAM.fromXML(xmlFromString);
	}
}

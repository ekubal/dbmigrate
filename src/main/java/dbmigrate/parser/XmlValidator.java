package dbmigrate.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import dbmigrate.exceptions.ParseException;
import dbmigrate.logging.Level;
import dbmigrate.logging.LoggerFactory;

public class XmlValidator {
	public static boolean validate(File file, String xsdPath) throws ParseException {
		return validateSchema(new StreamSource(file), xsdPath);
	}

	public static boolean validate(FileReader file, String xsdPath) throws ParseException {
		return validateSchema(new StreamSource(file), xsdPath);
	}
	
	public static boolean validate(File file) throws ParseException {
		return validateSchema(new StreamSource(file));
	}

	public static boolean validate(FileReader file) throws ParseException {
		return validateSchema(new StreamSource(file));
	}

	private static boolean validateSchema(Source source, String xsdPath) throws ParseException {
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");

			File schemaLocation = new File(xsdPath);
			Schema schema;
			schema = factory.newSchema(schemaLocation);

			Validator validator = schema.newValidator();

			validator.validate(source);
			return true;
		} catch (IOException e) {
			LoggerFactory.getLogger().log(e.getMessage(), Level.Error);
			throw new ParseException();
		} catch (SAXException e) {
			LoggerFactory.getLogger().log(e.getMessage(), Level.Error);
			throw new ParseException();
		}
	}
	
	private static boolean validateSchema(Source source) throws ParseException {
		return validateSchema(source, "res/dbmigrate.xsd");
	}
}

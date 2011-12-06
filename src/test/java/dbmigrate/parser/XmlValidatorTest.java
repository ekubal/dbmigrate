package dbmigrate.parser;

import java.io.File;

import dbmigrate.exceptions.ParseException;

import junit.framework.TestCase;

public class XmlValidatorTest extends TestCase {


	public void testIfValidationFailsWithWrongXml () {
		boolean validateResult = false;
		boolean exceptionThrown = false;
		
		try {
			validateResult = XmlValidator.validate(new File("test_res/wrongMigration.xml"), "test_res/correctSchema.xsd");
		} catch(ParseException e) {
			exceptionThrown = true;
		}
		
		this.assertTrue(!validateResult || exceptionThrown);
	}
	
	public void testIfValidationPassesWithCorrectMigration () throws ParseException {
		this.assertTrue(XmlValidator.validate(new File("test_res/correctMigration.xml"), "test_res/correctSchema.xsd"));
	}
	
}

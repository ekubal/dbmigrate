package dbmigrate.parser;

import dbmigrate.model.operation.MigrationConfiguration;
import dbmigrate.parser.model.Migration;
import dbmigrate.parser.model.RemoveColumn;
import junit.framework.TestCase;

public class LoaderTest  extends TestCase{
	
	public void testMapping() throws Exception {
		Migration m=new Migration();
		RemoveColumn rm=new RemoveColumn();
		rm.setTable("TEST");
		rm.setName("TEST2|");
		
		m.getDoList().add(rm);
		Loader.map(m);
		
		
		
	}
	

}

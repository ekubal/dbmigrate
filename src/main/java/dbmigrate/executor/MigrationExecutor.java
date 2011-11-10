package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dbmigrate.model.operation.IOperationDescriptor;
import dbmigrate.model.operation.MigrationConfiguration;

public class MigrationExecutor {
	private Connection connection;
	private MigrationConfiguration migrationConfiguration;
	
	public MigrationExecutor(){}
	
	public MigrationExecutor(Connection connection, MigrationConfiguration migrationConfiguration) {
		super();
		this.connection = connection;
		this.migrationConfiguration = migrationConfiguration;
	}

	public void setConnection(Connection c){
		connection = c;
	}
	
	public void setMigrationConfiguration(MigrationConfiguration migrationConfiguration) {
		this.migrationConfiguration = migrationConfiguration;
	}

	public boolean executeMigration(){
		if(connection == null){
			throw new NullPointerException("You must set connection first. Right now it is NULL");
		}

		try {
			connection.setAutoCommit(false);
			List<IOperationDescriptor> operations = migrationConfiguration.getOperations();
			for(IOperationDescriptor operation : operations){
				//TODO
				//stworzyć executora
				//wsadzić do niego connection z ustawionym automatycznym commitem na false
			}
			
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}

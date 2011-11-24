package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;

import dbmigrate.model.operation.AddColumnOperationDescriptor;
import dbmigrate.model.operation.ChangeColumnOperationDescriptor;
import dbmigrate.model.operation.CreateTableOperationDescriptor;
import dbmigrate.model.operation.DropColumnOperationDescriptor;
import dbmigrate.model.operation.DropTableOperationDescriptor;
import dbmigrate.model.operation.IOperationDescriptor;
import dbmigrate.model.operation.MigrationConfiguration;
import dbmigrate.model.operation.ModifyColumnOperationDescriptor;
import dbmigrate.model.operation.RenameColumnOperationDescriptor;

public class ExecutorEngine {

	private Connection connection;
	private MigrationConfiguration migrationConfiguration;
	private boolean autoCommitEnable = true;

	public ExecutorEngine(Connection connection,
			MigrationConfiguration migrationConfiguration, boolean atomicity) {
		this.connection = connection;
		this.migrationConfiguration = migrationConfiguration;
		
		if(atomicity){
			try {
				connection.setAutoCommit(false);
				autoCommitEnable = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}

	public void executeMigration() throws SQLException{
		
		for(IOperationDescriptor operation : migrationConfiguration.getOperations()){		
			if(operation instanceof AddColumnOperationDescriptor){
				new AddColumnExecutor(connection).execute((AddColumnOperationDescriptor) operation);
			} else if(operation instanceof DropTableOperationDescriptor){
				new DropTableExecutor(connection).execute((DropTableOperationDescriptor) operation);
			} else if(operation instanceof DropColumnOperationDescriptor){
				new DropColumnExecutor(connection).execute((DropColumnOperationDescriptor) operation);
			} else if(operation instanceof CreateTableOperationDescriptor){
				new CreateTableExecutor(connection).execute((CreateTableOperationDescriptor) operation);
			} else if(operation instanceof RenameColumnOperationDescriptor){
				new RenameColumnExecutor(connection).execute((RenameColumnOperationDescriptor) operation);
			}
			
		}
		
		if(!autoCommitEnable){
			try {
				connection.commit();
				autoCommitEnable = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}

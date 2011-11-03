package dbmigrate.executor;

import java.sql.Connection;

import dbmigrate.model.operation.AddColumnOperationDescriptor;
import dbmigrate.model.operation.ChangeColumnOperationDescriptor;
import dbmigrate.model.operation.CreateTableOperationDescriptor;
import dbmigrate.model.operation.DropColumnOperationDescriptor;
import dbmigrate.model.operation.DropTableOperationDescriptor;
import dbmigrate.model.operation.IOperationDescriptor;
import dbmigrate.model.operation.MigrationConfiguration;
import dbmigrate.model.operation.ModifyColumnOperationDescriptor;

public class ExecutorEngine {

	private Connection connection;
	private MigrationConfiguration migrationConfiguration;

	public ExecutorEngine(Connection connection,
			MigrationConfiguration migrationConfiguration) {
		this.connection = connection;
		this.migrationConfiguration = migrationConfiguration;
	}

	public void executeMigration(){
		for(IOperationDescriptor operation : migrationConfiguration.getOperations()){
			if(operation instanceof AddColumnOperationDescriptor){
				new AddColumnExecutor(connection).execute((AddColumnOperationDescriptor) operation);
			} else if(operation instanceof ModifyColumnOperationDescriptor){
				new ModifyColumnExecutor(connection).execute((ModifyColumnOperationDescriptor) operation);
			} else if(operation instanceof DropTableOperationDescriptor){
				new DropTableExecutor(connection).execute((DropTableOperationDescriptor) operation);
			} else if(operation instanceof DropColumnOperationDescriptor){
				new DropColumnExecutor(connection).execute((DropColumnOperationDescriptor) operation);
			} else if(operation instanceof CreateTableOperationDescriptor){
				new CreateTableExecutor(connection).execute((CreateTableOperationDescriptor) operation);
			} else if(operation instanceof ChangeColumnOperationDescriptor){
				new ChangeColumnExecutor(connection).execute((ChangeColumnOperationDescriptor) operation);
			}
		}
	}
	
}

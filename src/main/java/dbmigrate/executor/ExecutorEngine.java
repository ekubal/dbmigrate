package dbmigrate.executor;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;

import dbmigrate.exceptions.ValidationException;
import dbmigrate.logging.ILogger;
import dbmigrate.logging.Level;
import dbmigrate.model.operation.IOperationDescriptor;
import dbmigrate.model.operation.MigrationConfiguration;

public class ExecutorEngine {

	private Connection connection;
	private MigrationConfiguration migrationConfiguration;
	private boolean autoCommitEnable = true;
	private ILogger logger;
	private boolean forwards = true;

	private Map<Class<? extends IOperationDescriptor>, Class<? extends IExecutor>> executors;

	public ExecutorEngine(Connection connection,
			MigrationConfiguration migrationConfiguration, boolean atomicity) {
		this.connection = connection;
		this.migrationConfiguration = migrationConfiguration;

		if (atomicity) {
			try {
				connection.setAutoCommit(false);
				this.autoCommitEnable = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.executors = new LinkedHashMap<Class<? extends IOperationDescriptor>, Class<? extends IExecutor>>();
	}
	
	public void setForwards(boolean forwards) {
		this.forwards = forwards;
	}
	
	public boolean getForwards() {
		return this.forwards;
	}

	public void registerExecutor(Class<? extends IOperationDescriptor> descriptorClass, Class<? extends IExecutor> executorClass) {
		this.executors.put(descriptorClass, executorClass);
	}

	// tak na prade wyjatku nie rzUca nigdzie -.-
	public boolean executeMigration() throws SQLException {
		boolean areErrors = false;
		boolean isSuccess = true;
		Map<IOperationDescriptor, IExecutor> localExecutors = new LinkedHashMap<IOperationDescriptor, IExecutor>();
		for (IOperationDescriptor operation : this.migrationConfiguration
				.getOperations(this.forwards)) {
			try {
				// You can register new executors in Application.java now.
				Class<? extends IExecutor> cls = this.executors.get(operation.getClass());
				Constructor<? extends IExecutor> constructor = cls.getConstructor(Connection.class);
				IExecutor executor = constructor.newInstance(this.connection);
				executor.validate(operation);
				
				localExecutors.put(operation, executor);
			} catch (ValidationException e) {
				areErrors = true;
				isSuccess = false;
				this.logger.log(e.getMessage(), Level.Error);
			} catch (Exception e) {
				areErrors = true;
				isSuccess = false;
				this.logger.log("Error in the executor definition: "+e.getMessage(), Level.Error);
			}
		}

		if (!areErrors) {
			try {
				for(Map.Entry<IOperationDescriptor, IExecutor> entry: localExecutors.entrySet()) {
					entry.getValue().execute(entry.getKey());
				}

				if (!this.autoCommitEnable) {
					try {
						this.logger.log("Committing changes...", Level.Info);
						this.connection.commit();
						this.logger.log("Transaction committed.", Level.Info);
						this.autoCommitEnable = true;
					} catch (SQLException e) {
						e.printStackTrace();
						isSuccess = false;
					}
				}
			} catch (SQLException e) {
				this.logger.log(e.getMessage(), Level.Error);
				
				if (!this.autoCommitEnable) {
					try {
						this.logger.log("Rolling back the transaction...", Level.Info);
						this.connection.rollback();
						this.logger.log("Transaction rolled back.", Level.Info);
						this.autoCommitEnable = true;
					} catch (SQLException ex) {
						this.logger.log("Cannot rollback the transaction, I'm sorry: "+ex.getMessage(), Level.Error);
						isSuccess = false;
					}
				}
			}
		}
		
		return isSuccess;
	}

	public void setLogger(ILogger logger) {
		this.logger = logger;
	}
}

package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;

import dbmigrate.exceptions.ValidationException;
import dbmigrate.logging.ILogger;
import dbmigrate.logging.Level;
import dbmigrate.model.operation.IOperationDescriptor;
import dbmigrate.model.operation.MigrationConfiguration;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExecutorEngine {

	private Connection connection;
	private MigrationConfiguration migrationConfiguration;
	private boolean autoCommitEnable = true;
	private ILogger logger;
	
	private Map<Class<? extends IOperationDescriptor>, Class<? extends IExecutor>> executors;

	public ExecutorEngine(Connection connection,
			MigrationConfiguration migrationConfiguration, boolean atomicity) {
		this.connection = connection;
		this.migrationConfiguration = migrationConfiguration;

		if (atomicity) {
			try {
				connection.setAutoCommit(false);
				autoCommitEnable = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.executors = new LinkedHashMap<Class<? extends IOperationDescriptor>, Class<? extends IExecutor>>();
	}
	
	public void registerExecutor(Class<? extends IOperationDescriptor> descriptorClass, Class<? extends IExecutor> executorClass) {
		this.executors.put(descriptorClass, executorClass);
	}

	public void executeMigration() throws SQLException {
		boolean areErrors = false;
		Map<IOperationDescriptor, IExecutor> localExecutors = new LinkedHashMap<IOperationDescriptor, IExecutor>();
		for (IOperationDescriptor operation : migrationConfiguration
				.getOperations()) {
			try {
				// You can register new executors in Application.java now.
				Class<? extends IExecutor> cls = this.executors.get(operation.getClass());
				Constructor<? extends IExecutor> constructor = cls.getConstructor(Connection.class);
				IExecutor executor = constructor.newInstance(this.connection);
				executor.validate(operation);
				
				localExecutors.put(operation, executor);
			} catch (ValidationException e) {
				areErrors = true;
				logger.log(e.getMessage(), Level.Error);
			} catch (Exception e) {
				areErrors = true;
				logger.log("Error in the executor definition: "+e.getMessage(), Level.Error);
			}
		}

		if (!areErrors) {
			try {
				for(Map.Entry<IOperationDescriptor, IExecutor> entry: localExecutors.entrySet()) {
					entry.getValue().execute(entry.getKey());
				}

				if (!autoCommitEnable) {
					try {
						logger.log("Committing changes...", Level.Info);
						connection.commit();
						logger.log("Transaction committed.", Level.Info);
						autoCommitEnable = true;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				logger.log(e.getMessage(), Level.Error);
				
				if (!autoCommitEnable) {
					try {
						logger.log("Rolling back the transaction...", Level.Info);
						connection.rollback();
						logger.log("Transaction rolled back.", Level.Info);
						autoCommitEnable = true;
					} catch (SQLException ex) {
						logger.log("Cannot rollback the transaction, I'm sorry: "+ex.getMessage(), Level.Error);
					}
				}
			}
		}
	}

	public void setLogger(ILogger logger) {
		this.logger = logger;
	}
}

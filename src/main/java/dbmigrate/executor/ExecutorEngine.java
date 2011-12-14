package dbmigrate.executor;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import dbmigrate.exceptions.HistoryException;
import dbmigrate.exceptions.ValidationException;
import dbmigrate.logging.HistoryStorage;
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
	private HistoryStorage storage;

	private Map<Class<? extends IOperationDescriptor>, Class<? extends IExecutor>> executors;

	public ExecutorEngine(Connection connection, MigrationConfiguration migrationConfiguration, boolean atomicity) {
		this.connection = connection;
		this.migrationConfiguration = migrationConfiguration;

		if(atomicity) {
			try {
				connection.setAutoCommit(false);
				this.autoCommitEnable = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.executors = new LinkedHashMap<Class<? extends IOperationDescriptor>, Class<? extends IExecutor>>();
	}
	
	public void setHistoryStorage(HistoryStorage storage) {
		this.storage = storage;
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

	public boolean executeMigration() throws HistoryException, SQLException {
		StringBuilder sb = new StringBuilder();
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
			this.logger.log("Executor validation completed.", Level.Info);
			try {
				for(Map.Entry<IOperationDescriptor, IExecutor> entry: localExecutors.entrySet()) {
					String className = this.classNamePrettifier(entry.getKey().getClass().toString());
					this.logger.log("Executing "+className, Level.Info);
					entry.getValue().execute(entry.getKey());
					sb.append(className).append("\n");
				}

				if (!this.autoCommitEnable) {
					try {
						this.logger.log("Committing changes...", Level.Info);
						this.connection.commit();
						this.logger.log("Transaction committed.", Level.Info);
						this.autoCommitEnable = true;
					} catch (SQLException e) {
						this.logger.log(e.getSQLState(), Level.Error);
						this.logger.log(e.getMessage(), Level.Error);
						isSuccess = false;
					}
				}
			} catch (SQLException e) {
				isSuccess = false;
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
		if(null != this.storage) {
			this.storage.store("0.0.0.0", this.migrationConfiguration.getMigrationId(),
				(new Date()).toString(), (this.forwards ? 0 : 1), sb.toString(), isSuccess);
		}
		return isSuccess;
	}
	
	private String classNamePrettifier(String className) {
		String[] cn = className.split("\\.");
		String newName = cn[cn.length - 1];
		String prettyName = newName.replace("Descriptor", "");
		return prettyName;
	}

	public void setLogger(ILogger logger) {
		this.logger = logger;
	}
	
}

package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;

import dbmigrate.exceptions.ValidationException;
import dbmigrate.logging.ILogger;
import dbmigrate.logging.Level;
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
	private boolean autoCommitEnable = true;
	private ILogger logger;

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
	}

	public void executeMigration() throws SQLException {
		boolean areErrors = false;

		for (IOperationDescriptor operation : migrationConfiguration
				.getOperations()) {
			try {
				if (operation instanceof AddColumnOperationDescriptor) {
					new AddColumnExecutor(connection)
							.validate((AddColumnOperationDescriptor) operation);
				} else if (operation instanceof ModifyColumnOperationDescriptor) {
					new ModifyColumnExecutor(connection)
							.validate((ModifyColumnOperationDescriptor) operation);
				} else if (operation instanceof DropTableOperationDescriptor) {
					new DropTableExecutor(connection)
							.validate((DropTableOperationDescriptor) operation);
				} else if (operation instanceof DropColumnOperationDescriptor) {
					new DropColumnExecutor(connection)
							.validate((DropColumnOperationDescriptor) operation);
				} else if (operation instanceof CreateTableOperationDescriptor) {
					new CreateTableExecutor(connection)
							.validate((CreateTableOperationDescriptor) operation);
				} else if (operation instanceof ChangeColumnOperationDescriptor) {
					new ChangeColumnExecutor(connection)
							.validate((ChangeColumnOperationDescriptor) operation);
				}
			} catch (ValidationException e) {
				areErrors = true;
				logger.log(e.getMessage(), Level.Error);
			}
		}

		if (!areErrors) {
			try {
				for (IOperationDescriptor operation : migrationConfiguration
						.getOperations()) {
					if (operation instanceof AddColumnOperationDescriptor) {
						new AddColumnExecutor(connection)
								.execute((AddColumnOperationDescriptor) operation);
					} else if (operation instanceof ModifyColumnOperationDescriptor) {
						new ModifyColumnExecutor(connection)
								.execute((ModifyColumnOperationDescriptor) operation);
					} else if (operation instanceof DropTableOperationDescriptor) {
						new DropTableExecutor(connection)
								.execute((DropTableOperationDescriptor) operation);
					} else if (operation instanceof DropColumnOperationDescriptor) {
						new DropColumnExecutor(connection)
								.execute((DropColumnOperationDescriptor) operation);
					} else if (operation instanceof CreateTableOperationDescriptor) {
						new CreateTableExecutor(connection)
								.execute((CreateTableOperationDescriptor) operation);
					} else if (operation instanceof ChangeColumnOperationDescriptor) {
						new ChangeColumnExecutor(connection)
								.execute((ChangeColumnOperationDescriptor) operation);
					}

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

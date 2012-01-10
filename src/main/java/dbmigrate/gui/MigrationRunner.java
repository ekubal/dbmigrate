package dbmigrate.gui;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dbmigrate.app.Application;
import dbmigrate.exceptions.ConnectException;
import dbmigrate.exceptions.HistoryException;
import dbmigrate.executor.ExecutorEngine;
import dbmigrate.logging.HistoryStorage;
import dbmigrate.logging.LoggerFactory;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.operation.MigrationConfiguration;

public class MigrationRunner extends Thread {

	private boolean forwards;
	private DbConnector dbConnector;
	private final MigrationConfiguration migrationConfiguration;
	private String completeMsg;
	private IMigrationListener listener;
	private HistoryStorage historyStorage;

	public MigrationRunner(boolean forwards, DbConnector dbConnector,
			MigrationConfiguration migrationConfiguration, String completeMsg,
			HistoryStorage storage, IMigrationListener listener) {
		this.forwards = forwards;
		this.dbConnector = dbConnector;
		this.migrationConfiguration = migrationConfiguration;
		this.completeMsg = completeMsg;
		this.historyStorage = storage;
		this.listener = listener;
	}

	@Override
	public void run() {
		synchronized (this.migrationConfiguration) {
			try {
				ExecutorEngine executorEngine = new ExecutorEngine(
						this.dbConnector.getConnection(),
						this.migrationConfiguration, true);

				Application.configureExecutorEngine(executorEngine,
						this.dbConnector.getDbType());
				executorEngine.setForwards(this.forwards);
				executorEngine.setLogger(LoggerFactory.getLogger());
				executorEngine.setHistoryStorage(this.historyStorage);

				try {
					Connection conn = this.dbConnector.getConnection();
					this.historyStorage.setConnection(conn);
					executorEngine.executeMigration();
					this.listener.refreshHistoryModel();
					this.listener.setStatusMessage(this.completeMsg);
				} catch (HistoryException exception) {
					this.listener
							.setStatusMessage("A problem occured while registering the migration in the history.");
				} catch (SQLException exception) {
					this.listener
							.setStatusMessage("Migration not registered in the database.");
					JOptionPane.showMessageDialog(null,
							"A database problem occured while registering the migration in the history: "
									+ exception.getMessage(),
							"Dbmigrate error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (ConnectException exception) {
				this.listener.handleConnectionProblem(exception);
			} finally {
				this.listener.unlockButtons();
			}
		}
	}

}

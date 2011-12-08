package dbmigrate.gui;

import java.sql.SQLException;

import javax.swing.JLabel;

import dbmigrate.app.Application;
import dbmigrate.executor.ExecutorEngine;
import dbmigrate.logging.LoggerFactory;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.operation.MigrationConfiguration;

public class MigrationRunner extends Thread {

	private boolean forwards;
	private DbConnector dbConnector;
	private MigrationConfiguration migrationConfiguration;
	private String completeMsg;
	private JLabel status;

	public MigrationRunner(boolean forwards, DbConnector dbConnector,
			MigrationConfiguration migrationConfiguration, String completeMsg,
			JLabel status) {
		this.forwards = forwards;
		this.dbConnector = dbConnector;
		this.migrationConfiguration = migrationConfiguration;
		this.completeMsg = completeMsg;
		this.status = status;
	}

	public void run() {
		ExecutorEngine executorEngine = new ExecutorEngine(
				dbConnector.getConnection(), migrationConfiguration, true);
		Application.configureExecutorEngine(executorEngine);
		executorEngine.setForwards(forwards);
		executorEngine.setLogger(LoggerFactory.getLogger());

		try {
			executorEngine.executeMigration();
			status.setText(completeMsg);
		} catch (SQLException ex) {
			status.setText(ex.getMessage());
		}
	}

}

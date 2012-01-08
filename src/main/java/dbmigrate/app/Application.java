package dbmigrate.app;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import dbmigrate.executor.ExecutorEngine;
import dbmigrate.gui.ApplicationFrame;
import dbmigrate.logging.HistoryStorage;
import dbmigrate.logging.LoggerFactory;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.operation.MigrationConfiguration;
import dbmigrate.parser.Loader;

public class Application {
	private final static int ARGS_LENGTH = 5;
	private static ApplicationFrame app;

	public static void configureExecutorEngine(ExecutorEngine executorEngine,
			int dbaseType) {
		if (dbaseType == DbConnector.POSTGRESQL_DB) {
			executorEngine.registerPostgresExecutors();
		} else if (dbaseType == DbConnector.ORACLE_DB) {
			executorEngine.registerOracleExecutors();
		}
	}

	public static void main(String[] args) {
		if (args.length < Application.ARGS_LENGTH) {
			java.awt.EventQueue.invokeLater(new Runnable() {

				public void run() {
					app = new ApplicationFrame();
					app.setVisible(true);

				}
			});
			return;
		}
		boolean performValidation = false;
		if (args.length > Application.ARGS_LENGTH) {
			for (String opt : args) {
				if ("--validate".equals(opt)) {
					performValidation = true;
				}
			}
		}

		try {
			DbConnector dbConnector = new DbConnector();

			Connection connection = dbConnector.getConnection(
					DbConnector.POSTGRESQL_DB, args[1], args[2], args[3],
					args[4]);

			HistoryStorage historyStorage = new HistoryStorage();
			historyStorage.equals(connection);
			MigrationConfiguration migrationConfiguration = Loader.load(
					new File("migrations/" + args[0]), performValidation);

			ExecutorEngine executorEngine = new ExecutorEngine(connection,
					migrationConfiguration, true);

			Application.configureExecutorEngine(executorEngine,
					DbConnector.POSTGRESQL_DB);

			executorEngine.setLogger(LoggerFactory.getLogger());
			executorEngine.executeMigration();

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

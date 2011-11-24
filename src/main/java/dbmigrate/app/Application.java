package dbmigrate.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import dbmigrate.executor.ExecutorEngine;
import dbmigrate.logging.LoggerFactory;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.operation.MigrationConfiguration;
import dbmigrate.parser.Loader;

public class Application {

	public static void main(String[] args) {
		try {
			DbConnector dbConnector = new DbConnector();

			Connection connection = dbConnector.getConnection(
					DbConnector.DB_TYPE, args[1], args[2], args[3], args[4]);

			MigrationConfiguration migrationConfiguration = Loader
					.load(new File("migrations/" + args[0]));

			ExecutorEngine executorEngine = new ExecutorEngine(connection,
					migrationConfiguration, true);
			executorEngine.setLogger(LoggerFactory.getLogger());
			executorEngine.executeMigration();

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("Program zakonczyl dzialanie.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

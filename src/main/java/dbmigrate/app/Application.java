package dbmigrate.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import dbmigrate.executor.ExecutorEngine;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.operation.MigrationConfiguration;

public class Application {

	public static void main(String[] args) {
		DbConnector dbConnector = new DbConnector();

		Connection connection = dbConnector.getConnection(DbConnector.DB_TYPE, args[1], args[2], args[3], args[4]);

		MigrationConfiguration migrationConfiguration = new MigrationConfiguration();

		ExecutorEngine executorEngine = new ExecutorEngine(connection, migrationConfiguration, true);

		executorEngine.executeMigration();

		
		for(Properties props : connection.getClientInfo();
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Program zakoÄczony¸ dzia¸anie.");
	}
}

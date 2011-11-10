package dbmigrate.app;

import java.sql.Connection;
import java.sql.SQLException;

import dbmigrate.executor.ExecutorEngine;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.operation.MigrationConfiguration;
import dbmigrate.parser.Loader;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

	public static void main(String[] args) {
		try
		{
			DbConnector dbConnector = new DbConnector();

			Connection connection = dbConnector.getConnection(DbConnector.DB_TYPE, args[1], args[2], args[3], args[4]);	
			MigrationConfiguration migrationConfiguration = Loader.load(new File(args[0]));

			ExecutorEngine executorEngine = new ExecutorEngine(connection, migrationConfiguration, true);

			executorEngine.executeMigration();

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("Program zakonczony dzialanie.");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

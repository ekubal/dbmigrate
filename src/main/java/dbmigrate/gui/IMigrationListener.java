package dbmigrate.gui;

import dbmigrate.exceptions.ConnectException;

/**
 * An interface for the MigrationRunner.
 * 
 * @author Tomasz Jędrzejewski
 */
public interface IMigrationListener {
	public void setStatusMessage(String message);
	public void lockButtons();
	public void unlockButtons();
	public void refreshHistoryModel();
	public void handleConnectionProblem(ConnectException exception);
} // end IMigrationListener;


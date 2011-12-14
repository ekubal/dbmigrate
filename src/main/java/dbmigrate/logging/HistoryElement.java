package dbmigrate.logging;

public class HistoryElement {
	private String ip;
	private String migration_id;
	private String date;
	private int direction;
	private String operations;
	private boolean success;
	public String getIp() {
		return ip;
	}
	public String getMigration_id() {
		return migration_id;
	}
	public String getDate() {
		return date;
	}
	public int getDirection() {
		return direction;
	}
	public String getOperations() {
		return operations;
	}
	public boolean isSuccess() {
		return success;
	}
	public HistoryElement(String ip, String migration_id, String date,
			int direction, String operations, boolean success) {
		super();
		this.ip = ip;
		this.migration_id = migration_id;
		this.date = date;
		this.direction = direction;
		this.operations = operations;
		this.success = success;
	}
	public String toString() {
		return ip +  " @ " + date + " [#" + migration_id + "], direction: " + direction + ": " + success;
	}
	
}

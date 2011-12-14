package dbmigrate.logging;

public class HistoryElement {
	private String ip;
	private String migrationId;
	private String date;
	private int direction;
	private String operations;
	private boolean success;

	public String getIp() {
		return this.ip;
	}
	public String getMigrationId() {
		return this.migrationId;
	}
	public String getDate() {
		return this.date;
	}
	public int getDirection() {
		return this.direction;
	}
	public String getOperations() {
		return this.operations;
	}
	public boolean isSuccess() {
		return this.success;
	}
	public HistoryElement(String ip, String migration_id, String date,
			int direction, String operations, boolean success) {
		super();
		this.ip = ip;
		this.migrationId = migration_id;
		this.date = date;
		this.direction = direction;
		this.operations = operations;
		this.success = success;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ip);
		sb.append(" @ ");
		sb.append(date);
		sb.append(" [#");
		sb.append(migrationId);
		if(0 == this.direction) {
			sb.append("], forward: ");
		} else {
			sb.append("], backward: ");
		}
		if(this.success) {
			sb.append("success!");
		} else {
			sb.append("failure.");
		}
		return sb.toString();
	}
	
}

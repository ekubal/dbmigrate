package dbmigrate.logging;

import dbmigrate.exceptions.HistoryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbmigrate.executor.CreateTableExecutor;
import dbmigrate.model.db.Column;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.Table;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.CreateTableOperationDescriptor;

public class HistoryStorage {
	
	private Connection conn;
	private String tableName = "history_storage";
	
	private void createTable() {
		Table table = new Table();
		table.setName(this.tableName);
		
		List<IColumn> columns =  new ArrayList<IColumn> ();
		Column ip = new Column();
		ip.setName("ip");
		ip.setType(TypeEnum.VARCHAR);
		ip.setLength(15);
		ip.setNullable(true);
		
		Column migrationId = new Column();
		migrationId.setName("migration_id");
		migrationId.setType(TypeEnum.VARCHAR);
		migrationId.setLength(100);
		migrationId.setNullable(false);
		
		Column date = new Column();
		date.setName("migration_date");
		date.setType(TypeEnum.DATE);
		
		Column direction = new Column();
		direction.setName("direction");
		direction.setType(TypeEnum.INT);
		
		Column operations = new Column();
		operations.setName("operations");
		operations.setType(TypeEnum.TEXT);
		
		Column success = new Column();
		success.setName("success");
		success.setType(TypeEnum.INT);
		
		columns.add(ip);
		columns.add(migrationId);
		columns.add(date);
		columns.add(direction);
		columns.add(operations);
		columns.add(success);
		
		table.setColumns(columns);
		
		
		CreateTableOperationDescriptor createDesc = new CreateTableOperationDescriptor();
		createDesc.setTable(table);
		
		CreateTableExecutor createExec = new CreateTableExecutor(this.conn);
		
		try {
			createExec.execute(createDesc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void store(String ip, String migration_id, String date, int direction, String operations, boolean success) throws HistoryException, SQLException {
		if(null == this.conn) {
			throw new HistoryException();
		}
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO \"").append(this.tableName).append("\" (ip, migration_id, migration_date, direction, operations, success) VALUES(");
		query.append("'").append(ip).append("', ");
		query.append("'").append(migration_id).append("', ");
		query.append("'").append(date).append("', ");
		query.append("").append(direction).append(", ");
		query.append("'").append(operations).append("', ");
		int succ = 0;
		if (success) {
			succ = 1;
		}
		query.append("").append(succ).append(");");

		this.conn.createStatement().executeUpdate(query.toString());
		this.conn.commit();
	}
	
	public List<HistoryElement> getHistory() throws HistoryException, SQLException {
		if(null == this.conn) {
			throw new HistoryException();
		}
		String query = "SELECT * FROM \"" + this.tableName + "\" ORDER BY migration_date DESC";
		List<HistoryElement> elements = new ArrayList<HistoryElement> ();
		Statement stmt = this.conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String ip = rs.getString(1);
			String migrationId = rs.getString(2);
			String date = rs.getString(3);
			int direction = rs.getInt(4);
			String operations = rs.getString(5);
			boolean success = rs.getBoolean(6);
			elements.add(new HistoryElement(ip, migrationId, date, direction, operations, success));
		}
		return elements;
	}
	
	public void setConnection(Connection conn) throws SQLException {
		if(null == conn) {
			return;
		}
		this.conn = conn;
		
		String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public' AND table_name='" + this.tableName + "';";
		ResultSet rset = conn.createStatement().executeQuery(query);
		Boolean hasTable = rset.next();
		int count = rset.getInt(1);
		
		if (count == 0) {
			this.createTable();
		}
	}

}

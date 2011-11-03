package dbmigrate.model.db;

import java.util.List;

public class Table implements ITable {

	private String name;
	
	private List<IColumn> columns;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public List<IColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<IColumn> columns) {
		this.columns = columns;
	}

}

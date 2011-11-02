package dbmigrate.model.db;

import java.util.List;

public interface ITable {

	public String getName();
	
	public void setName(String name);
	
	public List<IColumn> getColumns();
	
	public void setColumns(List<IColumn> columns);

}

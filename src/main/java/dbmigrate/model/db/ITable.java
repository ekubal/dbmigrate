package dbmigrate.model.db;

public interface ITable {

	public String getName();
	
	public void setName(String name);
	
	public List<IColumn> getColumns();
	
	public void setColumns(List<IColumn> columns);

}

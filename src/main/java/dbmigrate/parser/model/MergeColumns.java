package dbmigrate.parser.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("merge-columns")
public class MergeColumns implements IOperation {

	@XStreamAlias("table-name")
	private String tableName;
	
	@XStreamAlias("source-columns")
	private List<String> columnNames = new ArrayList<String>();
	
	@XStreamAlias("destination-column")
	private DestinationColumn destinationColumn;

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getColumnNames() {
		return this.columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public DestinationColumn getDestinationColumn() {
		return this.destinationColumn;
	}

	public void setDestinationColumn(DestinationColumn destinationColumn) {
		this.destinationColumn = destinationColumn;
	}
	
}

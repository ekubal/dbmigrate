package dbmigrate.parser.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("split-column")
public class SplitColumn implements IOperation {
	
	@XStreamAsAttribute
	private String id;
	
	@XStreamAlias("table-name")
	private String tableName;
	
	@XStreamAlias("source-column")
	private SourceColumn sourceColumn;
	
	private List<DestinationColumn> destinations = new ArrayList<DestinationColumn>();

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<DestinationColumn> getDestinations() {
		return this.destinations;
	}

	public void setDestinations(List<DestinationColumn> destinations) {
		this.destinations = destinations;
	}

	public SourceColumn getSourceColumn() {
		return this.sourceColumn;
	}

	public void setSourceColumn(SourceColumn sourceColumn) {
		this.sourceColumn = sourceColumn;
	}

}

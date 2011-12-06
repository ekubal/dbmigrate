package dbmigrate.parser.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("edit-column")
public class EditColumn implements IOperation{

	@XStreamAsAttribute
	private String id;
	
	private String table;
	
	private String newColumnName;
	
	private String oldColumnName;
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTable() {
		return this.table;
	}

	public void setTable(String tableName) {
		this.table = tableName;
	}

	public String getNewColumnName() {
		return this.newColumnName;
	}

	public void setNewColumnName(String newColumnName) {
		this.newColumnName = newColumnName;
	}

	public String getOldColumnName() {
		return this.oldColumnName;
	}

	public void setOldColumnName(String oldColumnName) {
		this.oldColumnName = oldColumnName;
	}
}

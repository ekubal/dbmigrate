package dbmigrate.parser.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("create-column")
public class CreateColumn implements IOperation {

	@XStreamAsAttribute
	private String id;

	private String table;

	private String name;

	private String type;

	private Long length;

	private Boolean notnull;

	@XStreamAlias("default")
	private String defaultValue;

	@XStreamAlias("values")
	private String valueToSet;

	private Boolean signed;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTable() {
		return this.table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getLength() {
		return this.length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public Boolean getNotnull() {
		return this.notnull;
	}

	public void setNotnull(Boolean notnull) {
		this.notnull = notnull;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Boolean getSigned() {
		return this.signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	public String getValueToSet() {
		return this.valueToSet;
	}

	public void setValueToSet(String valueToSet) {
		this.valueToSet = valueToSet;
	}
}

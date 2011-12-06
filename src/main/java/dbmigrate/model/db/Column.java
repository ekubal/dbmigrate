package dbmigrate.model.db;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Column implements IColumn {

	public static final String RANDOM = "RANDOM";

	private String name;
	private Boolean nullable = null;
	private TypeEnum type;
	private Boolean signed = null;
	private int length = -1;
	private String defaultValue;
	private String valueToSet = null; // dla wartosci Column.RANDOM - losowa
	private static final Set<TypeEnum> typesWithLength = new HashSet<TypeEnum>(Arrays.asList(new TypeEnum[] { TypeEnum.VARCHAR, TypeEnum.TEXT }));

	public String getSqlDescription() {
		String desc = "";
		StringBuffer buf = new StringBuffer();

		buf.append(this.getName()).append(' ');
		buf.append(this.getType().toString()).append(' ');
		if (Column.typesWithLength.contains(this.type)) {
			if (this.getLength() > -1) {
				buf.append('(').append(this.getLength()).append(") ");
			}
		}

		if (this.defaultValue != null && this.defaultValue != "") {
			buf.append("DEFAULT ");
			switch (this.type) {
				case VARCHAR:
					buf.append("'" + this.defaultValue + "' ");
					break;
				case TEXT:
					buf.append("'" + this.defaultValue + "' ");
					break;
				case DATETIME:
					buf.append("'" + this.defaultValue + "' ");
					break;
				case DATE:
					buf.append("'" + this.defaultValue + "' ");
					break;
				case BINARY:
					buf.append("B'" + this.defaultValue + "' ");
					break;
				default:
					buf.append(this.defaultValue + " ");
					break;
			}
		}

		if (this.getSigned() != null) {
			if (this.getSigned()) {
				buf.append("SIGNED ");
			} else {
				buf.append("UNSIGNED ");
			}
		}

		if (this.getNullable() != null) {
			if (this.getNullable()) {
				buf.append("NULL ");
			} else {
				buf.append("NOT NULL ");
			}
		}

		desc = buf.toString().trim();
		return desc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeEnum getType() {
		return this.type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public Boolean getNullable() {
		return this.nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public Boolean getSigned() {
		return this.signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getDefault() {
		return this.defaultValue;
	}

	public void setDefault(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getValueToSet() {
		return this.valueToSet;
	}

	public void setValueToSet(String valueToSet) {
		this.valueToSet = valueToSet;
	}

}

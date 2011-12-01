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

		buf.append(getName()).append(' ');
		buf.append(getType().toString()).append(' ');
		if (typesWithLength.contains(type)) {
			if (getLength() > -1) {
				buf.append('(').append(getLength()).append(") ");
			}
		}

		if (defaultValue != null && defaultValue != "") {
			buf.append("DEFAULT ");
			switch (type) {
				case VARCHAR:
					buf.append("'" + defaultValue + "' ");
					break;
				case TEXT:
					buf.append("'" + defaultValue + "' ");
					break;
				case DATETIME:
					buf.append("'" + defaultValue + "' ");
					break;
				case DATE:
					buf.append("'" + defaultValue + "' ");
					break;
				case BINARY:
					buf.append("B'" + defaultValue + "' ");
					break;
				default:
					buf.append(defaultValue + " ");
					break;
			}
		}

		if (getSigned() != null) {
			if (getSigned()) {
				buf.append("SIGNED ");
			} else {
				buf.append("UNSIGNED ");
			}
		}

		if (getNullable() != null) {
			if (getNullable()) {
				buf.append("NULL ");
			} else {
				buf.append("NOT NULL ");
			}
		}

		desc = buf.toString().trim();
		return desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public Boolean getSigned() {
		return signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getDefault() {
		return defaultValue;
	}

	public void setDefault(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getValueToSet() {
		return valueToSet;
	}

	public void setValueToSet(String valueToSet) {
		this.valueToSet = valueToSet;
	}

}

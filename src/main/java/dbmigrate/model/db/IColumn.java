package dbmigrate.model.db;

public interface IColumn {

	public abstract String getSqlDescription();

	public abstract String getName();

	public abstract void setName(String name);

	public abstract TypeEnum getType();

	public abstract void setType(TypeEnum type);

	public abstract Boolean getNullable();

	public abstract void setNullable(Boolean nullable);

	public abstract Boolean getSigned();

	public abstract void setSigned(Boolean signed);

	public abstract int getLength();

	public abstract void setLength(int length);

}
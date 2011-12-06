package dbmigrate.model.operation;

import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.ITable;

public class RenameColumnOperationDescriptor implements IOperationDescriptor {
	private IColumn oldColumn;
	private IColumn newColumn;
	private ITable table;
	
	public RenameColumnOperationDescriptor(ITable table, IColumn oldColumn,
			IColumn newColumn) {
		super();
		this.oldColumn = oldColumn;
		this.newColumn = newColumn;
		this.table = table;
	}
	
	public IColumn getOldColumn() {
		return this.oldColumn;
	}
	public void setOldColumn(IColumn oldColumn) {
		this.oldColumn = oldColumn;
	}
	public IColumn getNewColumn() {
		return this.newColumn;
	}
	public void setNewColumn(IColumn newColumn) {
		this.newColumn = newColumn;
	}
	public ITable getTable() {
		return this.table;
	}
	public void setTable(ITable table) {
		this.table = table;
	}


	
}

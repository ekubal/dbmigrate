package dbmigrate.model.operation;

import java.util.ArrayList;
import java.util.List;

public class MigrationConfiguration {

	private List<IOperationDescriptor> operations;
	
	private List<IOperationDescriptor> undoOperations;

	public void addOperation(IOperationDescriptor operationDescriptor) {
		if (this.operations == null) {
			this.operations = new ArrayList<IOperationDescriptor>();
		}

		this.operations.add(operationDescriptor);
	}

	public void addUndoOperation(IOperationDescriptor operationDescriptor) {
		if (this.undoOperations == null) {
			this.undoOperations = new ArrayList<IOperationDescriptor>();
		}

		this.undoOperations.add(operationDescriptor);
	}
	
	public List<IOperationDescriptor> getOperations() {
		return this.operations;
	}
	
	public List<IOperationDescriptor> getOperations(boolean forwards) {
		if(forwards) {
			return this.operations;
		}
		return this.undoOperations;
	}

	public void setOperations(List<IOperationDescriptor> operations) {
		this.operations = operations;
	}
	
	public void setUndoOperations(List<IOperationDescriptor> operations) {
		this.undoOperations = operations;
	}

}

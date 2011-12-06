package dbmigrate.model.operation;

import java.util.ArrayList;
import java.util.List;

public class MigrationConfiguration {

	List<IOperationDescriptor> operations;

	public void addOperation(IOperationDescriptor operationDescriptor) {
		if (this.operations == null) {
			this.operations = new ArrayList<IOperationDescriptor>();
		}

		this.operations.add(operationDescriptor);
	}

	public List<IOperationDescriptor> getOperations() {
		return this.operations;
	}

	public void setOperations(List<IOperationDescriptor> operations) {
		this.operations = operations;
	}

}

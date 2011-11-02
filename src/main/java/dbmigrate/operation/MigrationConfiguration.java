package dbmigrate.operation;

import java.util.ArrayList;
import java.util.List;

public class MigrationConfiguration {

	List<IOperationDescriptor> operations;

	public void addOperation(IOperationDescriptor operationDescriptor) {
		if (operations == null) {
			operations = new ArrayList<IOperationDescriptor>();
		}

		operations.add(operationDescriptor);
	}

	public List<IOperationDescriptor> getOperations() {
		return operations;
	}

	public void setOperations(List<IOperationDescriptor> operations) {
		this.operations = operations;
	}

}

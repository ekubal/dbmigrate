package dbmigrate.parser.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("destination-column")
public class DestinationColumn {

	@XStreamAlias("merge-expression")
	private String mergeExpression;
	
	@XStreamAlias("splitted-index")
	private Integer splittedIndex;
	
	@XStreamAlias("create-column")
	private CreateColumn createColumn;

	public String getMergeExpression() {
		return mergeExpression;
	}

	public void setMergeExpression(String mergeExpression) {
		this.mergeExpression = mergeExpression;
	}

	public CreateColumn getCreateColumn() {
		return createColumn;
	}

	public void setCreateColumn(CreateColumn createColumn) {
		this.createColumn = createColumn;
	}

	public Integer getSplittedIndex() {
		return splittedIndex;
	}

	public void setSplittedIndex(Integer splittedIndex) {
		this.splittedIndex = splittedIndex;
	}
	
}

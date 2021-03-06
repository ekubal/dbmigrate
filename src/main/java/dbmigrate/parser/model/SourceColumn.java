package dbmigrate.parser.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("source-column")
public class SourceColumn {

	private String name;
	
	@XStreamAlias("split-expression")
	private String splitExpression;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSplitExpression() {
		return this.splitExpression;
	}

	public void setSplitExpression(String splitExpression) {
		this.splitExpression = splitExpression;
	}
	
}

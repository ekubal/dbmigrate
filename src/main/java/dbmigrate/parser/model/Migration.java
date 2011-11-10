package dbmigrate.parser.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("migration")
public class Migration {

	@XStreamAsAttribute
	private String version;

	@XStreamAlias("do")
	private List<IOperation> doList = new ArrayList<IOperation>();
	
	@XStreamAlias("undo")
	private List<IOperation> undoList = new ArrayList<IOperation>();
	
	public List<IOperation> getDoList() {
		return doList;
	}
	
	public void setDoList(List<IOperation> doList) {
		this.doList = doList;
	}
	
	public List<IOperation> getUndoList() {
		return undoList;
	}
	
	public void setUndoList(List<IOperation> undoList) {
		this.undoList = undoList;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}

package software.dygzt.service.research.model;

import java.util.ArrayList;
import java.util.List;

public class ResearchTableRow {
	private ResearchTableColumn rowInfo;
	private List<ResearchTableCell> value;
	public ResearchTableRow() {
		super();
		value = new ArrayList<ResearchTableCell>();
	}
	public ResearchTableColumn getRowInfo() {
		return rowInfo;
	}
	public void setRowInfo(ResearchTableColumn rowInfo) {
		this.rowInfo = rowInfo;
	}
	public List<ResearchTableCell> getValue() {
		return value;
	}
	public void setValue(List<ResearchTableCell> value) {
		this.value = value;
	}
	
	
}

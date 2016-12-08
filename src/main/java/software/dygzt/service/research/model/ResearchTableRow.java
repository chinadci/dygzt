package software.dygzt.service.research.model;

import java.util.ArrayList;
import java.util.List;

/*保存每一行的数据，每一行其实就是多个 cell 组成而来的*/
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

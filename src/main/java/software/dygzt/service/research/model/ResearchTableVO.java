package software.dygzt.service.research.model;

import java.util.List;

public class ResearchTableVO {
	/**
	 * 报表名称
	 */
	private String bbmc;
	/**
	 * 调研条件
	 */
	private String dytj;
	/**
	 * 行条件列数
	 */
	private int rowlevel;
	/**
	 * 列条件行数
	 */
	private int collevel;
	/**
	 * 值
	 */
	private List<List<ResearchTableCellVO>> valueList;
	
	public ResearchTableVO() {
		super();
	}
	public String getBbmc() {
		return bbmc;
	}
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}
	public String getDytj() {
		return dytj;
	}
	public void setDytj(String dytj) {
		this.dytj = dytj;
	}
	public int getRowlevel() {
		return rowlevel;
	}
	public void setRowlevel(int rowlevel) {
		this.rowlevel = rowlevel;
	}
	public int getCollevel() {
		return collevel;
	}
	public void setCollevel(int collevel) {
		this.collevel = collevel;
	}
	public List<List<ResearchTableCellVO>> getValueList() {
		return valueList;
	}
	public void setValueList(List<List<ResearchTableCellVO>> valueList) {
		this.valueList = valueList;
	}
	
}

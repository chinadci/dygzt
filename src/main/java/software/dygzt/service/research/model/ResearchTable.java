package software.dygzt.service.research.model;

import java.util.ArrayList;
import java.util.List;

public class ResearchTable implements Cloneable{
	/**
	 * 报表编号
	 */
	private String bbbh;
	/**
	 * 报表横向类型  1：法院 2：案由
	 */
	private String bblx;
	/**
	 * 报表名称
	 */
	private String bbmc;
	/**
	 * 开始日期
	 */
	private String ksrq;
	/**
	 * 结束日期
	 */
	private String jsrq;
	/**
	 * 法院范围(该表格要查询的法院)
	 */
	private String fyfw;
	/**
	 * 条件(baseCondition.toString())
	 */
	private String condition;
	/**
	 * 表条件(表格的一级列条件，进行初步查询)
	 */
	private ResearchQueryCondition baseCondition;
	/**
	 * 列变量(二级列条件)
	 */
	private String colvarlist;
	/**
	 * 行变量(行条件，案由，部门，法院等，需要设置不同的案由值，每个法院的案由集可能会不一样)
	 */
	private String rowvarlist;
	/**
	 * 列层数 
	 */
	private int collevel;
	/**
	 * 行层数 
	 */
	private int rowlevel;
	/**
	 * 行的信息(最重要的一个变量，基本表格要输出的数据都保存在这里，rowList 的一个元素，代表了页面中的表格要显示的一行数据)
	 */
	private List<ResearchTableRow> rowList;
	/**
	 * 列的信息(只是用来初始化前端页面的表头信息，具体数据豆包凑在 rowList 集合中)
	 */
	private List<ResearchTableColumn> colList;
	/**
	 * 列的条件，用来初步筛选(根据列条件查询出所有的符合的案件集合)
	 */
	private List<ResearchCellCondition> queryList;
	/**
	 * 失败法院
	 */
	private String sbfy;
	/**
	 * 调研条件(用户request传入的condition.toString())
	 */
	private String dytj;
	public ResearchTable() {
		super();
		rowList = new ArrayList<ResearchTableRow>();
		colList = new ArrayList<ResearchTableColumn>();
	}
	public String getBbbh() {
		return bbbh;
	}
	public void setBbbh(String bbbh) {
		this.bbbh = bbbh;
	}
	public String getBblx() {
		return bblx;
	}
	public void setBblx(String bblx) {
		this.bblx = bblx;
	}
	public String getBbmc() {
		return bbmc;
	}
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}
	public String getKsrq() {
		return ksrq;
	}
	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	public String getFyfw() {
		return fyfw;
	}
	public void setFyfw(String fyfw) {
		this.fyfw = fyfw;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public ResearchQueryCondition getBaseCondition() {
		return baseCondition;
	}
	public void setBaseCondition(ResearchQueryCondition baseCondition) {
		this.baseCondition = baseCondition;
	}
	public String getColvarlist() {
		return colvarlist;
	}
	public void setColvarlist(String colvarlist) {
		this.colvarlist = colvarlist;
	}
	public String getRowvarlist() {
		return rowvarlist;
	}
	public void setRowvarlist(String rowvarlist) {
		this.rowvarlist = rowvarlist;
	}
	public int getCollevel() {
		return collevel;
	}
	public void setCollevel(int collevel) {
		this.collevel = collevel;
	}
	public int getRowlevel() {
		return rowlevel;
	}
	public void setRowlevel(int rowlevel) {
		this.rowlevel = rowlevel;
	}
	public List<ResearchTableRow> getRowList() {
		return rowList;
	}
	public void setRowList(List<ResearchTableRow> rowList) {
		this.rowList = rowList;
	}
	public List<ResearchTableColumn> getColList() {
		return colList;
	}
	public void setColList(List<ResearchTableColumn> colList) {
		this.colList = colList;
	}
	public List<ResearchCellCondition> getQueryList() {
		return queryList;
	}
	public void setQueryList(List<ResearchCellCondition> queryList) {
		this.queryList = queryList;
	}
	public String getSbfy() {
		return sbfy;
	}
	public void setSbfy(String sbfy) {
		this.sbfy = sbfy;
	}
	public void addSbfy(String fy) {
		if(sbfy==null){
			sbfy = fy;
		}else{
			sbfy = sbfy +";"+ fy;
		}
	}
	public String getDytj() {
		return dytj;
	}
	public void setDytj(String dytj) {
		this.dytj = dytj;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bbbh == null) ? 0 : bbbh.hashCode());
		result = prime * result + ((bblx == null) ? 0 : bblx.hashCode());
		result = prime * result + ((bbmc == null) ? 0 : bbmc.hashCode());
		result = prime * result
				+ ((colvarlist == null) ? 0 : colvarlist.hashCode());
		result = prime * result
				+ ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((fyfw == null) ? 0 : fyfw.hashCode());
		result = prime * result + ((jsrq == null) ? 0 : jsrq.hashCode());
		result = prime * result + ((ksrq == null) ? 0 : ksrq.hashCode());
		result = prime * result
				+ ((rowvarlist == null) ? 0 : rowvarlist.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResearchTable other = (ResearchTable) obj;
		if (bbbh == null) {
			if (other.bbbh != null)
				return false;
		} else if (!bbbh.equals(other.bbbh))
			return false;
		if (bblx == null) {
			if (other.bblx != null)
				return false;
		} else if (!bblx.equals(other.bblx))
			return false;
		if (bbmc == null) {
			if (other.bbmc != null)
				return false;
		} else if (!bbmc.equals(other.bbmc))
			return false;
		if (colvarlist == null) {
			if (other.colvarlist != null)
				return false;
		} else if (!colvarlist.equals(other.colvarlist))
			return false;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (fyfw == null) {
			if (other.fyfw != null)
				return false;
		} else if (!fyfw.equals(other.fyfw))
			return false;
		if (jsrq == null) {
			if (other.jsrq != null)
				return false;
		} else if (!jsrq.equals(other.jsrq))
			return false;
		if (ksrq == null) {
			if (other.ksrq != null)
				return false;
		} else if (!ksrq.equals(other.ksrq))
			return false;
		if (rowvarlist == null) {
			if (other.rowvarlist != null)
				return false;
		} else if (!rowvarlist.equals(other.rowvarlist))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		ResearchTable table = new ResearchTable();
		table.setBbbh(bbbh);
		table.setBblx(bblx);
		table.setBbmc(bbmc);
		table.setKsrq(ksrq);
		table.setJsrq(jsrq);
		table.setFyfw(fyfw);
		table.setCondition(condition);
		table.setColvarlist(colvarlist);
		table.setRowvarlist(rowvarlist);
		table.setCollevel(collevel);
		table.setRowlevel(rowlevel);
		table.setBaseCondition((ResearchQueryCondition) baseCondition.clone());
		//列条件深度克隆
		List<ResearchCellCondition> querys = new ArrayList<ResearchCellCondition>();
		for(ResearchCellCondition query:queryList){
			querys.add((ResearchCellCondition) query.clone());
		}
		table.setQueryList(querys);
		List<ResearchTableColumn> cols = table.getColList();
		//列条件只有第一个重建
		if(colList.size()>0){
			cols.add((ResearchTableColumn) colList.get(0).clone());
			for(int i = 1;i<colList.size();i++){
				cols.add(colList.get(i));
			}
		}
		return table;
	}
	
}

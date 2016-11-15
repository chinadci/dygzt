package software.dygzt.service.research.model;

import software.dygzt.util.NumberUtil;


public class ResearchTableCellVO {
	private String value;
	private int colspan;
	private int rowspan;
	private String base;
	private String sql;
	private String fydm;
	
	public ResearchTableCellVO(String value, int colspan, int rowspan,String base,
			String sql,String fydm) {
		super();
		this.value = value;
		this.colspan = colspan;
		this.rowspan = rowspan;
		this.base = base;
		this.sql = sql;
		this.fydm = fydm;
	}
	
	public ResearchTableCellVO(Double value, int colspan, int rowspan,String base,
			String sql,String fydm) {
		super();
		setValue(value);
		this.colspan = colspan;
		this.rowspan = rowspan;
		this.base = base;
		this.sql = sql;
		this.fydm = fydm;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(Double value) {
		if(value.intValue()==value){
			this.value = String.valueOf(value.intValue());
		}else{
			this.value = NumberUtil.changeNumber(value.doubleValue(), 4);
		}
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getColspan() {
		return colspan;
	}
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
	public int getRowspan() {
		return rowspan;
	}
	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getFydm() {
		return fydm;
	}
	public void setFydm(String fydm) {
		this.fydm = fydm;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
}

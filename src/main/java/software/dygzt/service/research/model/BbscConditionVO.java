package software.dygzt.service.research.model;

import software.dygzt.util.DateUtil;
import software.dygzt.util.StringUtil;

public class BbscConditionVO {
	/**
	 * 报表编号（适用于案由）
	 */
	private String bbbh;
	/**
	 * 报表横向类型  researchfy：法院 researchay：案由 researchbm：部门。以及所有对应的jzrhZ
	 */
	private String bblx;
	/**
	 * 开始日期
	 */
	private String ksrq;
	/**
	 * 结束日期
	 */
	private String jsrq;
	/**
	 * 法院范围
	 */
	private String fyfw;
	/**
	 * 一级列条件
	 */
	private String yjCondition;
	/**
	 * 列条件(二级列条件),生成表头的二级列中一个cell要显示的条件
	 */
	private String colCondition;
	
	/**
	 * 报表编号
	 */
	public String getBbbh() {
		return bbbh;
	}
	public void setBbbh(String bbbh) {
		if(StringUtil.isNotBlank(bbbh)){
			this.bbbh = bbbh.trim();
		}
	}
	
	/**
	 * 类型
	 */
	public String getBblx() {
		return bblx;
	}
	public void setBblx(String bblx) {
		if(StringUtil.isNotBlank(bblx)){
			this.bblx = bblx;
		}
	}
	
	public String getKsrq() {
		return ksrq;
	}
	
	public void setKsrq(String ksrq) {
		if(StringUtil.isNotBlank(ksrq)){
			this.ksrq = DateUtil.format(DateUtil.parse(ksrq.trim(), 
					DateUtil.webFormat),DateUtil.webFormat);
		}
	}

	public String getJsrq() {
		return jsrq;
	}
	
	public void setJsrq(String jsrq) {
		if(StringUtil.isNotBlank(jsrq)){
			this.jsrq = DateUtil.format(DateUtil.parse(jsrq.trim(), 
					DateUtil.webFormat),DateUtil.webFormat);
		}
	}
	public String getFyfw() {
		return fyfw;
	}
	public void setFyfw(String fyfw){
		if(StringUtil.isNotBlank(fyfw)){
			this.fyfw = fyfw;
		}
	}
	public String getYjCondition() {
		return yjCondition;
	}
	public void setYjCondition(String yjCondition) {
		if(StringUtil.isNotBlank(yjCondition)){
			this.yjCondition = yjCondition;
		}
	}
	public String getColCondition() {
		return colCondition;
	}
	public void setColCondition(String colCondition) {
		if(StringUtil.isNotBlank(colCondition)){
			this.colCondition = colCondition;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((yjCondition == null) ? 0 : yjCondition.hashCode());
		result = prime * result + ((bbbh == null) ? 0 : bbbh.hashCode());
		result = prime * result + ((bblx == null) ? 0 : bblx.hashCode());
		result = prime * result
				+ ((colCondition == null) ? 0 : colCondition.hashCode());
		result = prime * result + ((fyfw == null) ? 0 : fyfw.hashCode());
		result = prime * result + ((jsrq == null) ? 0 : jsrq.hashCode());
		result = prime * result + ((ksrq == null) ? 0 : ksrq.hashCode());
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
		BbscConditionVO other = (BbscConditionVO) obj;
		if (yjCondition == null) {
			if (other.yjCondition != null)
				return false;
		} else if (!yjCondition.equals(other.yjCondition))
			return false;
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
		if (colCondition == null) {
			if (other.colCondition != null)
				return false;
		} else if (!colCondition.equals(other.colCondition))
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
		return true;
	}
	@Override
	public String toString() {
		String str = "";
		if(StringUtil.isNotBlank(bbbh)){
			str +="bbbh:"+bbbh+";";
		}
		if(StringUtil.isNotBlank(bblx)){
			str +="bblx:"+bblx+";";
		}
		if(StringUtil.isNotBlank(ksrq)){
			str +="ksrq:"+ksrq+";";
		}
		if(StringUtil.isNotBlank(jsrq)){
			str +="jsrq:"+jsrq+";";
		}
		if(StringUtil.isNotBlank(fyfw)){
			str +="fyfw:"+fyfw+";";
		}
		if(StringUtil.isNotBlank(yjCondition)){
			str +="yjCondition:"+yjCondition+";";
		}
		if(StringUtil.isNotBlank(colCondition)){
			str +="colCondition:"+colCondition+";";
		}
		if(str.endsWith(";")){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
}

package software.dygzt.service.research.model;

import software.dygzt.util.DateUtil;
import software.dygzt.util.StringUtil;

/**
 * 调研条件VO
 */
public class ResearchConditionVO {
	/**
	 * 报表编号
	 */
	private String bbbh;
	/**
	 * 报表横向类型  researchfy：法院 researchay：案由 researchbm：部门
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
	 * 报表名称
	 */
	private String bbmc;
	/**
	 * 列条件
	 */
	private String colCondition;
	/**
	 * 行条件
	 */
	private String rowCondition;
	/**
	 * 条件
	 */
	private String condition;
	
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
	public String getBbmc() {
		return bbmc;
	}
	public void setBbmc(String bbmc) {
		if(StringUtil.isNotBlank(bbmc)){
			this.bbmc = bbmc;
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
	public String getColCondition() {
		return colCondition;
	}
	public void setColCondition(String colCondition) {
		if(StringUtil.isNotBlank(colCondition)){
			this.colCondition = colCondition;
		}
	}
	public String getRowCondition() {
		return rowCondition;
	}
	public void setRowCondition(String rowCondition) {
		if(StringUtil.isNotBlank(rowCondition)){
			this.rowCondition = rowCondition;
		}
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		if(StringUtil.isNotBlank(condition)){
			this.condition = condition;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bbbh == null) ? 0 : bbbh.hashCode());
		result = prime * result + ((bblx == null) ? 0 : bblx.hashCode());
		result = prime * result + ((bbmc == null) ? 0 : bbmc.hashCode());
		result = prime * result
				+ ((colCondition == null) ? 0 : colCondition.hashCode());
		result = prime * result
				+ ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((fyfw == null) ? 0 : fyfw.hashCode());
		result = prime * result + ((jsrq == null) ? 0 : jsrq.hashCode());
		result = prime * result + ((ksrq == null) ? 0 : ksrq.hashCode());
		result = prime * result
				+ ((rowCondition == null) ? 0 : rowCondition.hashCode());
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
		ResearchConditionVO other = (ResearchConditionVO) obj;
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
		if (colCondition == null) {
			if (other.colCondition != null)
				return false;
		} else if (!colCondition.equals(other.colCondition))
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
		if (rowCondition == null) {
			if (other.rowCondition != null)
				return false;
		} else if (!rowCondition.equals(other.rowCondition))
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
		if(StringUtil.isNotBlank(bbmc)){
			str +="bbmc:"+bbmc+";";
		}
		if(StringUtil.isNotBlank(colCondition)){
			str +="colCondition:"+colCondition+";";
		}
		if(StringUtil.isNotBlank(rowCondition)){
			str +="rowCondition:"+rowCondition+";";
		}
		if(StringUtil.isNotBlank(condition)){
			str+=condition;
		}
		if(str.endsWith(";")){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
	
	
}

package software.dygzt.service.research.model;

import java.io.UnsupportedEncodingException;

import software.dygzt.util.StringUtil;

/**
 * 调研查询VO
 */
public class SearchConditionVO {
	/**
	 * 调研法院
	 */
	private String dyfy;
	/**
	 * 报表横向类型  researchfy：法院 researchay：案由 researchbm：部门
	 */
	private String bblx;
	/**
	 * 案件状态
	 */
	private String ajzt;
	/**
	 * 开始日期
	 */
	private String ksrq;
	/**
	 * 结束日期
	 */
	private String jsrq;
	/**
	 * 调研人
	 */
	private String dyr;
	/**
	 * 调研人法院
	 */
	private String dyrfydm;
	
	public String getDyfy() {
		return dyfy;
	}
	public void setDyfy(String dyfy) {
		if(StringUtil.isNotBlank(dyfy)){
			this.dyfy = dyfy;
		}
	}
	public String getAjzt() {
		return ajzt;
	}
	public void setAjzt(String ajzt) throws UnsupportedEncodingException {
		if(StringUtil.isNotBlank(ajzt)){
			this.ajzt = new String(ajzt.getBytes("ISO-8859-1"),"utf-8");
		}
	}
	public String getDyr() {
		return dyr;
	}
	public void setDyr(String dyr) throws UnsupportedEncodingException {
		if(StringUtil.isNotBlank(dyr)){
			this.dyr = new String(dyr.getBytes("ISO-8859-1"),"utf-8");;
		}
	}
	public String getDyrfydm() {
		return dyrfydm;
	}
	public void setDyrfydm(String dyrfydm) {
		if(StringUtil.isNotBlank(dyrfydm)){
			this.dyrfydm = dyrfydm;
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
			this.ksrq = ksrq.trim();
		}
	}

	public String getJsrq() {
		return jsrq;
	}
	
	public void setJsrq(String jsrq) {
		if(StringUtil.isNotBlank(jsrq)){
			this.jsrq = jsrq.trim();
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ajzt == null) ? 0 : ajzt.hashCode());
		result = prime * result + ((bblx == null) ? 0 : bblx.hashCode());
		result = prime * result + ((dyfy == null) ? 0 : dyfy.hashCode());
		result = prime * result + ((dyr == null) ? 0 : dyr.hashCode());
		result = prime * result + ((dyrfydm == null) ? 0 : dyrfydm.hashCode());
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
		SearchConditionVO other = (SearchConditionVO) obj;
		if (ajzt == null) {
			if (other.ajzt != null)
				return false;
		} else if (!ajzt.equals(other.ajzt))
			return false;
		if (bblx == null) {
			if (other.bblx != null)
				return false;
		} else if (!bblx.equals(other.bblx))
			return false;
		if (dyfy == null) {
			if (other.dyfy != null)
				return false;
		} else if (!dyfy.equals(other.dyfy))
			return false;
		if (dyr == null) {
			if (other.dyr != null)
				return false;
		} else if (!dyr.equals(other.dyr))
			return false;
		if (dyrfydm == null) {
			if (other.dyrfydm != null)
				return false;
		} else if (!dyrfydm.equals(other.dyrfydm))
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
		if(StringUtil.isNotBlank(dyfy)){
			str +="dyfy="+dyfy+"&";
		}
		if(StringUtil.isNotBlank(bblx)){
			str +="bblx="+bblx+"&";
		}
		if(StringUtil.isNotBlank(ajzt)){
			str +="ajzt="+ajzt+"&";
		}
		if(StringUtil.isNotBlank(ksrq)){
			str +="ksrq="+ksrq+"&";
		}
		if(StringUtil.isNotBlank(jsrq)){
			str +="jsrq="+jsrq+"&";
		}
		if(StringUtil.isNotBlank(dyr)){
			str +="dyr="+dyr+"&";
		}
		if(StringUtil.isNotBlank(dyrfydm)){
			str +="dyrfydm="+dyrfydm+"&";
		}
		if(str.endsWith("&")){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
}

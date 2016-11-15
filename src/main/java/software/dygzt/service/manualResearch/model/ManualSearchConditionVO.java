package software.dygzt.service.manualResearch.model;

import java.io.UnsupportedEncodingException;

import software.dygzt.util.StringUtil;

/**
 * 调研查询VO
 */
public class ManualSearchConditionVO {
	/**
	 * 调研状态
	 */
	private String dyzt;
	/**
	 * 调研法院
	 */
	private String dyfy;
	/**
	 * 开始日期
	 */
	private String ksrq;
	/**
	 * 结束日期
	 */
	private String jsrq;
	/**
	 * 案件类型
	 */
	private String ajlx;
	/**
	 * 调研人
	 */
	private String dyr;
	/**
	 * 调研人法院
	 */
	private String dyrfydm;
	/**
	 * 审批人
	 */
	private String spr;
	/**
	 * 审批人法院
	 */
	private String sprfydm;
	/**
	 * 计算人
	 */
	private String jsr;

	public String getDyzt() {
		return dyzt;
	}
	public void setDyzt(String dyzt) throws UnsupportedEncodingException {
		if(StringUtil.isNotBlank(dyzt)){
			this.dyzt = new String(dyzt.getBytes("ISO-8859-1"),"utf-8");
		}
	}
	public String getDyfy() {
		return dyfy;
	}
	public void setDyfy(String dyfy) throws UnsupportedEncodingException {
		if(StringUtil.isNotBlank(dyfy)){
			this.dyfy = new String(dyfy.getBytes("ISO-8859-1"),"utf-8");
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
	
	public String getAjlx() {
		return ajlx;
	}
	public void setAjlx(String ajlx) throws UnsupportedEncodingException {
		if(StringUtil.isNotBlank(ajlx)){
			this.ajlx = new String(ajlx.getBytes("ISO-8859-1"),"utf-8");
		}
	}
	public String getDyr() {
		return dyr;
	}
	public void setDyr(String dyr) throws UnsupportedEncodingException {
		if(StringUtil.isNotBlank(dyr)){
			this.dyr = new String(dyr.getBytes("ISO-8859-1"),"utf-8");
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
	public String getSpr() {
		return spr;
	}
	public void setSpr(String spr) throws UnsupportedEncodingException {
		if(StringUtil.isNotBlank(spr)){
			this.spr = new String(spr.getBytes("ISO-8859-1"),"utf-8");
		}
	}
	public String getSprfydm() {
		return sprfydm;
	}
	public void setSprfydm(String sprfydm) {
		if(StringUtil.isNotBlank(sprfydm)){
			this.sprfydm = sprfydm;
		}
	}
	public String getJsr() {
		return jsr;
	}
	public void setJsr(String jsr) throws UnsupportedEncodingException {
		if(StringUtil.isNotBlank(jsr)){
			this.jsr = new String(jsr.getBytes("ISO-8859-1"),"utf-8");;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ajlx == null) ? 0 : ajlx.hashCode());
		result = prime * result + ((dyfy == null) ? 0 : dyfy.hashCode());
		result = prime * result + ((dyr == null) ? 0 : dyr.hashCode());
		result = prime * result + ((dyrfydm == null) ? 0 : dyrfydm.hashCode());
		result = prime * result + ((dyzt == null) ? 0 : dyzt.hashCode());
		result = prime * result + ((jsr == null) ? 0 : jsr.hashCode());
		result = prime * result + ((jsrq == null) ? 0 : jsrq.hashCode());
		result = prime * result + ((ksrq == null) ? 0 : ksrq.hashCode());
		result = prime * result + ((spr == null) ? 0 : spr.hashCode());
		result = prime * result + ((sprfydm == null) ? 0 : sprfydm.hashCode());
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
		ManualSearchConditionVO other = (ManualSearchConditionVO) obj;
		if (ajlx == null) {
			if (other.ajlx != null)
				return false;
		} else if (!ajlx.equals(other.ajlx))
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
		if (dyzt == null) {
			if (other.dyzt != null)
				return false;
		} else if (!dyzt.equals(other.dyzt))
			return false;
		if (jsr == null) {
			if (other.jsr != null)
				return false;
		} else if (!jsr.equals(other.jsr))
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
		if (spr == null) {
			if (other.spr != null)
				return false;
		} else if (!spr.equals(other.spr))
			return false;
		if (sprfydm == null) {
			if (other.sprfydm != null)
				return false;
		} else if (!sprfydm.equals(other.sprfydm))
			return false;
		return true;
	}
	@Override
	public String toString() {
		String str = "";
		if(StringUtil.isNotBlank(dyzt)){
			str +="dyzt="+dyzt+"&";
		}
		if(StringUtil.isNotBlank(dyfy)){
			str +="dyfy="+dyfy+"&";
		}
		if(StringUtil.isNotBlank(ksrq)){
			str +="ksrq="+ksrq+"&";
		}
		if(StringUtil.isNotBlank(jsrq)){
			str +="jsrq="+jsrq+"&";
		}
		if(StringUtil.isNotBlank(ajlx)){
			str +="ajlx="+ajlx+"&";
		}
		if(StringUtil.isNotBlank(dyr)){
			str +="dyr="+dyr+"&";
		}
		if(StringUtil.isNotBlank(dyrfydm)){
			str +="dyrfydm="+dyrfydm+"&";
		}
		if(StringUtil.isNotBlank(spr)){
			str +="spr="+spr+"&";
		}
		if(StringUtil.isNotBlank(sprfydm)){
			str +="sprfydm="+sprfydm+"&";
		}
		if(StringUtil.isNotBlank(jsr)){
			str +="jsr="+jsr+"&";
		}
		if(str.endsWith("&")){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
}

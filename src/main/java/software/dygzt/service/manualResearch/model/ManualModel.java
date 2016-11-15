package software.dygzt.service.manualResearch.model;

import java.util.Date;

import software.dygzt.util.StringUtil;

public class ManualModel {
	private String ksrq;
	private String jsrq;
	private String sjlx;
	private String fyfw;
	private String ajlx;
	private String dymd;
	private String dyxq;
	private String fjm;
	private byte[] fj;
	private String dyr;
	private String dyrfydm;
	private String dyrphone;
	private String dyrlxfs;
	
	public ManualModel() {
		
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

	public String getSjlx() {
		return sjlx;
	}

	public void setSjlx(String sjlx) {
		if(StringUtil.isNotBlank(sjlx)){
			this.sjlx = sjlx.trim();
		}
	}

	public String getFyfw() {
		return fyfw;
	}

	public void setFyfw(String fyfw) {
		if(StringUtil.isNotBlank(fyfw)){
			this.fyfw = fyfw.trim();
		}
	}

	public String getAjlx() {
		return ajlx;
	}

	public void setAjlx(String ajlx) {
		if(StringUtil.isNotBlank(ajlx)){
			this.ajlx = ajlx.trim();
		}
	}

	public String getDymd() {
		return dymd;
	}

	public void setDymd(String dymd) {
		if(StringUtil.isNotBlank(dymd)){
			this.dymd = dymd.trim();
		}
	}

	public String getDyxq() {
		return dyxq;
	}

	public void setDyxq(String dyxq) {
		if(StringUtil.isNotBlank(dyxq)){
			this.dyxq = dyxq.trim();
		}
	}

	public String getFjm() {
		return fjm;
	}

	public void setFjm(String fjm) {
		if(StringUtil.isNotBlank(fjm)){
			this.fjm = fjm.trim();
		}
	}

	public byte[] getFj() {
		return fj;
	}

	public void setFj(byte[] fj) {
		this.fj = fj;
	}

	public String getDyr() {
		return dyr;
	}

	public void setDyr(String dyr) {
		if(StringUtil.isNotBlank(dyr)){
			this.dyr = dyr.trim();
		}
	}

	public String getDyrfydm() {
		return dyrfydm;
	}

	public void setDyrfydm(String dyrfydm) {
		if(StringUtil.isNotBlank(dyrfydm)){
			this.dyrfydm = dyrfydm.trim();
		}
	}

	public String getDyrphone() {
		return dyrphone;
	}

	public void setDyrphone(String dyrphone) {
		if(StringUtil.isNotBlank(dyrphone)){
			this.dyrphone = dyrphone.trim();
		}
	}

	public String getDyrlxfs() {
		return dyrlxfs;
	}

	public void setDyrlxfs(String dyrlxfs) {
		if(StringUtil.isNotBlank(dyrlxfs)){
			this.dyrlxfs = dyrlxfs.trim();
		}
	}

	
	
}

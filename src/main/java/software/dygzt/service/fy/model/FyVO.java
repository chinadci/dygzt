package software.dygzt.service.fy.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 法院的VO，主要用于传给页面的与法院列表相关的对象
 * 
 *
 */
public class FyVO {

	/**
	 * 法院名称
	 */
	private String fymc;
	/**
	 * 法院代码
	 */
	private String fydm;
	/**
	 * 法院简称
	 */
	private String fyjc;
	/**
	 * 下一级法院列表
	 */
	private List<FyVO> xjfyList;
	/**
	 * 相关代码
	 */
	private String xgdm;
	
	//add by wu
	private short fybh;
	private boolean active = false;
	private boolean hasXjfy = false;
	
	public FyVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FyVO(String fymc, String fydm,String fyjc,String xgdm) {
		super();
		this.fymc = fymc;
		this.fydm = fydm;
		this.fyjc = fyjc;
		this.xgdm=xgdm;
	}
	
	public FyVO(String fymc, String fydm, String fyjc,String xgdm,List<FyVO> xjfyList) {
		super();
		this.fymc = fymc;
		this.fydm = fydm;
		this.fyjc = fyjc;
		this.xgdm=xgdm;
		this.xjfyList = xjfyList;
		if(xjfyList != null && xjfyList.size() >0)
			hasXjfy = true;
	}
	
	public String getFymc() {
		return fymc;
	}
	public void setFymc(String fymc) {
		this.fymc = fymc;
	}
	
	public String getFydm() {
		return fydm;
	}
	public void setFydm(String fydm) {
		this.fydm = fydm;
	}
	public String getFyjc() {
		return fyjc;
	}

	public void setFyjc(String fyjc) {
		this.fyjc = fyjc;
	}
	
	public List<FyVO> getXjfyList() {
		return xjfyList;
	}
	public void setXjfyList(List<FyVO> xjfyList) {
		this.xjfyList = xjfyList;
		if(xjfyList != null && xjfyList.size() >0)
			hasXjfy = true;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getXgdm() {
		return xgdm;
	}

	public void setXgdm(String xgdm) {
		this.xgdm = xgdm;
	}

	public boolean isHasXjfy() {
		return hasXjfy;
	}

	public void setHasXjfy(boolean hasXjfy) {
		this.hasXjfy = hasXjfy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public short getFybh() {
		return fybh;
	}

	public void setFybh(short fybh) {
		this.fybh = fybh;
	}

}
package software.dygzt.service.dmb.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 代码的VO，主要用于传给页面的与代码表相关的对象
 */
public class DmVO {
	/**
	 * 编号
	 */
	public String bh;
	/**
	 * 描述
	 */
	public String ms;
	
	public DmVO() {
		super();
	}
	public DmVO(String bh, String ms) {
		super();
		this.bh = bh;
		this.ms = ms;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

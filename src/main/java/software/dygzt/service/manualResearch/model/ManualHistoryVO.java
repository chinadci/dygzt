package software.dygzt.service.manualResearch.model;

public class ManualHistoryVO {
	//调研日期
	String dyrq;
	//调研目的
	String dymd;
	//开始日期
	String ksrq;
	//结束日期
	String jsrq;
	//调研范围
	String dyfw;
	//调研需求
	String dyxq;
	//调研状态
	String dyzt;
	//操作1
	String btn;

	public ManualHistoryVO() {
		super();
	}
	/*
	 * 调研人、审批人
	 */
	public ManualHistoryVO(String dyrq, String dymd, String ksrq, String jsrq,
			String dyfw, String dyxq, String dyzt, String btn) {
		super();
		this.dyrq = dyrq;
		this.dymd = dymd;
		this.ksrq = ksrq;
		this.jsrq = jsrq;
		this.dyfw = dyfw;
		this.dyxq = dyxq;
		this.dyzt = dyzt;
		this.btn = btn;
	}
	/**
	 * 计算人
	 * @param dyrq
	 * @param dymd
	 * @param ksrq
	 * @param jsrq
	 * @param dyfw
	 * @param dyxq
	 * @param btn
	 */
	public ManualHistoryVO(String dyrq, String dymd, String ksrq, String jsrq,
			String dyfw, String dyxq, String btn) {
		super();
		this.dyrq = dyrq;
		this.dymd = dymd;
		this.ksrq = ksrq;
		this.jsrq = jsrq;
		this.dyfw = dyfw;
		this.dyxq = dyxq;
		this.btn = btn;
	}
	public String getDyrq() {
		return dyrq;
	}
	public void setDyrq(String dyrq) {
		this.dyrq = dyrq;
	}
	public String getDymd() {
		return dymd;
	}
	public void setDymd(String dymd) {
		this.dymd = dymd;
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
	public String getDyfw() {
		return dyfw;
	}
	public void setDyfw(String dyfw) {
		this.dyfw = dyfw;
	}
	public String getDyxq() {
		return dyxq;
	}
	public void setDyxq(String dyxq) {
		this.dyxq = dyxq;
	}
	public String getDyzt() {
		return dyzt;
	}
	public void setDyzt(String dyzt) {
		this.dyzt = dyzt;
	}
	public String getBtn() {
		return btn;
	}
	public void setBtn(String btn) {
		this.btn = btn;
	}
	
}

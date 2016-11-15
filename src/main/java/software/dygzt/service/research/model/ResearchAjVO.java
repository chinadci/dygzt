package software.dygzt.service.research.model;

public class ResearchAjVO {
	private int xh;
	/**
	 * 案号
	 */
	private String ah;
	/**
	 * 案号(超链接)
	 */
	private String ahstr;
	/**
	 * 案件名称
	 */
	private String ajmc;
	/**
	 * 立案时间
	 */
	private String larq;
	/**
	 * 结案时间
	 */
	private String jarq;
	public ResearchAjVO() {
	}
	
	public int getXh() {
		return xh;
	}

	public void setXh(int xh) {
		this.xh = xh;
	}

	public String getAh() {
		return ah;
	}
	public void setAh(String ah) {
		this.ah = ah;
	}
	public String getAhstr() {
		return ahstr;
	}
	public void setAhstr(String ahstr) {
		this.ahstr = ahstr;
	}
	public String getAjmc() {
		return ajmc;
	}
	public void setAjmc(String ajmc) {
		this.ajmc = ajmc;
	}
	public String getLarq() {
		return larq;
	}
	public void setLarq(String larq) {
		this.larq = larq;
	}
	public String getJarq() {
		return jarq;
	}
	public void setJarq(String jarq) {
		this.jarq = jarq;
	}
	
}

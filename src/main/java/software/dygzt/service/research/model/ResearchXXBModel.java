package software.dygzt.service.research.model;

public class ResearchXXBModel{
	private String xxmc;
	private String xsmc;
	private String condition;
	private String col;
	private String username;
	private String userfydm;
	private String bbbh;
	
	public ResearchXXBModel() {
	}
	
	public ResearchXXBModel(String xxmc, String xsmc, String condition,
			String col, String username, String userfydm,String bbbh) {
		this.xxmc = xxmc;
		this.xsmc = xsmc;
		this.condition = condition;
		this.col = col;
		this.username = username;
		this.userfydm = userfydm;
		this.bbbh = bbbh;
	}
	public String getXxmc() {
		return xxmc;
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	public String getXsmc() {
		return xsmc;
	}
	public void setXsmc(String xsmc) {
		this.xsmc = xsmc;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserfydm() {
		return userfydm;
	}
	public void setUserfydm(String userfydm) {
		this.userfydm = userfydm;
	}
	public String getBbbh() {
		return bbbh;
	}
	public void setBbbh(String bbbh) {
		this.bbbh = bbbh;
	}
}
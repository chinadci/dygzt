package software.dygzt.service.research.model;

public class ResearchXXZBModel{

	private String lbmc;
	private String xxs;
	private String username;
	private String userfydm;

	public ResearchXXZBModel() {
	}

	public ResearchXXZBModel(String lbmc, String xxs, String username,
			String userfydm) {
		this.lbmc = lbmc;
		this.xxs = xxs;
		this.username = username;
		this.userfydm = userfydm;
	}

	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	public String getXxs() {
		return xxs;
	}

	public void setXxs(String xxs) {
		this.xxs = xxs;
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
	
}
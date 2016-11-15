package software.dygzt.service.research.model;

public class ResearchTableDefModel{
	private String bbbh;
	private String bbmc;
	private String bblx;
	private String username;
	private String userfydm;
	private String condition;
	private String colCondition;
	private String rowCondition;
	private String rowConditionAyjzrh;

	public ResearchTableDefModel() {
	}
	
	public ResearchTableDefModel(String bbbh, String bbmc, String bblx,
			String username, String userfydm, String condition,
			String colCondition, String rowCondition, String rowConditionAyjzrh) {
		this.bbbh = bbbh;
		this.bbmc = bbmc;
		this.bblx = bblx;
		this.username = username;
		this.userfydm = userfydm;
		this.condition = condition;
		this.colCondition = colCondition;
		this.rowCondition = rowCondition;
		this.rowConditionAyjzrh = rowConditionAyjzrh;
	}

	public String getBbbh() {
		return bbbh;
	}

	public void setBbbh(String bbbh) {
		this.bbbh = bbbh;
	}

	public String getBbmc() {
		return bbmc;
	}

	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	public String getBblx() {
		return bblx;
	}

	public void setBblx(String bblx) {
		this.bblx = bblx;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getColCondition() {
		return colCondition;
	}

	public void setColCondition(String colCondition) {
		this.colCondition = colCondition;
	}

	public String getRowCondition() {
		return rowCondition;
	}

	public void setRowCondition(String rowCondition) {
		this.rowCondition = rowCondition;
	}
	
	public String getRowConditionAyjzrh() {
		return rowConditionAyjzrh;
	}

	public void setRowConditionAyjzrh(String rowConditionAyjzrh) {
		this.rowConditionAyjzrh = rowConditionAyjzrh;
	}

}
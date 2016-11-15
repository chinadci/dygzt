package software.dygzt.data.research.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ResearchTableDefDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_AUTO_BTXXB")
public class ResearchTableDefDO implements java.io.Serializable {

	// Fields

	private String bbbh;
	private String bbmc;
	private String bblx;
	private String username;
	private String userfydm;
	private String condition;
	private String colCondition;
	private String rowCondition;
	private String rowConditionAyjzrh;

	// Constructors

	/** default constructor */
	public ResearchTableDefDO() {
	}

	/** minimal constructor */
	public ResearchTableDefDO(String bbbh, String bbmc) {
		this.bbbh = bbbh;
		this.bbmc = bbmc;
	}

	/** full constructor */
	public ResearchTableDefDO(String bbbh, String bbmc, String bblx,
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

	// Property accessors
	@Id
	@Column(name = "bbbh", unique = true, nullable = false, length = 20)
	public String getBbbh() {
		return this.bbbh;
	}

	public void setBbbh(String bbbh) {
		this.bbbh = bbbh;
	}

	@Column(name = "bbmc", nullable = false, length = 50)
	public String getBbmc() {
		return this.bbmc;
	}

	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	@Column(name = "bblx", length = 10)
	public String getBblx() {
		return this.bblx;
	}

	public void setBblx(String bblx) {
		this.bblx = bblx;
	}

	@Column(name = "username", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "userfydm", length = 20)
	public String getUserfydm() {
		return this.userfydm;
	}

	public void setUserfydm(String userfydm) {
		this.userfydm = userfydm;
	}

	@Column(name = "condition")
	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name = "colCondition")
	public String getColCondition() {
		return this.colCondition;
	}

	public void setColCondition(String colCondition) {
		this.colCondition = colCondition;
	}

	@Column(name = "rowCondition")
	public String getRowCondition() {
		return this.rowCondition;
	}

	public void setRowCondition(String rowCondition) {
		this.rowCondition = rowCondition;
	}
	
	@Column(name = "rowCondition_ay_jzrh")
	public String getRowConditionAyjzrh() {
		return rowConditionAyjzrh;
	}

	public void setRowConditionAyjzrh(String rowConditionAyjzrh) {
		this.rowConditionAyjzrh = rowConditionAyjzrh;
	}

}
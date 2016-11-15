package software.dygzt.data.research.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ResearchXXBDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_AUTO_XXB")
public class ResearchXXBDO implements java.io.Serializable {

	// Fields

	private String xxmc;
	private String xsmc;
	private String condition;
	private String col;
	private String username;
	private String userfydm;
	private String bbbh;
	
	// Constructors

	/** default constructor */
	public ResearchXXBDO() {
	}

	/** minimal constructor */
	public ResearchXXBDO(String xxmc, String xsmc) {
		this.xxmc = xxmc;
		this.xsmc = xsmc;
	}

	/** full constructor */
	public ResearchXXBDO(String xxmc, String xsmc, String condition,
			String col, String username, String userfydm,String bbbh) {
		this.xxmc = xxmc;
		this.xsmc = xsmc;
		this.condition = condition;
		this.col = col;
		this.username = username;
		this.userfydm = userfydm;
		this.bbbh = bbbh;
	}

	// Property accessors
	@Id
	@Column(name = "xxmc", unique = true, nullable = false, length = 20)
	public String getXxmc() {
		return this.xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	@Column(name = "xsmc", nullable = false, length = 20)
	public String getXsmc() {
		return this.xsmc;
	}

	public void setXsmc(String xsmc) {
		this.xsmc = xsmc;
	}

	@Column(name = "condition")
	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name = "col", length = 20)
	public String getCol() {
		return this.col;
	}

	public void setCol(String col) {
		this.col = col;
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

	@Column(name = "bbbh", length = 20)
	public String getBbbh() {
		return bbbh;
	}

	public void setBbbh(String bbbh) {
		this.bbbh = bbbh;
	}
}
package software.dygzt.data.research.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ResearchXXZBDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_AUTO_XXZB")
public class ResearchXXZBDO implements java.io.Serializable {

	// Fields

	private String lbmc;
	private String xxs;
	private String username;
	private String userfydm;

	// Constructors

	/** default constructor */
	public ResearchXXZBDO() {
	}

	/** minimal constructor */
	public ResearchXXZBDO(String lbmc, String xxs) {
		this.lbmc = lbmc;
		this.xxs = xxs;
	}

	/** full constructor */
	public ResearchXXZBDO(String lbmc, String xxs, String username,
			String userfydm) {
		this.lbmc = lbmc;
		this.xxs = xxs;
		this.username = username;
		this.userfydm = userfydm;
	}

	// Property accessors
	@Id
	@Column(name = "lbmc", unique = true, nullable = false, length = 20)
	public String getLbmc() {
		return this.lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	@Column(name = "xxs", nullable = false)
	public String getXxs() {
		return this.xxs;
	}

	public void setXxs(String xxs) {
		this.xxs = xxs;
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
	
}
package software.dygzt.data.dmb.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * DmbDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_DMB")
@IdClass(DyDmbDOId.class)
public class DyDmbDO implements java.io.Serializable {
	// Fields

	private String lbbh;
	private String dmbh;
	
	private String dmms;
	
	// Constructors

	/** default constructor */
	public DyDmbDO() {
	}

	/** minimal constructor */
	public DyDmbDO(String lbbh, String dmbh) {
		this.lbbh = lbbh;
		this.dmbh = dmbh;
	}
	
	/** full constructor1 */
	public DyDmbDO(String lbbh, String dmbh, String dmms) {
		this.lbbh = lbbh;
		this.dmbh = dmbh;
		this.dmms = dmms;
	}


	// Property accessors
	@Column(name = "lbbh", nullable = false, length = 20)
	@Id
	public String getLbbh() {
		return this.lbbh;
	}

	public void setLbbh(String lbbh) {
		this.lbbh = lbbh;
	}

	@Column(name = "dmbh", nullable = false, length = 20)
	@Id
	public String getDmbh() {
		return this.dmbh;
	}

	public void setDmbh(String dmbh) {
		this.dmbh = dmbh;
	}

	@Column(name = "dmms", length = 250)
	public String getDmms() {
		return this.dmms;
	}

	public void setDmms(String dmms) {
		this.dmms = dmms;
	}

}
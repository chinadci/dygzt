package software.dygzt.data.research.dataobject;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * ResearchSavedInfoDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_AUTO_SAVEDINFO")
@IdClass(ResearchSavedInfoDOId.class)
public class ResearchSavedInfoDO implements java.io.Serializable {

	// Fields

	private Integer tableid;
	private String dyr;
	private String dyrfydm;
	private Date dyrq;
	private String bgms;
	private Integer type;

	// Constructors

	/** default constructor */
	public ResearchSavedInfoDO() {
	}

	/** minimal constructor */
	public ResearchSavedInfoDO(Integer tableid, String dyr, String dyrfydm,
			Date dyrq) {
		this.tableid = tableid;
		this.dyr = dyr;
		this.dyrfydm = dyrfydm;
		this.dyrq = dyrq;
	}

	/** full constructor */
	public ResearchSavedInfoDO(Integer tableid, String dyr, String dyrfydm,
			Date dyrq, String bgms,Integer type) {
		this.tableid = tableid;
		this.dyr = dyr;
		this.dyrfydm = dyrfydm;
		this.dyrq = dyrq;
		this.bgms = bgms;
		this.type = type;
	}

	@Column(name = "tableid", nullable = false)
	@Id
	public Integer getTableid() {
		return tableid;
	}

	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}
	
	@Column(name = "dyr", nullable = false, length = 20)
	@Id
	public String getDyr() {
		return dyr;
	}

	public void setDyr(String dyr) {
		this.dyr = dyr;
	}
	
	@Column(name = "dyrfydm", nullable = false, length = 20)
	@Id
	public String getDyrfydm() {
		return dyrfydm;
	}

	public void setDyrfydm(String dyrfydm) {
		this.dyrfydm = dyrfydm;
	}

	@Column(name = "dyrq", nullable = false, length = 23)
	public Date getDyrq() {
		return this.dyrq;
	}

	public void setDyrq(Date dyrq) {
		this.dyrq = dyrq;
	}

	@Column(name = "bgms")
	public String getBgms() {
		return this.bgms;
	}

	public void setBgms(String bgms) {
		this.bgms = bgms;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
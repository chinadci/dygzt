package software.dygzt.data.research.dataobject;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * researchTableDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_AUTO_TABLE")
public class ResearchTableDO implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bbmc;
	private String dytj;
	private Date ksrq;
	private Date jsrq;
	private String ajzt;
	private Integer rowlevel;
	private Integer collevel;
	private String sbfy;
	private Integer type;
	
	// Constructors

	/** default constructor */
	public ResearchTableDO() {
	}

	/** minimal constructor */
	public ResearchTableDO(Integer id, String bbmc, String dytj,
			Date ksrq, Date jsrq, String ajzt,
			Integer rowlevel, Integer collevel) {
		this.id = id;
		this.bbmc = bbmc;
		this.dytj = dytj;
		this.ksrq = ksrq;
		this.jsrq = jsrq;
		this.ajzt = ajzt;
		this.rowlevel = rowlevel;
		this.collevel = collevel;
	}
	/** full constructor */
	public ResearchTableDO(Integer id, String bbmc, String dytj,
			Date ksrq, Date jsrq, String ajzt,
			Integer rowlevel, Integer collevel, String sbfy,Integer type) {
		this.id = id;
		this.bbmc = bbmc;
		this.dytj = dytj;
		this.ksrq = ksrq;
		this.jsrq = jsrq;
		this.ajzt = ajzt;
		this.rowlevel = rowlevel;
		this.collevel = collevel;
		this.sbfy = sbfy;
		this.type = type;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "bbmc", nullable = false, length = 50)
	public String getBbmc() {
		return this.bbmc;
	}

	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	@Column(name = "dytj", nullable = false)
	public String getDytj() {
		return this.dytj;
	}

	public void setDytj(String dytj) {
		this.dytj = dytj;
	}

	@Column(name = "ksrq", nullable = false, length = 23)
	public Date getKsrq() {
		return this.ksrq;
	}

	public void setKsrq(Date ksrq) {
		this.ksrq = ksrq;
	}

	@Column(name = "jsrq", nullable = false, length = 23)
	public Date getJsrq() {
		return this.jsrq;
	}

	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}

	@Column(name = "ajzt", length = 10)
	public String getAjzt() {
		return this.ajzt;
	}

	public void setAjzt(String ajzt) {
		this.ajzt = ajzt;
	}

	@Column(name = "rowlevel", nullable = false)
	public Integer getRowlevel() {
		return this.rowlevel;
	}

	public void setRowlevel(Integer rowlevel) {
		this.rowlevel = rowlevel;
	}

	@Column(name = "collevel", nullable = false)
	public Integer getCollevel() {
		return this.collevel;
	}

	public void setCollevel(Integer collevel) {
		this.collevel = collevel;
	}
	
	@Column(name = "sbfy", length = 255)
	public String getSbfy() {
		return sbfy;
	}

	public void setSbfy(String sbfy) {
		this.sbfy = sbfy;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
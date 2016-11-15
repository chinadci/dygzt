package software.dygzt.data.ajlx.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AjlxbDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_AJLXB")
public class AjlxbDO implements java.io.Serializable {

	// Fields

	private String id;
	private String sjid;
	private String mc;
	private String dz;

	// Constructors

	/** default constructor */
	public AjlxbDO() {
	}

	/** minimal constructor */
	public AjlxbDO(String id, String mc) {
		this.id = id;
		this.mc = mc;
	}

	/** full constructor */
	public AjlxbDO(String id, String sjid, String mc, String dz) {
		this.id = id;
		this.sjid = sjid;
		this.mc = mc;
		this.dz = dz;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 20)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "sjid", length = 20)
	public String getSjid() {
		return this.sjid;
	}

	public void setSjid(String sjid) {
		this.sjid = sjid;
	}

	@Column(name = "mc", nullable = false, length = 50)
	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	@Column(name = "dz", length = 20)
	public String getDz() {
		return this.dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

}
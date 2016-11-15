package software.dygzt.data.user.dataobject;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * DyXtyhDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_XTYHB")
@IdClass(DyXtyhDOId.class)
public class DyXtyhDO implements java.io.Serializable {

	// Fields

	private String yhm;
	private String fydm;
	
	private String password;
	private String name;
	private String bm;
	private String qx;
	private String phone;
	private String lxfs;

	// Constructors

	/** default constructor */
	public DyXtyhDO() {
	}

	/** full constructor */
	public DyXtyhDO(String yhm, String fydm, String password, String name,
			String bm, String qx, String phone, String lxfs) {
		super();
		this.yhm = yhm;
		this.fydm = fydm;
		this.password = password;
		this.name = name;
		this.bm = bm;
		this.qx = qx;
		this.phone = phone;
		this.lxfs = lxfs;
	}
	
	@Column(name = "yhm", nullable = false, length = 20)
	@Id
	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	@Column(name = "fydm", nullable = false, length = 20)
	@Id
	public String getFydm() {
		return fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	@Column(name = "password", length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "bm", length = 20)
	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	@Column(name = "qx", nullable = false, length = 50)
	public String getQx() {
		return this.qx;
	}

	public void setQx(String qx) {
		this.qx = qx;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "lxfs", length = 20)
	public String getLxfs() {
		return this.lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

}
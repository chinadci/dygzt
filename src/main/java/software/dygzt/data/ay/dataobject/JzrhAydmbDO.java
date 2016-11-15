package software.dygzt.data.ay.dataobject;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JzrhAydmbDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_AYDMB")
public class JzrhAydmbDO implements java.io.Serializable {

	// Fields

	private String aydmbh;
	private String bbh;
	private String aymc;
	private String aylb;
	private String bz;
	private Date xgsj;
	private String dqbs;
	private Integer aydmbbh;
	private String sjdm;
	private String ayzlx;
	private Integer sftjay;

	// Constructors

	/** default constructor */
	public JzrhAydmbDO() {
	}

	/** minimal constructor */
	public JzrhAydmbDO(String aydmbh) {
		this.aydmbh = aydmbh;
	}

	/** full constructor */
	public JzrhAydmbDO(String aydmbh, String bbh, String aymc, String aylb,
			String bz, Date xgsj, String dqbs, Integer aydmbbh, String sjdm,
			String ayzlx, Integer sftjay) {
		this.aydmbh = aydmbh;
		this.bbh = bbh;
		this.aymc = aymc;
		this.aylb = aylb;
		this.bz = bz;
		this.xgsj = xgsj;
		this.dqbs = dqbs;
		this.aydmbbh = aydmbbh;
		this.sjdm = sjdm;
		this.ayzlx = ayzlx;
		this.sftjay = sftjay;
	}

	// Property accessors
	@Id
	@Column(name = "AYDMBH", unique = true, nullable = false, length = 10)
	public String getAydmbh() {
		return this.aydmbh;
	}

	public void setAydmbh(String aydmbh) {
		this.aydmbh = aydmbh;
	}

	@Column(name = "BBH", length = 2)
	public String getBbh() {
		return this.bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
	}

	@Column(name = "AYMC")
	public String getAymc() {
		return this.aymc;
	}

	public void setAymc(String aymc) {
		this.aymc = aymc;
	}

	@Column(name = "AYLB", length = 10)
	public String getAylb() {
		return this.aylb;
	}

	public void setAylb(String aylb) {
		this.aylb = aylb;
	}

	@Column(name = "BZ")
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "XGSJ", length = 19)
	public Date getXgsj() {
		return this.xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	@Column(name = "DQBS", length = 1)
	public String getDqbs() {
		return this.dqbs;
	}

	public void setDqbs(String dqbs) {
		this.dqbs = dqbs;
	}

	@Column(name = "AYDMBBH")
	public Integer getAydmbbh() {
		return this.aydmbbh;
	}

	public void setAydmbbh(Integer aydmbbh) {
		this.aydmbbh = aydmbbh;
	}

	@Column(name = "SJDM", length = 10)
	public String getSjdm() {
		return this.sjdm;
	}

	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}

	@Column(name = "AYZLX", length = 10)
	public String getAyzlx() {
		return this.ayzlx;
	}

	public void setAyzlx(String ayzlx) {
		this.ayzlx = ayzlx;
	}

	@Column(name = "SFTJAY")
	public Integer getSftjay() {
		return this.sftjay;
	}

	public void setSftjay(Integer sftjay) {
		this.sftjay = sftjay;
	}

}
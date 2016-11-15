package software.dygzt.data.ay.dataobject;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * AydmbDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_AYDMB")
@IdClass(AydmbDOId.class)
public class AydmbDO implements java.io.Serializable {

	// Fields

	private String bbh;
	private String dmbh;
	private String dmms;
	private String aydm4;
	private Integer aydm5;
	private String sfsx;
	private String aylb;
	private String txh;
	private String kxh;
	private String bz1;
	private String bz2;
	private Integer aydm8;
	private Integer aydm11;
	private Date xgsj;
	private String aymc;
	private String bz;
	private String dqbs;
	private String sjdm;

	// Constructors

	/** default constructor */
	public AydmbDO() {
	}

	/** minimal constructor */
	/** minimal constructor */
	public AydmbDO(String bbh, String dmbh, Date xgsj) {
		this.bbh = bbh;
		this.dmbh = dmbh;
		this.xgsj = xgsj;
	}

	public AydmbDO(String bbh, String dmbh, String aymc, String aylb, String bz) {
		this.bbh = bbh;
		this.dmbh = dmbh;
		this.aymc = aymc;
		this.aylb = aylb;
		this.bz = bz;
	}
	
	public AydmbDO(String bbh, String dmbh, Date xgsj,
			String dqbs) {
		this.bbh = bbh;
		this.dmbh = dmbh;
		this.xgsj = xgsj;
		this.dqbs = dqbs;
	}

	/** full constructor */
	public AydmbDO(String bbh, String dmbh, String dmms,
			String aydm4, Integer aydm5, String sfsx, String aylb, String txh,
			String kxh, String bz1, String bz2, Integer aydm8, Integer aydm11,
			Date xgsj, String aymc, String bz, String dqbs, String sjdm) {
		this.bbh = bbh;
		this.dmbh = dmbh;
		this.dmms = dmms;
		this.aydm4 = aydm4;
		this.aydm5 = aydm5;
		this.sfsx = sfsx;
		this.aylb = aylb;
		this.txh = txh;
		this.kxh = kxh;
		this.bz1 = bz1;
		this.bz2 = bz2;
		this.aydm8 = aydm8;
		this.aydm11 = aydm11;
		this.xgsj = xgsj;
		this.aymc = aymc;
		this.bz = bz;
		this.dqbs = dqbs;
		this.sjdm = sjdm;
	}

	// Property accessors
	@Id
	@Column(name = "BBH", nullable = false, length = 2)
	public String getBbh() {
		return this.bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
	}
	@Id
	@Column(name = "DMBH", nullable = false, length = 10)
	public String getDmbh() {
		return this.dmbh;
	}

	public void setDmbh(String dmbh) {
		this.dmbh = dmbh;
	}

	@Column(name = "DMMS")
	public String getDmms() {
		return this.dmms;
	}

	public void setDmms(String dmms) {
		this.dmms = dmms;
	}

	@Column(name = "AYDM_4", length = 10)
	public String getAydm4() {
		return this.aydm4;
	}

	public void setAydm4(String aydm4) {
		this.aydm4 = aydm4;
	}

	@Column(name = "AYDM_5")
	public Integer getAydm5() {
		return this.aydm5;
	}

	public void setAydm5(Integer aydm5) {
		this.aydm5 = aydm5;
	}

	@Column(name = "SFSX", length = 1)
	public String getSfsx() {
		return this.sfsx;
	}

	public void setSfsx(String sfsx) {
		this.sfsx = sfsx;
	}

	@Column(name = "AYLB", length = 10)
	public String getAylb() {
		return this.aylb;
	}

	public void setAylb(String aylb) {
		this.aylb = aylb;
	}

	@Column(name = "TXH", length = 40)
	public String getTxh() {
		return this.txh;
	}

	public void setTxh(String txh) {
		this.txh = txh;
	}

	@Column(name = "KXH", length = 40)
	public String getKxh() {
		return this.kxh;
	}

	public void setKxh(String kxh) {
		this.kxh = kxh;
	}

	@Column(name = "BZ1")
	public String getBz1() {
		return this.bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

	@Column(name = "BZ2")
	public String getBz2() {
		return this.bz2;
	}

	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}

	@Column(name = "AYDM_8")
	public Integer getAydm8() {
		return this.aydm8;
	}

	public void setAydm8(Integer aydm8) {
		this.aydm8 = aydm8;
	}

	@Column(name = "AYDM_11")
	public Integer getAydm11() {
		return this.aydm11;
	}

	public void setAydm11(Integer aydm11) {
		this.aydm11 = aydm11;
	}

	@Column(name = "XGSJ", nullable = false, length = 23)
	public Date getXgsj() {
		return this.xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	@Column(name = "AYMC")
	public String getAymc() {
		return this.aymc;
	}

	public void setAymc(String aymc) {
		this.aymc = aymc;
	}

	@Column(name = "BZ")
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "DQBS", nullable = false, length = 1)
	public String getDqbs() {
		return this.dqbs;
	}

	public void setDqbs(String dqbs) {
		this.dqbs = dqbs;
	}

	@Column(name = "SJDM", length = 10)
	public String getSjdm() {
		return this.sjdm;
	}

	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}

}
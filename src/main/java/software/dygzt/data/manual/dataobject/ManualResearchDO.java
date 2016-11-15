package software.dygzt.data.manual.dataobject;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ManualResearchDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_RGDYXXB")
public class ManualResearchDO implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date dyrq;
	private Date ksrq;
	private Date jsrq;
	private String sjlx;
	private String dyzt;
	private String fyfw;
	private String ajlx;
	private String dymd;
	private String dyxq;
	private String fjm;
	private byte[] fj;
	private String dyr;
	private String dyrfydm;
	private String dyrphone;
	private String dyrlxfs;
	private String spr;
	private String sprfydm;
	private String spryj;
	private String sprthyy;
	private String jsr;
	private String jsrfydm;
	private String dyjgm;
	private byte[] dyjg;
	private String dydmm;
	private byte[] dydm;
	private Date sprspsj;
	private Date jsrjssj;
	private String zpr;
	private String zprfydm;
	private Date zpsj;

	// Constructors

	/** default constructor */
	public ManualResearchDO() {
	}

	/** minimal constructor */
	public ManualResearchDO(Integer id, Date dyrq, Date ksrq, Date jsrq,
			String dyzt, String fyfw, String dymd,
			String dyxq, String sprthyy) {
		this.id = id;
		this.dyrq = dyrq;
		this.ksrq = ksrq;
		this.jsrq = jsrq;
		this.dyzt = dyzt;
		this.fyfw = fyfw;
		this.dymd = dymd;
		this.dyxq = dyxq;
		this.sprthyy = sprthyy;
	}

	/** full constructor */
	public ManualResearchDO(Date dyrq, Date ksrq, Date jsrq, String sjlx,
			String dyzt, String fyfw, String ajlx, String dymd, String dyxq,
			String fjm, byte[] fj, String dyr, String dyrfydm, String dyrphone,
			String dyrlxfs, String spr, String sprfydm, String spryj,
			String sprthyy, String jsr, String jsrfydm, String dyjgm,
			byte[] dyjg, String dydmm, byte[] dydm, Date sprspsj, Date jsrjssj,
			String zpr, String zprfydm, Date zpsj) {
		this.dyrq = dyrq;
		this.ksrq = ksrq;
		this.jsrq = jsrq;
		this.sjlx = sjlx;
		this.dyzt = dyzt;
		this.fyfw = fyfw;
		this.ajlx = ajlx;
		this.dymd = dymd;
		this.dyxq = dyxq;
		this.fjm = fjm;
		this.fj = fj;
		this.dyr = dyr;
		this.dyrfydm = dyrfydm;
		this.dyrphone = dyrphone;
		this.dyrlxfs = dyrlxfs;
		this.spr = spr;
		this.sprfydm = sprfydm;
		this.spryj = spryj;
		this.sprthyy = sprthyy;
		this.jsr = jsr;
		this.jsrfydm = jsrfydm;
		this.dyjgm = dyjgm;
		this.dyjg = dyjg;
		this.dydmm = dydmm;
		this.dydm = dydm;
		this.sprspsj = sprspsj;
		this.jsrjssj = jsrjssj;
		this.zpr = zpr;
		this.zprfydm = zprfydm;
		this.zpsj = zpsj;
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

	@Column(name = "dyrq", nullable = false, length = 23)
	public Date getDyrq() {
		return this.dyrq;
	}

	public void setDyrq(Date dyrq) {
		this.dyrq = dyrq;
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

	@Column(name = "sjlx", nullable = false, length = 20)
	public String getSjlx() {
		return this.sjlx;
	}

	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}

	@Column(name = "dyzt", nullable = false, length = 20)
	public String getDyzt() {
		return this.dyzt;
	}

	public void setDyzt(String dyzt) {
		this.dyzt = dyzt;
	}

	@Column(name = "fyfw", nullable = false, length = 30)
	public String getFyfw() {
		return this.fyfw;
	}

	public void setFyfw(String fyfw) {
		this.fyfw = fyfw;
	}

	@Column(name = "ajlx", nullable = false)
	public String getAjlx() {
		return this.ajlx;
	}

	public void setAjlx(String ajlx) {
		this.ajlx = ajlx;
	}

	@Column(name = "dymd", nullable = false)
	public String getDymd() {
		return this.dymd;
	}

	public void setDymd(String dymd) {
		this.dymd = dymd;
	}

	@Column(name = "dyxq", nullable = false)
	public String getDyxq() {
		return this.dyxq;
	}

	public void setDyxq(String dyxq) {
		this.dyxq = dyxq;
	}

	@Column(name = "fjm", length = 200)
	public String getFjm() {
		return this.fjm;
	}

	public void setFjm(String fjm) {
		this.fjm = fjm;
	}

	@Column(name = "fj")
	public byte[] getFj() {
		return this.fj;
	}

	public void setFj(byte[] fj) {
		this.fj = fj;
	}

	@Column(name = "dyr", nullable = false, length = 50)
	public String getDyr() {
		return this.dyr;
	}

	public void setDyr(String dyr) {
		this.dyr = dyr;
	}

	@Column(name = "dyrfydm", nullable = false, length = 20)
	public String getDyrfydm() {
		return this.dyrfydm;
	}

	public void setDyrfydm(String dyrfydm) {
		this.dyrfydm = dyrfydm;
	}

	@Column(name = "dyrphone", length = 20)
	public String getDyrphone() {
		return this.dyrphone;
	}

	public void setDyrphone(String dyrphone) {
		this.dyrphone = dyrphone;
	}

	@Column(name = "dyrlxfs", length = 20)
	public String getDyrlxfs() {
		return this.dyrlxfs;
	}

	public void setDyrlxfs(String dyrlxfs) {
		this.dyrlxfs = dyrlxfs;
	}

	@Column(name = "spr", length = 50)
	public String getSpr() {
		return this.spr;
	}

	public void setSpr(String spr) {
		this.spr = spr;
	}

	@Column(name = "sprfydm", length = 20)
	public String getSprfydm() {
		return this.sprfydm;
	}

	public void setSprfydm(String sprfydm) {
		this.sprfydm = sprfydm;
	}

	@Column(name = "spryj", length = 2)
	public String getSpryj() {
		return this.spryj;
	}

	public void setSpryj(String spryj) {
		this.spryj = spryj;
	}

	@Column(name = "sprthyy", length = 200)
	public String getSprthyy() {
		return this.sprthyy;
	}

	public void setSprthyy(String sprthyy) {
		this.sprthyy = sprthyy;
	}

	@Column(name = "jsr", length = 50)
	public String getJsr() {
		return this.jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	@Column(name = "jsrfydm", length = 20)
	public String getJsrfydm() {
		return this.jsrfydm;
	}

	public void setJsrfydm(String jsrfydm) {
		this.jsrfydm = jsrfydm;
	}

	@Column(name = "dyjgm", length = 200)
	public String getDyjgm() {
		return this.dyjgm;
	}

	public void setDyjgm(String dyjgm) {
		this.dyjgm = dyjgm;
	}

	@Column(name = "dyjg")
	public byte[] getDyjg() {
		return this.dyjg;
	}

	public void setDyjg(byte[] dyjg) {
		this.dyjg = dyjg;
	}

	@Column(name = "dydmm", length = 200)
	public String getDydmm() {
		return this.dydmm;
	}

	public void setDydmm(String dydmm) {
		this.dydmm = dydmm;
	}

	@Column(name = "dydm")
	public byte[] getDydm() {
		return this.dydm;
	}

	public void setDydm(byte[] dydm) {
		this.dydm = dydm;
	}

	@Column(name = "sprspsj", length = 23)
	public Date getSprspsj() {
		return this.sprspsj;
	}

	public void setSprspsj(Date sprspsj) {
		this.sprspsj = sprspsj;
	}

	@Column(name = "jsrjssj", length = 23)
	public Date getJsrjssj() {
		return this.jsrjssj;
	}

	public void setJsrjssj(Date jsrjssj) {
		this.jsrjssj = jsrjssj;
	}

	@Column(name = "zpr", length = 50)
	public String getZpr() {
		return this.zpr;
	}

	public void setZpr(String zpr) {
		this.zpr = zpr;
	}

	@Column(name = "zprfydm", length = 20)
	public String getZprfydm() {
		return this.zprfydm;
	}

	public void setZprfydm(String zprfydm) {
		this.zprfydm = zprfydm;
	}

	@Column(name = "zpsj", length = 23)
	public Date getZpsj() {
		return this.zpsj;
	}

	public void setZpsj(Date zpsj) {
		this.zpsj = zpsj;
	}

}
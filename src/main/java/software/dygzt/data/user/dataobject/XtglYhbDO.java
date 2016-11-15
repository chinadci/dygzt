package software.dygzt.data.user.dataobject;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * XtglYhbDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_XTGL_YHB")
public class XtglYhbDO implements java.io.Serializable {

	// Fields

	private Integer yhbh;
	private String yhdm;
	private String yhmc;
	private String yhkl;
	private String yhbm;
	private String klts;
	private String klda;
	private String yhsf;
	private Date klszsj;
	private Date qjkssj;
	private Date qjjssj;
	private String qjyy;
	private Integer grnzb;
	private Integer qtnzb;
	private Integer grbajs;
	private String phone;
	private String xfqx;
	private String ssfwzxqx;
	private byte[] dzqmImg;
	private String jbxxjCode;
	private Integer fazt;
	private Integer fasl;
	private Integer fybh;
	private Integer yhzt;
	private String cocall;
	
	// Constructors

	/** default constructor */
	public XtglYhbDO() {
	}

	/** minimal constructor */
	public XtglYhbDO(Integer yhbh) {
		this.yhbh = yhbh;
	}

	/** full constructor */
	

	// Property accessors
	@Id
	@Column(name = "YHBH", unique = true, nullable = false)
	public Integer getYhbh() {
		return this.yhbh;
	}

	public XtglYhbDO(Integer yhbh, String yhdm, String yhmc, String yhkl,
			String yhbm, String klts, String klda, String yhsf, Date klszsj,
			Date qjkssj, Date qjjssj, String qjyy, Integer grnzb,
			Integer qtnzb, Integer grbajs, String phone, String xfqx,
			String ssfwzxqx, byte[] dzqmImg, String jbxxjCode, Integer fazt,
			Integer fasl, Integer fybh, Integer yhzt, String cocall) {
		this.yhbh = yhbh;
		this.yhdm = yhdm;
		this.yhmc = yhmc;
		this.yhkl = yhkl;
		this.yhbm = yhbm;
		this.klts = klts;
		this.klda = klda;
		this.yhsf = yhsf;
		this.klszsj = klszsj;
		this.qjkssj = qjkssj;
		this.qjjssj = qjjssj;
		this.qjyy = qjyy;
		this.grnzb = grnzb;
		this.qtnzb = qtnzb;
		this.grbajs = grbajs;
		this.phone = phone;
		this.xfqx = xfqx;
		this.ssfwzxqx = ssfwzxqx;
		this.dzqmImg = dzqmImg;
		this.jbxxjCode = jbxxjCode;
		this.fazt = fazt;
		this.fasl = fasl;
		this.fybh = fybh;
		this.yhzt = yhzt;
		this.cocall = cocall;
	}

	public void setYhbh(Integer yhbh) {
		this.yhbh = yhbh;
	}

	@Column(name = "YHDM", length = 10)
	public String getYhdm() {
		return this.yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	@Column(name = "YHMC", length = 50)
	public String getYhmc() {
		return this.yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	@Column(name = "YHKL", length = 20)
	public String getYhkl() {
		return this.yhkl;
	}

	public void setYhkl(String yhkl) {
		this.yhkl = yhkl;
	}

	@Column(name = "YHBM", length = 20)
	public String getYhbm() {
		return this.yhbm;
	}

	public void setYhbm(String yhbm) {
		this.yhbm = yhbm;
	}

	@Column(name = "KLTS", length = 40)
	public String getKlts() {
		return this.klts;
	}

	public void setKlts(String klts) {
		this.klts = klts;
	}

	@Column(name = "KLDA", length = 20)
	public String getKlda() {
		return this.klda;
	}

	public void setKlda(String klda) {
		this.klda = klda;
	}

	@Column(name = "YHSF", length = 20)
	public String getYhsf() {
		return this.yhsf;
	}

	public void setYhsf(String yhsf) {
		this.yhsf = yhsf;
	}

	@Column(name = "KLSZSJ", length = 23)
	public Date getKlszsj() {
		return this.klszsj;
	}

	public void setKlszsj(Date klszsj) {
		this.klszsj = klszsj;
	}

	@Column(name = "QJKSSJ", length = 23)
	public Date getQjkssj() {
		return this.qjkssj;
	}

	public void setQjkssj(Date qjkssj) {
		this.qjkssj = qjkssj;
	}

	@Column(name = "QJJSSJ", length = 23)
	public Date getQjjssj() {
		return this.qjjssj;
	}

	public void setQjjssj(Date qjjssj) {
		this.qjjssj = qjjssj;
	}

	@Column(name = "QJYY", length = 200)
	public String getQjyy() {
		return this.qjyy;
	}

	public void setQjyy(String qjyy) {
		this.qjyy = qjyy;
	}

	@Column(name = "GRNZB")
	public Integer getGrnzb() {
		return this.grnzb;
	}

	public void setGrnzb(Integer grnzb) {
		this.grnzb = grnzb;
	}

	@Column(name = "QTNZB")
	public Integer getQtnzb() {
		return this.qtnzb;
	}

	public void setQtnzb(Integer qtnzb) {
		this.qtnzb = qtnzb;
	}

	@Column(name = "GRBAJS")
	public Integer getGrbajs() {
		return this.grbajs;
	}

	public void setGrbajs(Integer grbajs) {
		this.grbajs = grbajs;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "XFQX", length = 100)
	public String getXfqx() {
		return this.xfqx;
	}

	public void setXfqx(String xfqx) {
		this.xfqx = xfqx;
	}

	@Column(name = "SSFWZXQX", length = 100)
	public String getSsfwzxqx() {
		return this.ssfwzxqx;
	}

	public void setSsfwzxqx(String ssfwzxqx) {
		this.ssfwzxqx = ssfwzxqx;
	}

	@Column(name = "DZQM_IMG")
	public byte[] getDzqmImg() {
		return this.dzqmImg;
	}

	public void setDzqmImg(byte[] dzqmImg) {
		this.dzqmImg = dzqmImg;
	}

	@Column(name = "JBXXJ_CODE", length = 30)
	public String getJbxxjCode() {
		return this.jbxxjCode;
	}

	public void setJbxxjCode(String jbxxjCode) {
		this.jbxxjCode = jbxxjCode;
	}

	@Column(name = "FAZT")
	public Integer getFazt() {
		return this.fazt;
	}

	public void setFazt(Integer fazt) {
		this.fazt = fazt;
	}

	@Column(name = "FASL")
	public Integer getFasl() {
		return this.fasl;
	}

	public void setFasl(Integer fasl) {
		this.fasl = fasl;
	}

	@Column(name = "FYBH")
	public Integer getFybh() {
		return this.fybh;
	}

	public void setFybh(Integer fybh) {
		this.fybh = fybh;
	}

	@Column(name = "YHZT")
	public Integer getYhzt() {
		return this.yhzt;
	}

	public void setYhzt(Integer yhzt) {
		this.yhzt = yhzt;
	}

	@Column(name = "COCALL")
	public String getCocall() {
		return cocall;
	}

	public void setCocall(String cocall) {
		this.cocall = cocall;
	}

}
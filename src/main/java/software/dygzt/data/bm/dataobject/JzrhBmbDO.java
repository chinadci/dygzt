package software.dygzt.data.bm.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * JzrhBmbDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_XTGL_BMB")
@IdClass(JzrhBmbDOId.class)
public class JzrhBmbDO implements java.io.Serializable {

	// Fields

	private long bmbh;
	private Integer fybh;
	private String bmmc;
	private String spbm;
	private String bmzt;
	private Integer dybh;

	// Constructors

	/** default constructor */
	public JzrhBmbDO() {
	}

	/** minimal constructor */
	public JzrhBmbDO(long bmbh, Integer fybh) {
		this.bmbh = bmbh;
		this.fybh = fybh;
	}

	/** full constructor */
	public JzrhBmbDO(long bmbh, Integer fybh, String bmmc, String spbm, String bmzt,
			Integer dybh) {
		this.bmbh = bmbh;
		this.fybh = fybh;
		this.bmmc = bmmc;
		this.spbm = spbm;
		this.bmzt = bmzt;
		this.dybh = dybh;
	}

	@Id
	@Column(name = "BMBH", nullable = false)
	public long getBmbh() {
		return this.bmbh;
	}

	public void setBmbh(long bmbh) {
		this.bmbh = bmbh;
	}

	@Id
	@Column(name = "FYBH", nullable = false)
	public Integer getFybh() {
		return this.fybh;
	}

	public void setFybh(Integer fybh) {
		this.fybh = fybh;
	}
	
	@Column(name = "BMMC", length = 30)
	public String getBmmc() {
		return this.bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	@Column(name = "SPBM", length = 1)
	public String getSpbm() {
		return this.spbm;
	}

	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}

	@Column(name = "BMZT", length = 8)
	public String getBmzt() {
		return this.bmzt;
	}

	public void setBmzt(String bmzt) {
		this.bmzt = bmzt;
	}

	@Column(name = "DYBH")
	public Integer getDybh() {
		return this.dybh;
	}

	public void setDybh(Integer dybh) {
		this.dybh = dybh;
	}

}
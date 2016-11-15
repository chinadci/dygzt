package software.dygzt.data.ay.dataobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AydmbDOId entity. @author MyEclipse Persistence Tools
 */
public class AydmbDOId implements java.io.Serializable {

	// Fields

	private String bbh;
	private String dmbh;

	// Constructors

	/** default constructor */
	public AydmbDOId() {
	}

	/** full constructor */
	public AydmbDOId(String bbh, String dmbh) {
		this.bbh = bbh;
		this.dmbh = dmbh;
	}

	// Property accessors

	@Column(name = "BBH", nullable = false, length = 2)
	public String getBbh() {
		return bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
	}

	@Column(name = "DMBH", nullable = false, length = 10)
	public String getDmbh() {
		return this.dmbh;
	}
	
	public void setDmbh(String dmbh) {
		this.dmbh = dmbh;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AydmbDOId))
			return false;
		AydmbDOId castOther = (AydmbDOId) other;

		return ((this.getBbh() == castOther.getBbh()) || (this.getBbh() != null
				&& castOther.getBbh() != null && this.getBbh().equals(
				castOther.getBbh())))
				&& ((this.getDmbh() == castOther.getDmbh()) || (this.getDmbh() != null
						&& castOther.getDmbh() != null && this.getDmbh()
						.equals(castOther.getDmbh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBbh() == null ? 0 : this.getBbh().hashCode());
		result = 37 * result
				+ (getDmbh() == null ? 0 : this.getDmbh().hashCode());
		return result;
	}

}
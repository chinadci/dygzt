package software.dygzt.data.dmb.dataobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DmbDOId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DmbDOId implements java.io.Serializable {

	// Fields

	private String lbbh;
	private String dmbh;

	// Constructors

	/** default constructor */
	public DmbDOId() {
	}

	/** full constructor */
	public DmbDOId(String lbbh, String dmbh) {
		this.lbbh = lbbh;
		this.dmbh = dmbh;
	}

	// Property accessors

	@Column(name = "LBBH", nullable = false, length = 20)
	public String getLbbh() {
		return this.lbbh;
	}

	public void setLbbh(String lbbh) {
		this.lbbh = lbbh;
	}

	@Column(name = "DMBH", nullable = false, length = 20)
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
		if (!(other instanceof DmbDOId))
			return false;
		DmbDOId castOther = (DmbDOId) other;

		return ((this.getLbbh() == castOther.getLbbh()) || (this.getLbbh() != null
				&& castOther.getLbbh() != null && this.getLbbh().equals(
				castOther.getLbbh())))
				&& ((this.getDmbh() == castOther.getDmbh()) || (this.getDmbh() != null
						&& castOther.getDmbh() != null && this.getDmbh()
						.equals(castOther.getDmbh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getLbbh() == null ? 0 : this.getLbbh().hashCode());
		result = 37 * result
				+ (getDmbh() == null ? 0 : this.getDmbh().hashCode());
		return result;
	}

}
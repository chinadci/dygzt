package software.dygzt.data.dmb.dataobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DmbDOId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DyDmbDOId implements java.io.Serializable {

	// Fields

	private String lbbh;
	private String dmbh;

	// Constructors

	/** default constructor */
	public DyDmbDOId() {
	}

	/** full constructor */
	public DyDmbDOId(String lbbh, String dmbh) {
		this.lbbh = lbbh;
		this.dmbh = dmbh;
	}

	// Property accessors

	@Column(name = "lbbh", nullable = false, length = 20)
	public String getLbbh() {
		return this.lbbh;
	}

	public void setLbbh(String lbbh) {
		this.lbbh = lbbh;
	}

	@Column(name = "dmbh", nullable = false, length = 20)
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
		if (!(other instanceof DyDmbDOId))
			return false;
		DyDmbDOId castOther = (DyDmbDOId) other;

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
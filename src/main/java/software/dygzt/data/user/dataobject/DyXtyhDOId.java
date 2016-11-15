package software.dygzt.data.user.dataobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DyXtyhDOId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DyXtyhDOId implements java.io.Serializable {

	// Fields

	private String yhm;
	private String fydm;

	// Constructors

	/** default constructor */
	public DyXtyhDOId() {
	}

	/** full constructor */
	public DyXtyhDOId(String yhm, String fydm) {
		this.yhm = yhm;
		this.fydm = fydm;
	}

	// Property accessors

	@Column(name = "yhm", nullable = false, length = 20)
	public String getYhm() {
		return this.yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	@Column(name = "fydm", nullable = false, length = 20)
	public String getFydm() {
		return this.fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DyXtyhDOId))
			return false;
		DyXtyhDOId castOther = (DyXtyhDOId) other;

		return ((this.getYhm() == castOther.getYhm()) || (this.getYhm() != null
				&& castOther.getYhm() != null && this.getYhm().equals(
				castOther.getYhm())))
				&& ((this.getFydm() == castOther.getFydm()) || (this.getFydm() != null
						&& castOther.getFydm() != null && this.getFydm()
						.equals(castOther.getFydm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getYhm() == null ? 0 : this.getYhm().hashCode());
		result = 37 * result
				+ (getFydm() == null ? 0 : this.getFydm().hashCode());
		return result;
	}

}
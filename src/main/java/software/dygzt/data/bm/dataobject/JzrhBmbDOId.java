package software.dygzt.data.bm.dataobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * JzrhBmbDOId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class JzrhBmbDOId implements java.io.Serializable {

	// Fields

	private long bmbh;
	private Integer fybh;

	// Constructors

	/** default constructor */
	public JzrhBmbDOId() {
	}

	/** full constructor */
	public JzrhBmbDOId(long bmbh, Integer fybh) {
		this.bmbh = bmbh;
		this.fybh = fybh;
	}

	// Property accessors

	@Column(name = "BMBH", nullable = false)
	public long getBmbh() {
		return this.bmbh;
	}

	public void setBmbh(long bmbh) {
		this.bmbh = bmbh;
	}

	@Column(name = "FYBH", nullable = false)
	public Integer getFybh() {
		return this.fybh;
	}

	public void setFybh(Integer fybh) {
		this.fybh = fybh;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof JzrhBmbDOId))
			return false;
		JzrhBmbDOId castOther = (JzrhBmbDOId) other;

		return (this.getBmbh() == castOther.getBmbh())
				&& ((this.getFybh() == castOther.getFybh()) || (this.getFybh() != null
						&& castOther.getFybh() != null && this.getFybh()
						.equals(castOther.getFybh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getBmbh();
		result = 37 * result
				+ (getFybh() == null ? 0 : this.getFybh().hashCode());
		return result;
	}

}
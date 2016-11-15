package software.dygzt.data.research.dataobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResearchSavedInfoDOId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ResearchSavedInfoDOId implements java.io.Serializable {

	// Fields

	private Integer tableid;
	private String dyr;
	private String dyrfydm;

	// Constructors

	/** default constructor */
	public ResearchSavedInfoDOId() {
	}

	/** full constructor */
	public ResearchSavedInfoDOId(Integer tableid, String dyr, String dyrfydm) {
		this.tableid = tableid;
		this.dyr = dyr;
		this.dyrfydm = dyrfydm;
	}

	// Property accessors

	@Column(name = "tableid", nullable = false)
	public Integer getTableid() {
		return this.tableid;
	}

	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}

	@Column(name = "dyr", nullable = false, length = 20)
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResearchSavedInfoDOId))
			return false;
		ResearchSavedInfoDOId castOther = (ResearchSavedInfoDOId) other;

		return ((this.getTableid() == castOther.getTableid()) || (this
				.getTableid() != null && castOther.getTableid() != null && this
				.getTableid().equals(castOther.getTableid())))
				&& ((this.getDyr() == castOther.getDyr()) || (this.getDyr() != null
						&& castOther.getDyr() != null && this.getDyr().equals(
						castOther.getDyr())))
				&& ((this.getDyrfydm() == castOther.getDyrfydm()) || (this
						.getDyrfydm() != null && castOther.getDyrfydm() != null && this
						.getDyrfydm().equals(castOther.getDyrfydm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTableid() == null ? 0 : this.getTableid().hashCode());
		result = 37 * result
				+ (getDyr() == null ? 0 : this.getDyr().hashCode());
		result = 37 * result
				+ (getDyrfydm() == null ? 0 : this.getDyrfydm().hashCode());
		return result;
	}

}
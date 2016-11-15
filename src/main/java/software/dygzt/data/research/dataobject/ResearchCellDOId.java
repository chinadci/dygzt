package software.dygzt.data.research.dataobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResearchCellDOId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ResearchCellDOId implements java.io.Serializable {

	// Fields

	private Integer tableid;
	private Integer rowid;
	private Integer cellid;

	// Constructors

	/** default constructor */
	public ResearchCellDOId() {
	}

	/** full constructor */
	public ResearchCellDOId(Integer tableid, Integer rowid, Integer cellid) {
		this.tableid = tableid;
		this.rowid = rowid;
		this.cellid = cellid;
	}

	// Property accessors

	@Column(name = "tableid", nullable = false)
	public Integer getTableid() {
		return this.tableid;
	}

	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}

	@Column(name = "rowid", nullable = false)
	public Integer getRowid() {
		return this.rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}

	@Column(name = "cellid", nullable = false)
	public Integer getCellid() {
		return this.cellid;
	}

	public void setCellid(Integer cellid) {
		this.cellid = cellid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResearchCellDOId))
			return false;
		ResearchCellDOId castOther = (ResearchCellDOId) other;

		return ((this.getTableid() == castOther.getTableid()) || (this
				.getTableid() != null && castOther.getTableid() != null && this
				.getTableid().equals(castOther.getTableid())))
				&& ((this.getRowid() == castOther.getRowid()) || (this
						.getRowid() != null && castOther.getRowid() != null && this
						.getRowid().equals(castOther.getRowid())))
				&& ((this.getCellid() == castOther.getCellid()) || (this
						.getCellid() != null && castOther.getCellid() != null && this
						.getCellid().equals(castOther.getCellid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTableid() == null ? 0 : this.getTableid().hashCode());
		result = 37 * result
				+ (getRowid() == null ? 0 : this.getRowid().hashCode());
		result = 37 * result
				+ (getCellid() == null ? 0 : this.getCellid().hashCode());
		return result;
	}

}
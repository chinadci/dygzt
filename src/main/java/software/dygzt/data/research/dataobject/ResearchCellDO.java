package software.dygzt.data.research.dataobject;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * ResearchCellDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DY_AUTO_CELL")
@IdClass(ResearchCellDOId.class)
public class ResearchCellDO implements java.io.Serializable {

	// Fields

	private Integer tableid;
	private Integer rowid;
	private Integer cellid;
	private String value;
	private String fydm;
	private String sql;
	private Integer colspan;
	private Integer rowspan;
	private String base;

	// Constructors

	/** default constructor */
	public ResearchCellDO() {
	}

	/** minimal constructor */
	public ResearchCellDO(Integer tableid, Integer rowid, Integer cellid,
			Integer colspan, Integer rowspan) {
		this.tableid = tableid;
		this.rowid = rowid;
		this.cellid = cellid;
		this.colspan = colspan;
		this.rowspan = rowspan;
	}

	/** full constructor */
	public ResearchCellDO(Integer tableid, Integer rowid, Integer cellid,
			String value, String fydm, String sql, Integer colspan,
			Integer rowspan,String base) {
		this.tableid = tableid;
		this.rowid = rowid;
		this.cellid = cellid;
		this.value = value;
		this.fydm = fydm;
		this.sql = sql;
		this.colspan = colspan;
		this.rowspan = rowspan;
		this.base = base;
	}

	@Column(name = "tableid", nullable = false)
	@Id
	public Integer getTableid() {
		return tableid;
	}

	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}
	
	@Column(name = "rowid", nullable = false)
	@Id
	public Integer getRowid() {
		return rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}
	
	@Column(name = "cellid", nullable = false)
	@Id
	public Integer getCellid() {
		return cellid;
	}

	public void setCellid(Integer cellid) {
		this.cellid = cellid;
	}


	@Column(name = "value", length = 200)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "fydm", length = 20)
	public String getFydm() {
		return this.fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	@Column(name = "sql")
	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@Column(name = "colspan", nullable = false)
	public Integer getColspan() {
		return this.colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	@Column(name = "rowspan", nullable = false)
	public Integer getRowspan() {
		return this.rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	@Column(name = "base")
	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
}
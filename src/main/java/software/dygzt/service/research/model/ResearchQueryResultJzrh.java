package software.dygzt.service.research.model;

public class ResearchQueryResultJzrh implements ResearchQueryResult{
	@Result(table="BZ_AJ_AJXXB", column="CONVERT(INT,RIGHT(CONVERT(varchar,?AJBS),LEN(CONVERT(varchar,?AJBS))-2))", field = "ajxh")
	public Boolean ajxh;
	@Result(table="BZ_AJ_AJXXB", column="?AH", field = "ah")
	public Boolean ah;
	@Result(table="BZ_AJ_AJXXB", column="?AJMC", field = "ajmc")
	public Boolean ajmc;
	@Result(table="BZ_AJ_AJXXB", column="CONVERT(VARCHAR,?BABM)", field = "baspt")
	public Boolean spt;
	@Result(table="BZ_AJ_AJXXB", column="?LARQ", field = "larq")
	public Boolean larq;
	@Result(table="BZ_AJ_AJXXB", column="?JARQ", field = "jarq")
	public Boolean jarq;
	@Result(table="BZ_AJ_AJXXB",column="SUBSTRING(?AJLX,2,1)", field = "spcx")
	public Boolean spcx;
	@Result(table="BZ_AJ_AJXXB",column="RIGHT(?AJLX,LEN(?AJLX)-2)", field = "spcxlx")
	public Boolean spcxdz;
	@Result(table="BZ_AJ_AJXXB",column="SUBSTRING(?AJLX,1,1)", field = "ajxz")
	public Boolean ajxz;
	@Result(table="BZ_AJ_AJXXB",column="CONVERT(VARCHAR,?ZAY)", field = "laay")
	public Boolean laay;
	@Result(table="BZ_AJ_AJXXB",column="?FYBH", field = "fybh")
	public Boolean fybh;
	
	public Boolean getAjxh() {
		return ajxh;
	}
	public void setAjxh(Boolean ajxh) {
		this.ajxh = ajxh;
	}
	public Boolean getAh() {
		return ah;
	}
	public void setAh(Boolean ah) {
		this.ah = ah;
	}
	public Boolean getAjmc() {
		return ajmc;
	}
	public void setAjmc(Boolean ajmc) {
		this.ajmc = ajmc;
	}
	public Boolean getSpt() {
		return spt;
	}
	public void setSpt(Boolean spt) {
		this.spt = spt;
	}
	public Boolean getLarq() {
		return larq;
	}
	public void setLarq(Boolean larq) {
		this.larq = larq;
	}
	public Boolean getJarq() {
		return jarq;
	}
	public void setJarq(Boolean jarq) {
		this.jarq = jarq;
	}
	public Boolean getSpcx() {
		return spcx;
	}
	public void setSpcx(Boolean spcx) {
		this.spcx = spcx;
	}
	public Boolean getSpcxdz() {
		return spcxdz;
	}
	public void setSpcxdz(Boolean spcxdz) {
		this.spcxdz = spcxdz;
	}
	public Boolean getAjxz() {
		return ajxz;
	}
	public void setAjxz(Boolean ajxz) {
		this.ajxz = ajxz;
	}
	public Boolean getLaay() {
		return laay;
	}
	public void setLaay(Boolean laay) {
		this.laay = laay;
	}
	public Boolean getFybh() {
		return fybh;
	}
	public void setFybh(Boolean fybh) {
		this.fybh = fybh;
	}
}

package software.dygzt.service.research.model;

public class ResearchQueryResultSepa implements ResearchQueryResult{
	@Result(table="PUB_AJ_JB", column="?AJXH", field = "ajxh")
	public Boolean ajxh;
	@Result(table="PUB_AJ_JB", column="?AH", field = "ah")
	public Boolean ah;
	@Result(table="PUB_AJ_JB", column="?AJMC", field = "ajmc")
	public Boolean ajmc;
	@Result(table="PUB_AJ_JB", column="?BASPT", field = "baspt")
	public Boolean spt;
	@Result(table="PUB_AJ_JB", column="?LARQ", field = "larq")
	public Boolean larq;
	@Result(table="PUB_AJ_JB", column="?JARQ", field = "jarq")
	public Boolean jarq;
	@Result(table="PUB_AJ_JB",column="?SPCX", field = "spcx")
	public Boolean spcx;
	@Result(table="PUB_AJ_JB",column="?SPCXDZ", field = "spcxlx")
	public Boolean spcxdz;
	@Result(table="PUB_AJ_JB",column="?AJXZ", field = "ajxz")
	public Boolean ajxz;
	@Result(table="PUB_LA_AY",column="?AY", field = "laay")
	public Boolean laay;
	
//	@Result(table="PUB_AJ_JB",column="PSYCY")
//	public String psycy;
//	@Result(table="PUB_AJ_JB",column="SFXESS")
//	public String sfxess;
//	@Result(table="PUB_SPRY", column="XM")
//	public String xm;
//	@Result(table="PUB_SPRY",column="SFCBR")
//	public String sfcbr;
//	@Result(table="PUB_SPRY",column="SFRMPSY")
//	public String sfrmpsy;
//	@Result(table="PUB_SPRY",column="SFSPZ")
//	public String sfspz;
//	@Result(table="PUB_AJ_JB",column="JAFS")
//	public String jafs;
//	@Result(table="PUB_JA_AY",column="JAAY")
//	public String jaay;
//	@Result(table="PUB_AJ_JB",column="SX")
//	public Integer sx;
//	@Result(table="PUB_AJ_JB",column="FJSX")
//	public Integer fjsx;
//	@Result(table="PUB_AJ_JB",column="GDBZ")
//	public String gdbz;
//	@Result(table="PUB_AJ_JB",column="GDRQ")
//	public String gdrq;
//	@Result(table="PUB_FY_SSF",column="LABD")
//	public Double labd;
//	@Result(table="PUB_FY_SSF",column="JABD")
//	public Double jabd;
//	@Result(table="PUB_JA_PJ",column="SFDTXP")
//	public String sfdtxp;
//	@Result(table="PUB_AJ_JB", column="SYCX")
//	public String sycx;
//	@Result(table="PUB_SPRY",column="FG")
//	public String fg; 
//	@Result(table="PUB_AJ_JB",column="AJZT")
//	public String ajzt;
//	@Result(table="PUB_AJ_JB",column="SFZSCQ")
//	public String sfzscq;
//	@Result(table="PUB_AJ_JB",column="AJWSQK")
//	public String ajwsqk;
//	@Result(table="PUB_LA_SSFC_YSCP",column="YSSXFY")
//	public String ysfy;
//	@Result(table="PUB_AJLC", column="FSYSCLSJ")
//	public String ysclsj;
//	@Result(table="PUB_AJLC", column="SCKTSJ")
//	public String firstKtsj;
//	@Result(table="PUB_AJLC", column="ZHYCTSSJ")
//	public String lastKtsj;
//	@Result(table="PUB_AJLC", column="WSZZHQFSJ")
//	public String wsqfsj;
//	@Result(table="PUB_AJLC", column="YSSJYBGSRQ")
//	public String sjybgszd;
//	@Result(table="PUB_AJLC", column="YSDASRQ")
//	public String gdsj;
//	@Result(table="PUB_AJLC", column="SFFSYSCL")
//	public String sfysclsj;
//	@Result(table="PUB_AJLC", column="SFKT")
//	public String sffirstKtsj;
//	@Result(table="PUB_AJ_JB",column="BYCXDZ")
//	public String bycxdz;
	
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
	
	
}

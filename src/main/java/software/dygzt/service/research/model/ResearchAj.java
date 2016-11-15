package software.dygzt.service.research.model;

import java.util.Date;

import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.util.DateUtil;
import software.dygzt.util.StringUtil;

public class ResearchAj {
	/**
	 * 案件序号
	 */
	private int ajxh;
	/**
	 * 案号
	 */
	private String ah;
	/**
	 * 法院代码
	 */
	private String fydm;
	/**
	 * 法院编号
	 */
	private Integer fybh;
	/**
	 * 案件名称
	 */
	private String ajmc;
	/**
	 * 立案时间
	 */
	private Date larq;
	/**
	 * 结案时间
	 */
	private Date jarq;
	//案件性质
	private String ajxz;
	//审判程序
	private String spcx;
	//审判程序类型
	private String spcxlx;
	//立案案由
	private String laay;
	//办案审判庭
	private String baspt;
	public ResearchAj() {
	}
	
	public int getAjxh() {
		return ajxh;
	}
	public void setAjxh(Object ajxh) {
		this.ajxh = (Integer)ajxh;
	}
	public String getAh() {
		return ah;
	}
	public void setAh(Object ah) {
		this.ah = ah==null?null:String.valueOf(ah);
	}
	public String getFydm() {
		return fydm;
	}
	public void setFydm(String fydm) {
		this.fydm = fydm;
	}
	public Integer getFybh() {
		return fybh;
	}
	public void setFybh(Object fybh) {
		this.fybh = (Integer)fybh;
		String fydm = FYDataSourceEnum.getDataSourceByFybh(this.fybh).getFydm();
		this.fydm = fydm;
	}
	public String getAjmc() {
		return ajmc;
	}
	public void setAjmc(Object ajmc) {
		this.ajmc = ajmc==null?null:String.valueOf(ajmc);
	}
	public Date getLarq() {
		return larq;
	}
	public String getLarqStr() {
		return DateUtil.format(larq, DateUtil.webFormat);
	}
	public void setLarq(Object larq) {
		this.larq = (Date)larq;
	}
	public Date getJarq() {
		return jarq;
	}
	public String getJarqStr() {
		return DateUtil.format(jarq, DateUtil.webFormat);
	}
	public void setJarq(Object jarq) {
		this.jarq = (Date)jarq;
	}
	public String getAjxz() {
		return ajxz;
	}
	public void setAjxz(Object ajxz) {
		String tmp = ajxz==null?null:String.valueOf(ajxz);
		if(StringUtil.isNotBlank(tmp)){
			this.ajxz = tmp.trim();
		}
	}
	public String getSpcx() {
		return spcx;
	}
	public void setSpcx(Object spcx) {
		String tmp = spcx==null?null:String.valueOf(spcx);
		if(StringUtil.isNotBlank(tmp)){
			this.spcx = tmp.trim();
		}
	}
	public String getSpcxlx() {
		return spcxlx;
	}
	public void setSpcxlx(Object spcxlx) {
		String tmp = spcxlx==null?null:String.valueOf(spcxlx);
		if(StringUtil.isNotBlank(tmp)){
			this.spcxlx = tmp.trim();
		}
	}
	public String getLaay() {
		return laay;
	}
	public void setLaay(Object laay) {
		String tmp = laay==null?null:String.valueOf(laay);
		if(StringUtil.isNotBlank(tmp)){
			this.laay = tmp.trim();
		}
	}

	public String getBaspt() {
		return baspt;
	}

	public void setBaspt(Object baspt) {
		String tmp = baspt==null?null:String.valueOf(baspt);
		if(StringUtil.isNotBlank(tmp)){
			this.baspt = tmp.trim();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ajxh;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResearchAj other = (ResearchAj) obj;
		if (ajxh != other.ajxh)
			return false;
		return true;
	}
}

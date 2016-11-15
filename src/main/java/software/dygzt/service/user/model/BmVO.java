package software.dygzt.service.user.model;

import java.util.List;

public class BmVO {
	String bmbh;
	String bmmc;
	List<XtyhVO> xtyhs;
	
	public BmVO() {
		super();
	}
	public BmVO(String bmbh, String bmmc, List<XtyhVO> xtyhs) {
		super();
		this.bmbh = bmbh;
		this.bmmc = bmmc;
		this.xtyhs = xtyhs;
	}
	public String getBmbh() {
		return bmbh;
	}
	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public List<XtyhVO> getXtyhs() {
		return xtyhs;
	}
	public void setXtyhs(List<XtyhVO> xtyhs) {
		this.xtyhs = xtyhs;
	}
	
}

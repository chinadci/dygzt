package software.dygzt.service.bm.model;

/**
 * 
 * 部门的领域模型
 * 
 */
public class BmModel {
	/**
	 * 部门编号
	 */
	private String bmbh;
	/**
	 * 法院编号
	 */
	private long fybh;
	/**
	 * 部门名称
	 */
	private String bmmc;

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	public String getBmbh() {
		return bmbh;
	}

	public long getFybh() {
		return fybh;
	}

	public void setFybh(long fybh) {
		this.fybh = fybh;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getBmmc() {
		return bmmc;
	}
}

package software.dygzt.service.user.model;

public class UserContextModel {
	/**
	 * 用户编号
	 */
	private long yhbh;
	/**
	 * 法院编号 空值
	 */
	private long fybh;
	/**
	 * 法院代码
	 */
	private String fydm;
	/**
	 * 用户代码
	 */
	private String yhdm;
	/**
	 * 用户名称
	 */
	private String yhmc;
	/**
	 * 用户部门
	 */
	private String yhbm;
	/**
	 * 用户身份
	 */
	private String yhsf;
	/**
	 * 当前系统的用户权限
	 */
	private String yhqx;
	/**
	 * 用户密码
	 * （供调用其他系统时使用）
	 */
	private String password;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 联系方式（coco）
	 */
	private String lxfs;
	
	public long getYhbh() {
		return yhbh;
	}
	public void setYhbh(long yhbh) {
		this.yhbh = yhbh;
	}
	public long getFybh() {
		return fybh;
	}
	public void setFybh(long fybh) {
		this.fybh = fybh;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getYhbm() {
		return yhbm;
	}
	public void setYhbm(String yhbm) {
		this.yhbm = yhbm;
	}
	public String getYhsf() {
		return yhsf;
	}
	public void setYhsf(String yhsf) {
		this.yhsf = yhsf;
	}
	public String getYhqx() {
		return yhqx;
	}
	public void setYhqx(String yhqx) {
		this.yhqx = yhqx;
	}
	public String getFydm() {
		return fydm;
	}
	public void setFydm(String fydm) {
		this.fydm = fydm;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLxfs() {
		return lxfs;
	}
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	
}
package software.dygzt.service.share.model;

public class ChartDataVO {
	/**
	 * 值
	 */
	int y;
	/**
	 * 网址
	 */
	String url;
	
	public ChartDataVO(int y, String url) {
		super();
		this.y = y;
		this.url = url;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

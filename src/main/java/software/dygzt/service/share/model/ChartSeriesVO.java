package software.dygzt.service.share.model;

import java.util.List;

public class ChartSeriesVO {
	private String name;
	private List data; 
	/**
	 * 组
	 */
	private String stack;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
}

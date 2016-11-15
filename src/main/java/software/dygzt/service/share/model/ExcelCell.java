package software.dygzt.service.share.model;

public class ExcelCell {
	private int start_x;
	private int start_y;
	private int end_x;
	private int end_y;
	private String value;
	private boolean isMerged;
	private boolean isHeader;
	
	public ExcelCell(int start_x, int start_y, String value, boolean isHeader) {
		this.start_x = start_x;
		this.start_y = start_y;
		this.value = value;
		this.isHeader = isHeader;
		isMerged = false;
		end_x = start_x;
		end_y = start_y;
	}
	public int getStart_x() {
		return start_x;
	}
	public void setStart_x(int start_x) {
		this.start_x = start_x;
	}
	public int getStart_y() {
		return start_y;
	}
	public void setStart_y(int start_y) {
		this.start_y = start_y;
	}
	public int getEnd_x() {
		return end_x;
	}
	public void setEnd_x(int end_x) {
		this.end_x = end_x;
	}
	public int getEnd_y() {
		return end_y;
	}
	public void setEnd_y(int end_y) {
		this.end_y = end_y;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isMerged() {
		return isMerged;
	}
	public void setMerged(boolean isMerged) {
		this.isMerged = isMerged;
	}
	public boolean isHeader() {
		return isHeader;
	}
	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}
}

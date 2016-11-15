package software.dygzt.service.share.model;

import java.util.List;

public class TreeNodeVO {
	private List<TreeNodeVO> children;
	private String name;
	private boolean isParent;
	private boolean open;
	//自定义
	private String dmid;
	
	public TreeNodeVO(String name, boolean isParent, boolean open, String dmid) {
		this.name = name;
		this.isParent = isParent;
		this.open = open;
		this.dmid = dmid;
	}
	public TreeNodeVO(String name, boolean isParent, String dmid) {
		this.name = name;
		this.isParent = isParent;
		this.dmid = dmid;
	}
	public List<TreeNodeVO> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNodeVO> children) {
		this.children = children;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getDmid() {
		return dmid;
	}
	public void setDmid(String dmid) {
		this.dmid = dmid;
	}
	
}

package software.dygzt.service.ajlx.model;

import java.util.ArrayList;
import java.util.List;

public class AjlxVO {
	String id;
	String sjid;
	String mc;
	String dz;
	List<AjlxVO> xjList;
	
	public AjlxVO() {
		xjList = new ArrayList<AjlxVO>();
	}

	public AjlxVO(String id, String sjid, String mc, String dz) {
		this.id = id;
		this.sjid = sjid;
		this.mc = mc;
		this.dz = dz;
		this.xjList = new ArrayList<AjlxVO>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSjid() {
		return sjid;
	}

	public void setSjid(String sjid) {
		this.sjid = sjid;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public List<AjlxVO> getXjList() {
		return xjList;
	}

	public void setXjList(List<AjlxVO> xjList) {
		this.xjList = xjList;
	}
	public void addXj(AjlxVO xj) {
		if(xjList==null){
			xjList = new ArrayList<AjlxVO>();
		}
		xjList.add(xj);
	}
	
}

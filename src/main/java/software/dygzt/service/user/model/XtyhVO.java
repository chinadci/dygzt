package software.dygzt.service.user.model;

public class XtyhVO {
	private String yhm;
	private String name;
	
	public XtyhVO(String yhm, String name) {
		super();
		this.yhm = yhm;
		this.name = name;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

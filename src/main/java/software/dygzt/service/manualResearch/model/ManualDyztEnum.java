package software.dygzt.service.manualResearch.model;

public enum ManualDyztEnum {
	
	DSP("待审批"),
	SPBTG("审批不通过"),
	DZP("待指派"),
	DJS("待计算"),
	DYWC("调研完成"),
	YSC("已删除");
	
	private String dyzt;

	private ManualDyztEnum(String dyzt) {
		this.dyzt = dyzt;
	}

	public String getDyzt() {
		return dyzt;
	}

	public void setDyzt(String dyzt) {
		this.dyzt = dyzt;
	}

}

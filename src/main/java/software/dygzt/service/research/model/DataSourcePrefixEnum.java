package software.dygzt.service.research.model;

public enum DataSourcePrefixEnum {
	JZRH("JZRH_");
	String prefix;

	private DataSourcePrefixEnum(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}

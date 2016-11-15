package software.dygzt.service.research.model;

import software.dygzt.util.NumberUtil;


public class ResearchTableCell {
	private Double value;
	private String condition;	//条件
	private String fydm;
	private String base;		//基本条件
	
	//得到str value;
	public String getValue() {
		if(value.intValue()==value){
			return String.valueOf(value.intValue());
		}else{
			return NumberUtil.changeNumber(value.doubleValue(), 4);
		}
	}
	//得到实际value
	public double getValueDouble(){
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getFydm() {
		return fydm;
	}
	public void setFydm(String fydm) {
		this.fydm = fydm;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	
}

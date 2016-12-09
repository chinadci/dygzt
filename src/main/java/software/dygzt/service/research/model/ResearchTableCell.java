package software.dygzt.service.research.model;

import software.dygzt.util.NumberUtil;


public class ResearchTableCell {
    private Double value; //当前查询条件需要显示的值
    private Double lastYearValue;
    private Double samePeriodLastYearValue;//同比值

    private Double lastPeriod;//环比
    private String condition;    //条件
    private String fydm;
    private String base;        //基本条件

    //得到str value;
    public String getValue() {
        if (value.intValue() == value) {
            return String.valueOf(value.intValue());
        } else {
            return NumberUtil.changeNumber(value.doubleValue(), 4);
        }
    }

    //得到实际value
    public double getValueDouble() {
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

    public Double getLastYearValue() {
        return lastYearValue;
    }

    public void setLastYearValue(Double lastYearValue) {
        this.lastYearValue = lastYearValue;
    }

    public void setSamePeriodLastYearValue(Double samePeriodLastYearValue) {
        this.samePeriodLastYearValue = samePeriodLastYearValue;
    }

    public String getSamePeriodLastYearValue() {

        if (samePeriodLastYearValue.intValue() == samePeriodLastYearValue) {
            return String.valueOf(samePeriodLastYearValue.intValue());
        } else {
            return NumberUtil.changeNumber(samePeriodLastYearValue.doubleValue(), 4);
        }

    }

    public void setLastPeriod(Double lastPeriod) {
        this.lastPeriod = lastPeriod;
    }

    public Double getLastPeriod() {

        return lastPeriod;
    }

}

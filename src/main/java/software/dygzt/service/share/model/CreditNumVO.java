package software.dygzt.service.share.model;

/**
 * Created by Pzy on 12/15/16.
 * 置信度类
 */
public class CreditNumVO {
    double XSCredit; //新收置信度
    double JCCredit;//旧存置信度
    double YJCredit;//已结置信度
    double WJcredit;//未结置信度

    public CreditNumVO() {
    }

    public CreditNumVO(double XSCredit, double JCCredit, double YJCredit, double WJcredit) {
        this.XSCredit = XSCredit;
        this.JCCredit = JCCredit;
        this.YJCredit = YJCredit;
        this.WJcredit = WJcredit;
    }

    public double getXSCredit() {
        return XSCredit;
    }

    public double getJCCredit() {
        return JCCredit;
    }

    public double getYJCredit() {
        return YJCredit;
    }

    public double getWJcredit() {
        return WJcredit;
    }

    public void setXSCredit(double XSCredit) {
        this.XSCredit = XSCredit;
    }

    public void setJCCredit(double JCCredit) {
        this.JCCredit = JCCredit;
    }

    public void setYJCredit(double YJCredit) {
        this.YJCredit = YJCredit;
    }

    public void setWJcredit(double WJcredit) {
        this.WJcredit = WJcredit;
    }
}

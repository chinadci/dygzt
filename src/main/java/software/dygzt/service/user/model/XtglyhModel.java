package software.dygzt.service.user.model;

/**
 * Created by Pzy on 12/14/16.
 * PUB_XTGL_YHB 对应的系统用户 model
 *
 */

public class XtglyhModel {
    private String yhdm;
    private int fybh; //法院编号，和法院代码的区别(两种表示方式)
    private String yhmc;
    private String yhkl;
    private String yhbm;
    private String yhsf;

    public XtglyhModel() {
    }

    public XtglyhModel(String yhdm, int fybh, String yhmc, String yhkl, String yhbm, String yhsf) {
        this.yhdm = yhdm;
        this.fybh = fybh;
        this.yhmc = yhmc;
        this.yhkl = yhkl;
        this.yhbm = yhbm;
        this.yhsf = yhsf;
    }

    public int getFybh() {
        return fybh;
    }

    public void setFybh(int fybh) {
        this.fybh = fybh;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    public void setYhsf(String yhsf) {
        this.yhsf = yhsf;
    }

    public String getYhdm() {
        return yhdm;
    }

    public String getYhmc() {
        return yhmc;
    }

    public String getYhkl() {
        return yhkl;
    }

    public String getYhbm() {
        return yhbm;
    }

    public String getYhsf() {
        return yhsf;
    }
}
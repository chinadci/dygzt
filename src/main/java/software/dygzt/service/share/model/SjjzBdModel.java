package software.dygzt.service.share.model;

/**
 * Created by Pzy on 12/14/16.
 */
public class SjjzBdModel {
    private String kssj; //开始时间
    private String jssj;//结束时间
    private int jzrhXs;
    private int jzrhJc;
    private int jzrhYj;
    private int jzrhWj;
    private int bdXs;
    private int bdJc;
    private int bdYj;
    private int bdWj;
    private int cyXs;
    private int cyJc;
    private int cyYj;
    private int cyWj;

    public SjjzBdModel() {
    }

    public String getKssj() {
        return kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public Integer getJzrhXs() {
        return jzrhXs;
    }

    public Integer getJzrhJc() {
        return jzrhJc;
    }

    public Integer getJzrhYj() {
        return jzrhYj;
    }

    public Integer getJzrhWj() {
        return jzrhWj;
    }

    public Integer getBdXs() {
        return bdXs;
    }

    public Integer getBdJc() {
        return bdJc;
    }

    public Integer getBdYj() {
        return bdYj;
    }

    public Integer getBdWj() {
        return bdWj;
    }

    public Integer getCyXs() {
        return cyXs;
    }

    public Integer getCyJc() {
        return cyJc;
    }

    public Integer getCyYj() {
        return cyYj;
    }

    public Integer getCyWj() {
        return cyWj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public void setJzrhXs(Integer jzrhXs) {
        this.jzrhXs = jzrhXs;
    }

    public void setJzrhJc(Integer jzrhJc) {
        this.jzrhJc = jzrhJc;
    }

    public void setJzrhYj(Integer jzrhYj) {
        this.jzrhYj = jzrhYj;
    }

    public void setJzrhWj(Integer jzrhWj) {
        this.jzrhWj = jzrhWj;
    }

    public void setBdXs(Integer bdXs) {
        this.bdXs = bdXs;
    }

    public void setBdJc(Integer bdJc) {
        this.bdJc = bdJc;
    }

    public void setBdYj(Integer bdYj) {
        this.bdYj = bdYj;
    }

    public void setBdWj(Integer bdWj) {
        this.bdWj = bdWj;
    }

    public void setCyXs(Integer cyXs) {
        this.cyXs = cyXs;
    }

    public void setCyJc(Integer cyJc) {
        this.cyJc = cyJc;
    }

    public void setCyYj(Integer cyYj) {
        this.cyYj = cyYj;
    }

    public void setCyWj(Integer cyWj) {
        this.cyWj = cyWj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SjjzBdModel that = (SjjzBdModel) o;

        if (jzrhXs != that.jzrhXs) return false;
        if (jzrhJc != that.jzrhJc) return false;
        if (jzrhYj != that.jzrhYj) return false;
        if (jzrhWj != that.jzrhWj) return false;
        if (bdXs != that.bdXs) return false;
        if (bdJc != that.bdJc) return false;
        if (bdYj != that.bdYj) return false;
        if (bdWj != that.bdWj) return false;
        if (cyXs != that.cyXs) return false;
        if (cyJc != that.cyJc) return false;
        if (cyYj != that.cyYj) return false;
        if (cyWj != that.cyWj) return false;
        if (kssj != null ? !kssj.equals(that.kssj) : that.kssj != null) return false;
        return jssj != null ? jssj.equals(that.jssj) : that.jssj == null;

    }

    @Override
    public int hashCode() {
        int result = kssj != null ? kssj.hashCode() : 0;
        result = 31 * result + (jssj != null ? jssj.hashCode() : 0);
        result = 31 * result + jzrhXs;
        result = 31 * result + jzrhJc;
        result = 31 * result + jzrhYj;
        result = 31 * result + jzrhWj;
        result = 31 * result + bdXs;
        result = 31 * result + bdJc;
        result = 31 * result + bdYj;
        result = 31 * result + bdWj;
        result = 31 * result + cyXs;
        result = 31 * result + cyJc;
        result = 31 * result + cyYj;
        result = 31 * result + cyWj;
        return result;
    }
}

package software.dygzt.data.share.dataobject;

import javax.persistence.*;


/**
 * Created by Pzy on 12/14/16.
 */
@Entity
@Table(name = "SJJZ_SJJZBD", schema = "dbo", catalog = "JZRH")
public class SjjzBdDO {
    private String bdid;
    private String fybh;
    private Integer ksNd;
    private Integer ksYf;
    private Integer ksTs;
    private Integer jsNd;
    private Integer jsYf;
    private Integer jsTs;
    private Integer jzrhXs;
    private Integer jzrhJc;
    private Integer jzrhYj;
    private Integer jzrhWj;
    private Integer bdXs;
    private Integer bdJc;
    private Integer bdYj;
    private Integer bdWj;
    private Integer cyXs;
    private Integer cyJc;
    private Integer cyYj;
    private Integer cyWj;

    @Id
    @Column(name = "BDID")
    public String getBdid() {
        return bdid;
    }

    public void setBdid(String bdid) {
        this.bdid = bdid;
    }

    @Basic
    @Column(name = "FYBH")
    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    @Basic
    @Column(name = "KS_ND")
    public Integer getKsNd() {
        return ksNd;
    }

    public void setKsNd(Integer ksNd) {
        this.ksNd = ksNd;
    }

    @Basic
    @Column(name = "KS_YF")
    public Integer getKsYf() {
        return ksYf;
    }

    public void setKsYf(Integer ksYf) {
        this.ksYf = ksYf;
    }

    @Basic
    @Column(name = "KS_TS")
    public Integer getKsTs() {
        return ksTs;
    }

    public void setKsTs(Integer ksTs) {
        this.ksTs = ksTs;
    }

    @Basic
    @Column(name = "JS_ND")
    public Integer getJsNd() {
        return jsNd;
    }

    public void setJsNd(Integer jsNd) {
        this.jsNd = jsNd;
    }

    @Basic
    @Column(name = "JS_YF")
    public Integer getJsYf() {
        return jsYf;
    }

    public void setJsYf(Integer jsYf) {
        this.jsYf = jsYf;
    }

    @Basic
    @Column(name = "JS_TS")
    public Integer getJsTs() {
        return jsTs;
    }

    public void setJsTs(Integer jsTs) {
        this.jsTs = jsTs;
    }

    @Basic
    @Column(name = "JZRH_XS")
    public Integer getJzrhXs() {
        return jzrhXs;
    }

    public void setJzrhXs(Integer jzrhXs) {
        this.jzrhXs = jzrhXs;
    }

    @Basic
    @Column(name = "JZRH_JC")
    public Integer getJzrhJc() {
        return jzrhJc;
    }

    public void setJzrhJc(Integer jzrhJc) {
        this.jzrhJc = jzrhJc;
    }

    @Basic
    @Column(name = "JZRH_YJ")
    public Integer getJzrhYj() {
        return jzrhYj;
    }

    public void setJzrhYj(Integer jzrhYj) {
        this.jzrhYj = jzrhYj;
    }

    @Basic
    @Column(name = "JZRH_WJ")
    public Integer getJzrhWj() {
        return jzrhWj;
    }

    public void setJzrhWj(Integer jzrhWj) {
        this.jzrhWj = jzrhWj;
    }

    @Basic
    @Column(name = "BD_XS")
    public Integer getBdXs() {
        return bdXs;
    }

    public void setBdXs(Integer bdXs) {
        this.bdXs = bdXs;
    }

    @Basic
    @Column(name = "BD_JC")
    public Integer getBdJc() {
        return bdJc;
    }

    public void setBdJc(Integer bdJc) {
        this.bdJc = bdJc;
    }

    @Basic
    @Column(name = "BD_YJ")
    public Integer getBdYj() {
        return bdYj;
    }

    public void setBdYj(Integer bdYj) {
        this.bdYj = bdYj;
    }

    @Basic
    @Column(name = "BD_WJ")
    public Integer getBdWj() {
        return bdWj;
    }

    public void setBdWj(Integer bdWj) {
        this.bdWj = bdWj;
    }

    @Basic
    @Column(name = "CY_XS")
    public Integer getCyXs() {
        return cyXs;
    }

    public void setCyXs(Integer cyXs) {
        this.cyXs = cyXs;
    }

    @Basic
    @Column(name = "CY_JC")
    public Integer getCyJc() {
        return cyJc;
    }

    public void setCyJc(Integer cyJc) {
        this.cyJc = cyJc;
    }

    @Basic
    @Column(name = "CY_YJ")
    public Integer getCyYj() {
        return cyYj;
    }

    public void setCyYj(Integer cyYj) {
        this.cyYj = cyYj;
    }

    @Basic
    @Column(name = "CY_WJ")
    public Integer getCyWj() {
        return cyWj;
    }

    public void setCyWj(Integer cyWj) {
        this.cyWj = cyWj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SjjzBdDO that = (SjjzBdDO) o;

        if (bdid != null ? !bdid.equals(that.bdid) : that.bdid != null) return false;
        if (fybh != null ? !fybh.equals(that.fybh) : that.fybh != null) return false;
        if (ksNd != null ? !ksNd.equals(that.ksNd) : that.ksNd != null) return false;
        if (ksYf != null ? !ksYf.equals(that.ksYf) : that.ksYf != null) return false;
        if (ksTs != null ? !ksTs.equals(that.ksTs) : that.ksTs != null) return false;
        if (jsNd != null ? !jsNd.equals(that.jsNd) : that.jsNd != null) return false;
        if (jsYf != null ? !jsYf.equals(that.jsYf) : that.jsYf != null) return false;
        if (jsTs != null ? !jsTs.equals(that.jsTs) : that.jsTs != null) return false;
        if (jzrhXs != null ? !jzrhXs.equals(that.jzrhXs) : that.jzrhXs != null) return false;
        if (jzrhJc != null ? !jzrhJc.equals(that.jzrhJc) : that.jzrhJc != null) return false;
        if (jzrhYj != null ? !jzrhYj.equals(that.jzrhYj) : that.jzrhYj != null) return false;
        if (jzrhWj != null ? !jzrhWj.equals(that.jzrhWj) : that.jzrhWj != null) return false;
        if (bdXs != null ? !bdXs.equals(that.bdXs) : that.bdXs != null) return false;
        if (bdJc != null ? !bdJc.equals(that.bdJc) : that.bdJc != null) return false;
        if (bdYj != null ? !bdYj.equals(that.bdYj) : that.bdYj != null) return false;
        if (bdWj != null ? !bdWj.equals(that.bdWj) : that.bdWj != null) return false;
        if (cyXs != null ? !cyXs.equals(that.cyXs) : that.cyXs != null) return false;
        if (cyJc != null ? !cyJc.equals(that.cyJc) : that.cyJc != null) return false;
        if (cyYj != null ? !cyYj.equals(that.cyYj) : that.cyYj != null) return false;
        if (cyWj != null ? !cyWj.equals(that.cyWj) : that.cyWj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bdid != null ? bdid.hashCode() : 0;
        result = 31 * result + (fybh != null ? fybh.hashCode() : 0);
        result = 31 * result + (ksNd != null ? ksNd.hashCode() : 0);
        result = 31 * result + (ksYf != null ? ksYf.hashCode() : 0);
        result = 31 * result + (ksTs != null ? ksTs.hashCode() : 0);
        result = 31 * result + (jsNd != null ? jsNd.hashCode() : 0);
        result = 31 * result + (jsYf != null ? jsYf.hashCode() : 0);
        result = 31 * result + (jsTs != null ? jsTs.hashCode() : 0);
        result = 31 * result + (jzrhXs != null ? jzrhXs.hashCode() : 0);
        result = 31 * result + (jzrhJc != null ? jzrhJc.hashCode() : 0);
        result = 31 * result + (jzrhYj != null ? jzrhYj.hashCode() : 0);
        result = 31 * result + (jzrhWj != null ? jzrhWj.hashCode() : 0);
        result = 31 * result + (bdXs != null ? bdXs.hashCode() : 0);
        result = 31 * result + (bdJc != null ? bdJc.hashCode() : 0);
        result = 31 * result + (bdYj != null ? bdYj.hashCode() : 0);
        result = 31 * result + (bdWj != null ? bdWj.hashCode() : 0);
        result = 31 * result + (cyXs != null ? cyXs.hashCode() : 0);
        result = 31 * result + (cyJc != null ? cyJc.hashCode() : 0);
        result = 31 * result + (cyYj != null ? cyYj.hashCode() : 0);
        result = 31 * result + (cyWj != null ? cyWj.hashCode() : 0);
        return result;
    }
}

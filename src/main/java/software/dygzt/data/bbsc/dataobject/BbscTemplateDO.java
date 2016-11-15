package software.dygzt.data.bbsc.dataobject;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BbscTemplateDO entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="DY_BBSC_TEMPLATE")

public class BbscTemplateDO  implements java.io.Serializable {

    // Fields    

     private Integer id;
     private String name;
     private String yjCondition;
     private String colCondition;
     private String bblx;
     private String fyfw;
     private String bbbh;
     private String username;
     private String userfydm;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public BbscTemplateDO() {
    }

	/** minimal constructor */
    public BbscTemplateDO(Integer id, String yjCondition, String colCondition, String bblx, String fyfw) {
        this.id = id;
        this.yjCondition = yjCondition;
        this.colCondition = colCondition;
        this.bblx = bblx;
        this.fyfw = fyfw;
    }
    
    /** full constructor */
    public BbscTemplateDO(Integer id, String name, String yjCondition, String colCondition, String bblx, String fyfw, String bbbh, String username, String userfydm, Date cjsj) {
        this.id = id;
        this.name = name;
        this.yjCondition = yjCondition;
        this.colCondition = colCondition;
        this.bblx = bblx;
        this.fyfw = fyfw;
        this.bbbh = bbbh;
        this.username = username;
        this.userfydm = userfydm;
        this.cjsj = cjsj;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="name")

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="yjCondition", nullable=false)

    public String getYjCondition() {
        return this.yjCondition;
    }
    
    public void setYjCondition(String yjCondition) {
        this.yjCondition = yjCondition;
    }
    
    @Column(name="colCondition", nullable=false)

    public String getColCondition() {
        return this.colCondition;
    }
    
    public void setColCondition(String colCondition) {
        this.colCondition = colCondition;
    }
    
    @Column(name="bblx", nullable=false, length=20)

    public String getBblx() {
        return this.bblx;
    }
    
    public void setBblx(String bblx) {
        this.bblx = bblx;
    }
    
    @Column(name="fyfw", nullable=false, length=20)

    public String getFyfw() {
        return this.fyfw;
    }
    
    public void setFyfw(String fyfw) {
        this.fyfw = fyfw;
    }
    
    @Column(name="bbbh", length=20)

    public String getBbbh() {
        return this.bbbh;
    }
    
    public void setBbbh(String bbbh) {
        this.bbbh = bbbh;
    }
    
    @Column(name="username", length=20)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="userfydm", length=20)

    public String getUserfydm() {
        return this.userfydm;
    }
    
    public void setUserfydm(String userfydm) {
        this.userfydm = userfydm;
    }
    
    @Column(name="cjsj", length=23)

    public Date getCjsj() {
        return this.cjsj;
    }
    
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
   
}
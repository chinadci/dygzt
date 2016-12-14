package software.dygzt.service.user.model;

public class XtyhVO {
    private String yhm;
    private String name;
    private String fydm;

    public XtyhVO(String yhm, String name, String fydm) {
        this.yhm = yhm;
        this.name = name;
        this.fydm = fydm;
    }


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


    public void setFydm(String fydm) {

        this.fydm = fydm;
    }

    public String getFydm() {

        return fydm;
    }

}

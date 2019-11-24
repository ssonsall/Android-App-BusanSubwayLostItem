package dev.ssonsallsub.busansubwaylostitem;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("dest")
    @Expose
    private String dest;
    @SerializedName("finddate")
    @Expose
    private String finddate;
    @SerializedName("findplace")
    @Expose
    private String findplace;
    @SerializedName("ftime")
    @Expose
    private String ftime;
    @SerializedName("gubun")
    @Expose
    private String gubun;
    @SerializedName("lostoffice")
    @Expose
    private String lostoffice;
    @SerializedName("manageno")
    @Expose
    private String manageno;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("telno")
    @Expose
    private String telno;
    @SerializedName("title")
    @Expose
    private String title;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getFinddate() {
        return finddate;
    }

    public void setFinddate(String finddate) {
        this.finddate = finddate;
    }

    public String getFindplace() {
        return findplace;
    }

    public void setFindplace(String findplace) {
        this.findplace = findplace;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }

    public String getLostoffice() {
        return lostoffice;
    }

    public void setLostoffice(String lostoffice) {
        this.lostoffice = lostoffice;
    }

    public String getManageno() {
        return manageno;
    }

    public void setManageno(String manageno) {
        this.manageno = manageno;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
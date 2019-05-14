package JDBC.Dao;

import java.io.Serializable;

public class products implements Serializable {
    private  String pid;
    private  String pname;
    private  int qoh;
    private  String qoh_threshold;
    private  int original_price;
    private  float discnt_rate;
    private String sid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getQoh() {
        return qoh;
    }

    public void setQoh(int qoh) {
        this.qoh = qoh;
    }

    public String getQoh_threshold() {
        return qoh_threshold;
    }

    public void setQoh_threshold(String qoh_threshold) {
        this.qoh_threshold = qoh_threshold;
    }

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public float getDiscnt_rate() {
        return discnt_rate;
    }

    public void setDiscnt_rate(float discnt_rate) {
        this.discnt_rate = discnt_rate;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}

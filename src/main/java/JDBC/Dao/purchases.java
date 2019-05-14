package JDBC.Dao;

import java.io.Serializable;

public class purchases implements Serializable {
    private  int purid;
    private  String cid;
    private  String eid;
    private  String pid;
    private int qty;
    private String ptime;
    private int total_price;

    public int getPurid() {
        return purid;
    }

    public void setPurid(int purid) {
        this.purid = purid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}

package JDBC.Dao;

import java.io.Serializable;

public class employee implements Serializable {
    private  String eid;
    private  String ename;
    private  String city;

    public void setEid(String eid){
        this.eid=eid;
    }
    public String getEid(){
        return eid;
    }
    public void setEname(String ename){
        this.ename=ename;
    }
    public String getEname(){
        return ename;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String  getCity(){
        return city;
    }
}

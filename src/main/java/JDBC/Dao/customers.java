package JDBC.Dao;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class customers  {
    private  String cid;
    private  String cname;
    private  String city;
    private int visits_made;
    private String last_visit_time;

    public String getLast_visit_time() {
        return last_visit_time;
    }

    public void setLast_visit_time(String last_visit_time) {
        this.last_visit_time = last_visit_time;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getVisits_made() {
        return visits_made;
    }

    public void setVisits_made(int visits_made) {
        this.visits_made = visits_made;
    }


}

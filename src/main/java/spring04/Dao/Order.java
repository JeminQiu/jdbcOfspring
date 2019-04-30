package spring04.Dao;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    public String order_id;

    public String order_no;

    public Date date;

    public int quantity;
    public  void setOrder_id(String order_id){
        this.order_id = order_id;
    }
    public void setOrder_no(String order_no){
        this.order_no = order_no;
    }
    public void setDate(String Date){
        this.date=date;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public String getOrder_id(){
        return order_id;
    }
    public  String getOrder_no(){
        return order_no;
    }
    public  Date getDate(){
        return date;
    }
    public  int getQuantity(){
        return quantity;
    }
    /**
     * 省略 get set
     */
}

package JDBC.Impl;
import JDBC.Dao.*;
import JDBC.rowMapper.*;
import JDBC.rowMapper.employeesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class employeesImpl implements employeesDao {
     public String table;
     public int colSum;
     public  List<colName> list;
     @Autowired
     NamedParameterJdbcTemplate namedParameterJdbcTemplate;
     @Autowired
     JdbcTemplate jdbcTemplate;
     public  void setTable(String table){
         this.table=table;
     }
     public List<today> daySum(){
         String sql="select current_date as today," +
                 "TIMESTAMPDIFF(DAY,current_date(),cast('2019/07/15' as date))as remain";
         return namedParameterJdbcTemplate.query(sql,new todayRowMapper());
     }
     public boolean add(All all){
         String sql="insert into "+table;
         sql+="(";
         for (int i=0;i<colSum-1;i++)
             sql+=list.get(i).getName()+",";
         sql+=list.get(colSum-1).getName()+") values (";
         for (int i=0;i<colSum-1;i++)
             sql+=":"+list.get(i).getName()+",";
         sql+=":"+list.get(colSum-1).getName()+")";
         KeyHolder keyHolder = new GeneratedKeyHolder();
         namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(all), keyHolder);
         return  true;
     }

     public  List<colName> findCha(){
         String sql="desc "+table;
         list= namedParameterJdbcTemplate.query(sql,new structureRowMapper() );
         colSum=list.size();
         return namedParameterJdbcTemplate.query(sql,new structureRowMapper() );
     }
    public List<employee> findAll(){
         String sql="select * from "+table;
        List<employee>list= namedParameterJdbcTemplate.query(sql,new employeesRowMapper());
        return list;
    }
    public List<customers> findCustomer(){
        String sql="select * from "+table;
        List<customers>list= namedParameterJdbcTemplate.query(sql,new customersRowMapper());
        return list;
    }
    public List<suppliers> findSuppliers(){
        String sql="select * from "+table;
        List<suppliers>list= namedParameterJdbcTemplate.query(sql,new suppliersRowMapper());
        return list;
     }
    public List<logs> findLogs(){
        String sql="select * from "+table;
        List<logs> list= namedParameterJdbcTemplate.query(sql,new logsRowMapper());
        return list;
    }
    public List<purchases> findPurchases(){
        String sql="select * from "+table;
        List<purchases> list= namedParameterJdbcTemplate.query(sql,new purchaseRowmapper());
        return list;
     }
    public List<products> findProducts(){
        String sql="select * from "+table;
        List<products>list= namedParameterJdbcTemplate.query(sql,new productsRowMapper());
        return list;
     }
    public boolean delete(All all){
         //String sql="delete from employees where eid=:eid";
       String sql="delete from "+table+" where "+list.get(0).getName()+"=:"+list.get(0).getName();
        System.out.println(sql);
        namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(all));
        return true;
     }
    public boolean update(All all){
       //  String sql="update employees set eid=:eid ,ename=:ename ,city=:city where eid=:eid";
        String sql="update "+table+" set ";
        for (int i=0;i<colSum-1;i++)
            sql+=list.get(i).getName()+"=:"+list.get(i).getName()+",";
        sql+=list.get(colSum-1).getName()+"=:"+list.get(colSum-1).getName()+" where "+list.get(0).getName()+"=:"+list.get(0).getName();
        System.out.println(sql);
        namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(all));
         return true;
    }
    public employee get(String eid){
        String sql = "select * from user where eid=:eid";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("eid",eid);
        Object user = namedParameterJdbcTemplate.queryForObject(sql,sqlParameterSource,new employeesRowMapper());
        return (employee) user;

    }
    public employee getByname(String name){
         employee employee=new employee();
         return employee;
    }
}

class  structureRowMapper implements RowMapper<colName> {
    @Override
    public colName mapRow(ResultSet resultSet, int i) throws SQLException {
        colName colName = new colName();
        colName.setName(resultSet.getString("Field"));
        return colName;
    }
}
class colName {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}


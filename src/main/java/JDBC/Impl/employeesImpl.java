package JDBC.Impl;
import JDBC.Dao.*;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class employeesImpl implements employeesDao {
     @Autowired
     NamedParameterJdbcTemplate namedParameterJdbcTemplate;
     public boolean add(employee emp){
         String sql = "insert into employees (eid, ename, city) values (:eid, :ename, :city)";
         KeyHolder keyHolder = new GeneratedKeyHolder();
         namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(emp), keyHolder);
         //long order_id = keyHolder.getKey().longValue();
       //  emp.setOrder_id(order_id);
        // return order_id;
         return  true;
     }
    public List<employee> findAll(){
         String sql="select * from employees";
         return namedParameterJdbcTemplate.query(sql,new employeesRowMapper());
    }
    public boolean delete(employee employee){
         String sql="delete from employees where eid=:eid";
         namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(employee));
        return true;
     }
    public boolean update(employee employee){
         String sql="update employees set eid=:eid ,ename=:ename ,city=:city where eid=:eid";
         namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(employee));
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
    public static  void main(String[] args){
         employeesImpl emp=new employeesImpl();
         List<employee> list=emp.findAll();
         while (list.size()>0){
             System.out.println(list.get(0).getEid()+" "+list.get(0).getEname());
             list.remove(0);
         }
    }
}
class employeesRowMapper implements RowMapper<employee> {
    @Override
    public employee  mapRow(ResultSet resultSet, int i) throws SQLException {
        employee employee=new employee();
        employee.setEid(resultSet.getString("eid"));
        employee.setEname(resultSet.getString("ename"));
        employee.setCity(resultSet.getString("city"));
        return employee;
    }
}
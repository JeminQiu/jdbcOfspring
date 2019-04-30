package JDBC.Dao;

import java.util.List;

public interface employeesDao {
      boolean add(employee emp);
      List<employee> findAll();
      boolean delete(employee employee);
      boolean update(employee employee);
      employee get(String eid);
      employee getByname(String name);

}

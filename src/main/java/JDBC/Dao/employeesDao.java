package JDBC.Dao;

import java.util.List;

public interface employeesDao {
      boolean add(All all);
      List<employee> findAll();
      boolean delete(All all);
      boolean update(All all);
      employee get(String eid);
      employee getByname(String name);

}

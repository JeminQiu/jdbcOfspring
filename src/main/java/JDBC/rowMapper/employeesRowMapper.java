package JDBC.rowMapper;

import JDBC.Dao.employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class employeesRowMapper implements RowMapper<employee> {
    @Override
    public employee mapRow(ResultSet resultSet, int i) throws SQLException {
        employee employee = new employee();
        employee.setEid(resultSet.getString("eid"));
        employee.setEname(resultSet.getString("ename"));
        employee.setCity(resultSet.getString("city"));
        return employee;
    }
}

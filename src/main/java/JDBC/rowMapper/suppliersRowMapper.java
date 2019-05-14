package JDBC.rowMapper;

import JDBC.Dao.suppliers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class suppliersRowMapper implements RowMapper<suppliers> {
    @Override
    public suppliers mapRow(ResultSet resultSet, int i) throws SQLException {
        suppliers logs=new suppliers();
        logs.setCity(resultSet.getString("city"));
        logs.setSid(resultSet.getString("sid"));
        logs.setSname(resultSet.getString("sname"));
        logs.setTelephone_no(resultSet.getString("telephone_no"));
        return logs;
    }
}
package JDBC.rowMapper;

import JDBC.Dao.today;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class todayRowMapper implements RowMapper<today> {
    @Override
    public today mapRow(ResultSet resultSet , int t) throws SQLException {
        today today=new today();
        today.setToday(resultSet.getString("today"));
        today.setRemain(resultSet.getInt("remain"));
        return today;
    }
}

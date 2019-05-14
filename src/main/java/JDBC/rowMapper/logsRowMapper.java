package JDBC.rowMapper;

import JDBC.Dao.logs;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class logsRowMapper implements RowMapper<logs> {
    @Override
    public logs mapRow(ResultSet resultSet, int i) throws SQLException {
        logs logs=new logs();
        logs.setTime(resultSet.getTime("time"));
        logs.setLogid(resultSet.getInt("logid"));
        logs.setKey_value(resultSet.getString("key_value"));
        logs.setOperation(resultSet.getString("operation"));
        logs.setTable_name(resultSet.getString("table_name"));
        logs.setWho(resultSet.getString("who"));
        return logs;
    }
}

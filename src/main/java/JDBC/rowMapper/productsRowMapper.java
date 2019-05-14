package JDBC.rowMapper;

import JDBC.Dao.products;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class productsRowMapper implements RowMapper<products> {
    @Override
    public products mapRow(ResultSet resultSet, int i) throws SQLException {
        products logs=new products();
        logs.setPname(resultSet.getString("pname"));
        logs.setQoh(resultSet.getInt("qoh"));
        logs.setQoh_threshold(resultSet.getString("qoh_threshold"));
        logs.setSid(resultSet.getString("sid"));
        logs.setOriginal_price(resultSet.getInt("original"));
        logs.setDiscnt_rate(resultSet.getFloat("discnt_rate"));
        return logs;
    }
}

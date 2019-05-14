package JDBC.rowMapper;

import JDBC.Dao.purchases;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class purchaseRowmapper implements RowMapper<purchases> {
    @Override
    public purchases mapRow(ResultSet resultSet, int i) throws SQLException {
        purchases logs=new purchases();
        logs.setPtime(resultSet.getString("ptime"));
        logs.setCid(resultSet.getString("cid"));
        logs.setEid(resultSet.getString("eid"));
        logs.setPid(resultSet.getString("pid"));
        logs.setTotal_price(resultSet.getInt("total_price"));
        logs.setPurid(resultSet.getInt("purid"));
        logs.setQty(resultSet.getInt("qty"));
        return logs;
    }
}

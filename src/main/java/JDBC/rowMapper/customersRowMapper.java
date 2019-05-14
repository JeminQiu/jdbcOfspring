package JDBC.rowMapper;

import JDBC.Dao.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class customersRowMapper  implements RowMapper<customers> {
    @Override
    public customers mapRow(ResultSet resultSet, int i) throws SQLException {
        customers colName = new customers();
        colName.setCid(resultSet.getString("cid"));
        colName.setCity(resultSet.getString("city"));
        colName.setCname(resultSet.getString("cname"));
        colName.setVisits_made(resultSet.getInt("visits_made"));
       // colName.setLast_visit_time(resultSet.getDate("last_visit_time").toString()+" "+ resultSet.getTime("last_visit_time"));
       colName.setLast_visit_time(resultSet.getString("last_visit_time"));
       //System.out.println(resultSet.getTimestamp("last_visit_time"));
        return colName;
    }
}


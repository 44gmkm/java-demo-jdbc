package es.cipfpbatoi.damb.UD02A03.persistencia.repository.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Demo;

public class DemoResultSetExtractor implements ResultSetExtractor<Demo>{

    @Override
    public Demo extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (rs.next()) {
            Demo demo = new Demo();

            demo.setId(rs.getInt("demo_id"));
            demo.setVarchar(rs.getString("varchar"));
            demo.setMyFloat(rs.getFloat("float"));
            demo.setMyBool(rs.getBoolean("bool"));
            demo.setMyChar(rs.getString("char").charAt(0));
            demo.setMyDate(rs.getDate("date"));

            return demo;
        }
        return null;
    }
    
}

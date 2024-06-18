package es.cipfpbatoi.damb.UD02A03.persistencia.repository.demo;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class DemoRowMapper implements RowMapper<Demo>{

    @Override
    public Demo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Demo demo = new Demo();

        demo.setId(rs.getInt("demo_id"));
        demo.setVarchar(rs.getString("varchar"));
        demo.setMyFloat(rs.getFloat("float"));
        demo.setMyBool(rs.getBoolean("bool"));
        demo.setMyChar(rs.getString("char").charAt(0));
        demo.setMyDate(rs.getDate("date"));

        return demo;
    }
    
}

package es.cipfpbatoi.damb.UD02A03.persistencia.repository.demo;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DemoJDBCTemplateRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM demo";

    private static final String SELECT_ALL = "SELECT * FROM demo";
    private static final String SELECT_BY_ID = "SELECT * FROM demo WHERE demo_id = ?";

    private static final String INSERT = "INSERT INTO demo " +
            "(varchar, float, bool, char, date) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE demo " +
            "SET varchar = ?, float = ?, bool = ?, char = ?, date = ?" +
            "WHERE demo_id = ?";

    private static final String DELETE = "DELETE FROM demo WHERE demo_id = ?";

    public int count() {
        return this.jdbcTemplate.queryForObject(
                SELECT_COUNT,
                Integer.class);
    }

    public List<Demo> findAll() {
        return this.jdbcTemplate.query(
            con -> con.prepareStatement(
                SELECT_ALL,
                Statement.CLOSE_CURRENT_RESULT),
                new DemoRowMapper());
    }

    public Demo findById(Integer id) {
        return this.jdbcTemplate.query(
            new PreparedStatementCreator() {
                @Override
                public PreparedStatement
                createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(
                        SELECT_BY_ID,
                        Statement.CLOSE_CURRENT_RESULT);
                        ps.setInt(1, id);
                        return ps;
                }
            },
        new DemoResultSetExtractor());
    }

    public boolean insert(Demo demo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int resultado = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement
                    createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                INSERT,
                                Statement.CLOSE_CURRENT_RESULT);

                            ps.setString(1, demo.getVarchar());
                            ps.setFloat(2, demo.getMyFloat());
                            ps.setBoolean(3, demo.isMyBool());
                            ps.setString(4, String.valueOf(demo.getMyChar()));
                            ps.setDate(5, demo.getMyDate());
                        return ps;
                    }
                },
                keyHolder);

        if (resultado != 1)
            return false;
        
        demo.setId(keyHolder.getKey().intValue());
        return true;
    }

    public boolean update(Demo demo){
        int resultado = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement
                    createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                UPDATE,
                                Statement.CLOSE_CURRENT_RESULT);
                            ps.setString(1, demo.getVarchar());
                            ps.setFloat(2, demo.getMyFloat());
                            ps.setBoolean(3, demo.isMyBool());
                            ps.setString(4, String.valueOf(demo.getMyChar()));
                            ps.setDate(5, demo.getMyDate());
                            ps.setInt(6, demo.getId());
            
                            return ps;
                    }
                });
        return resultado == 1;
    }

    public boolean delete(Demo demo){
        int resultado = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement
                    createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                DELETE,
                                Statement.CLOSE_CURRENT_RESULT);
                        ps.setInt(1, demo.getId());
                        return ps;
                    }
                });
        return resultado == 1;
    }
}

package es.cipfpbatoi.damb.UD02A03.persistencia.repository.tripulantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Tripulante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
@Repository
public class TripulantesJdbcTemplateRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM barcos";

    private static final String SELECT_ALL = "SELECT * FROM tripulantes";
    private static final String SELECT_BY_ID = "SELECT * FROM tripulantes WHERE tripulante_id = ?";

    private static final String INSERT = "INSERT INTO tripulantes " +
            "(nombre, apellido1, apellido2, rol, barco) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE tripulantes " +
            "SET nombre = ?, apellido1 = ?, apellido2 = ?, rol = ?, barco = ? " +
            "WHERE tripulante_id = ?";

    private static final String DELETE = "DELETE FROM tripulantes WHERE tripulante_id = ?";

    public int count() {
        return this.jdbcTemplate.queryForObject(
                SELECT_COUNT,
                Integer.class);
    }

    public List<Tripulante> findAll() {
        return this.jdbcTemplate.query(
            con -> con.prepareStatement(
                SELECT_ALL,
                Statement.CLOSE_CURRENT_RESULT),
                new TripulantesRowMapper());
    }

    public Tripulante findById(Integer id) {
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
                new TripulantesResultSetExtractor());
    }

    public boolean insert(Tripulante tripulante) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int resultado = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement
                    createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                INSERT,
                                Statement.CLOSE_CURRENT_RESULT);

                        ps.setString(1, tripulante.getNombre());
                        ps.setString(2, tripulante.getApellido1());
                        ps.setString(3, tripulante.getApellido2());
                        ps.setString(4, tripulante.getRol());
                        ps.setInt(5, tripulante.getIdBarco());
                        return ps;
                    }
                },
                keyHolder);

        if (resultado != 1)
            return false;

        tripulante.setId(keyHolder.getKey().intValue());
        return true;
    }

    public boolean update(Tripulante tripulante){
        int resultado = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement
                    createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                UPDATE,
                                Statement.CLOSE_CURRENT_RESULT);
                        ps.setString(1, tripulante.getNombre());
                        ps.setString(2, tripulante.getApellido1());
                        ps.setString(3, tripulante.getApellido2());
                        ps.setString(4, tripulante.getRol());
                        ps.setInt(5, tripulante.getIdBarco());
                        ps.setInt(6, tripulante.getId());
                        return ps;
                    }
                });
        return resultado == 1;
    }

    public boolean delete(Tripulante tripulante){
        int resultado = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement
                    createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                DELETE,
                                Statement.CLOSE_CURRENT_RESULT);
                        ps.setInt(1, tripulante.getId());
                        return ps;
                    }
                });
        return resultado == 1;
    }
}

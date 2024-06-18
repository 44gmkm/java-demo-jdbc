package es.cipfpbatoi.damb.UD02A03.persistencia.repository.barcos;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Barco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class BarcosJDBCTemplateRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM barcos";

    private static final String SELECT_ALL = "SELECT * FROM barcos";
    private static final String SELECT_BY_ID = "SELECT * FROM barcos WHERE barco_id = ?";

    private static final String FIND_BY_ID_WITH_TRIPULACION = 
            "SELECT BARCOS.BARCO_ID, BARCOS.NOMBRE, BARCOS.ESLORA, BARCOS.POTENCIA_CV, BARCOS.POTENCIA_KW, BARCOS.ES_EXTRANGERO,TRIPULANTES.TRIPULANTE_ID,  TRIPULANTES.NOMBRE, TRIPULANTES.APELLIDO1, TRIPULANTES.APELLIDO2, TRIPULANTES.ROL "+
            "FROM BARCOS " +
            "INNER JOIN TRIPULANTES ON BARCOS.BARCO_ID = TRIPULANTES.BARCO " +
            "WHERE BARCOS.BARCO_ID = ?";

    private static final String INSERT = "INSERT INTO barcos " +
            "(nombre, eslora, potencia_cv, potencia_kw, es_extrangero) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE barcos " +
            "SET nombre = ?, eslora = ?, potencia_cv = ?, potencia_kw = ?, es_extrangero = ? " +
            "WHERE barco_id = ?";

    private static final String DELETE = "DELETE FROM barcos WHERE barco_id = ?";

    public int count() {
        return this.jdbcTemplate.queryForObject(
                SELECT_COUNT,
                Integer.class);
    }

    public List<Barco> findAll() {
        return this.jdbcTemplate.query(
            con -> con.prepareStatement(
                SELECT_ALL,
                Statement.CLOSE_CURRENT_RESULT),
                new BarcosRowMapper());
    }

    public Barco findById(Integer id) {
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
                new BarcosResultSetExtractor());
    }

    public boolean insert(Barco barco) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int resultado = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement
                    createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                INSERT,
                                Statement.CLOSE_CURRENT_RESULT);

                        ps.setString(1, barco.getNombre());
                        ps.setInt(2, barco.getEslora());
                        ps.setFloat(3, barco.getPotenciaCv());
                        ps.setDouble(4, barco.getPotenciaKw());
                        ps.setBoolean(5, barco.esExtrangero());
                        return ps;
                    }
                },
                keyHolder);

        if (resultado != 1)
            return false;

        barco.setId(keyHolder.getKey().intValue());
        return true;
    }

    public boolean update(Barco barco){
        int resultado = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement
                    createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                UPDATE,
                                Statement.CLOSE_CURRENT_RESULT);
                        ps.setString(1, barco.getNombre());
                        ps.setInt(2, barco.getEslora());
                        ps.setFloat(3, barco.getPotenciaCv());
                        ps.setDouble(4, barco.getPotenciaKw());
                        ps.setBoolean(5, barco.esExtrangero());
                        ps.setInt(6, barco.getId());
                        return ps;
                    }
                });
        return resultado == 1;
    }

    public boolean delete(Barco barco){
        int resultado = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement
                    createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                DELETE,
                                Statement.CLOSE_CURRENT_RESULT);
                        ps.setInt(1, barco.getId());
                        return ps;
                    }
                });
        return resultado == 1;
    }

    public Barco findByIdWithTripulacion(Integer id) {
        return this.jdbcTemplate.query(
            new PreparedStatementCreator() {
                @Override
                public PreparedStatement
                createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(
                            FIND_BY_ID_WITH_TRIPULACION,
                            Statement.CLOSE_CURRENT_RESULT);
                    ps.setInt(1, id);
                    return ps;
                }
            }, 
            new BarcosWithTripulacionResultSetExtractor());
    }
}

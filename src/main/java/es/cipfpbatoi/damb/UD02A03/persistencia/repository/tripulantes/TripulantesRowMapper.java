package es.cipfpbatoi.damb.UD02A03.persistencia.repository.tripulantes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Tripulante;

public class TripulantesRowMapper implements RowMapper<Tripulante> {

    @Override
    public Tripulante mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tripulante tripulante = new Tripulante();

        tripulante.setId(rs.getInt("tripulante_id"));
        tripulante.setNombre(rs.getString("nombre"));
        tripulante.setApellido1(rs.getString("apellido1"));
        tripulante.setApellido2(rs.getString("apellido2"));
        tripulante.setRol(rs.getString("rol"));
        tripulante.setIdBarco(rs.getInt("barco"));

        return tripulante;
    }
    
}

package es.cipfpbatoi.damb.UD02A03.persistencia.repository.tripulantes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Tripulante;

public class TripulantesResultSetExtractor implements ResultSetExtractor<Tripulante>{

    @Override
    public Tripulante extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (rs.next()) {
            Tripulante tripulante = new Tripulante();

            tripulante.setId(rs.getInt("tripulante_id"));
            tripulante.setNombre(rs.getString("nombre"));
            tripulante.setApellido1(rs.getString("apellido1"));
            tripulante.setApellido2(rs.getString("apellido2"));
            tripulante.setRol(rs.getString("rol"));
            tripulante.setIdBarco(rs.getInt("barco"));
    
            return tripulante;
        }
        return null;
    }
    
}

package es.cipfpbatoi.damb.UD02A03.persistencia.repository.barcos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Barco;
import es.cipfpbatoi.damb.UD02A03.persistencia.model.Tripulante;

public class BarcosWithTripulacionResultSetExtractor implements ResultSetExtractor<Barco>{

    @Override
    public Barco extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (!rs.next()){
            return null; 
        }

        Barco barco = new Barco();
        barco.setId(rs.getInt("barcos.barco_id"));
        barco.setNombre(rs.getString("barcos.nombre"));
        barco.setEslora(rs.getInt("barcos.eslora"));
        barco.setPotenciaCv(rs.getFloat("barcos.potencia_cv")); 
        barco.setPotenciaKw(rs.getDouble("barcos.potencia_kw"));
        barco.setEsExtrangero(rs.getBoolean("barcos.es_extrangero"));

        barco.setTripulacion(new ArrayList<>());

        do {
            Tripulante tripulante = new Tripulante();
    
            tripulante.setId(rs.getInt("tripulantes.tripulante_id"));
            tripulante.setNombre(rs.getString("tripulantes.nombre"));
            tripulante.setApellido1(rs.getString("tripulantes.apellido1"));
            tripulante.setApellido2(rs.getString("tripulantes.apellido2"));
            tripulante.setRol(rs.getString("tripulantes.rol"));
            tripulante.setIdBarco(rs.getInt("barcos.barco_id"));
    
            barco.addTripulante(tripulante);
        } while (rs.next());
    
        return barco;
    }
    
}

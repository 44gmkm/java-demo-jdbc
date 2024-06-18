package es.cipfpbatoi.damb.UD02A03.persistencia.repository.barcos;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Barco;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

//  `barco_id` bigint NOT NULL AUTO_INCREMENT,
//  `nombre` varchar(30) NOT NULL DEFAULT '',
//  `eslora` smallint NOT NULL DEFAULT '0',
//  `potencia_cv` float NOT NULL DEFAULT '0',
//  `potencia_w` double NOT NULL DEFAULT '0',
//  `es_extrangero` boolean DEFAULT 'false',

public class BarcosResultSetExtractor implements ResultSetExtractor<Barco> {
    @Override
    public Barco extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (rs.next()){
            Barco barco = new Barco();
            
            barco.setId(rs.getInt("barco_id"));
            barco.setNombre(rs.getString("nombre"));
            barco.setEslora(rs.getInt("eslora"));
            barco.setPotenciaCv(rs.getFloat("potencia_cv"));
            barco.setPotenciaKw(rs.getDouble("potencia_kw"));
            barco.setEsExtrangero(rs.getBoolean("es_extrangero"));

            return barco;
        }
        return null;
    }
}

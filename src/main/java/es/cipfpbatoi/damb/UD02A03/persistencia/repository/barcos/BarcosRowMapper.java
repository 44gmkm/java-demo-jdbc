package es.cipfpbatoi.damb.UD02A03.persistencia.repository.barcos;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Barco;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//  `barco_id` bigint NOT NULL AUTO_INCREMENT,
//  `nombre` varchar(30) NOT NULL DEFAULT '',
//  `eslora` smallint NOT NULL DEFAULT '0',
//  `potencia_cv` float NOT NULL DEFAULT '0',
//  `potencia_w` double NOT NULL DEFAULT '0',
//  `es_extrangero` boolean DEFAULT 'false',

public class BarcosRowMapper implements RowMapper<Barco> {
    @Override
    public Barco mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        Barco barco = new Barco();
        barco.setId(resultSet.getInt("barco_id"));
        barco.setNombre(resultSet.getString("nombre"));
        barco.setEslora(resultSet.getInt("eslora"));
        barco.setPotenciaCv(resultSet.getFloat("potencia_cv"));
        barco.setPotenciaKw(resultSet.getDouble("potencia_kw"));
        barco.setEsExtrangero(resultSet.getBoolean("es_extrangero"));

        return barco;
    }
}

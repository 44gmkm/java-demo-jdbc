package es.cipfpbatoi.damb.UD02A03.persistencia.model;

import java.util.List;
import java.util.Objects;

public class Barco {
    //  `barco_id` bigint NOT NULL AUTO_INCREMENT,
    //  `nombre` varchar(30) NOT NULL DEFAULT '',
    //  `eslora` smallint NOT NULL DEFAULT '0',
    //  `potencia_cv` float NOT NULL DEFAULT '0',
    //  `potencia_w` double NOT NULL DEFAULT '0',
    //  `es_extrangero` boolean DEFAULT 'false',

    private Integer id;
    private String nombre;
    private Integer eslora;
    private float potenciaCv;
    private double potenciaKw;
    private boolean esExtrangero;

    private List<Tripulante> tripulacion;
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        return true;
        if (obj == null)
        return false;
        if (getClass() != obj.getClass())
        return false;
        Barco other = (Barco) obj;
        return Objects.equals(id, other.id);
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEslora() {
        return eslora;
    }
    
    public void setEslora(Integer eslora) {
        this.eslora = eslora;
    }
    
    public float getPotenciaCv() {
        return potenciaCv;
    }
    
    public void setPotenciaCv(float potenciaCv) {
        this.potenciaCv = potenciaCv;
    }
    
    public double getPotenciaKw() {
        return potenciaKw;
    }

    public void setPotenciaKw(double potenciaKw) {
        this.potenciaKw = potenciaKw;
    }
    
    public boolean esExtrangero() {
        return esExtrangero;
    }
    
    public void setEsExtrangero(boolean esExtrangero) {
        this.esExtrangero = esExtrangero;
    }

    public List<Tripulante> getTripulacion() {
        return tripulacion;
    }
    
    public void setTripulacion(List<Tripulante> tripulacion) {
        this.tripulacion = tripulacion;
    }

    public void addTripulante(Tripulante tripulante){
        this.tripulacion.add(tripulante);
    }
}

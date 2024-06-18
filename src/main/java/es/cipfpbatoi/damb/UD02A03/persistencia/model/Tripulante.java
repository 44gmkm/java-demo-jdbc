package es.cipfpbatoi.damb.UD02A03.persistencia.model;

import java.util.Objects;

public class Tripulante {
    //`tripulante_id` bigint NOT NULL AUTO_INCREMENT,
    //`nombre` varchar(30) NOT NULL DEFAULT '',
    //`apellido1` varchar(30) NOT NULL DEFAULT '',
    //`apellido2` varchar(30) NOT NULL DEFAULT '',
    //`rol` varchar(60) NOT NULL DEFAULT '',
    //`barco` bigint,

    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String rol;
    private Integer idBarco;

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
        Tripulante other = (Tripulante) obj;
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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getIdBarco() {
        return idBarco;
    }

    public void setIdBarco(Integer barco_id) {
        this.idBarco = barco_id;
    }

    
}

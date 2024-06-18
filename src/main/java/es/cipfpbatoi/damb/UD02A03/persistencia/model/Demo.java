package es.cipfpbatoi.damb.UD02A03.persistencia.model;

import java.sql.Date;
import java.util.Objects;

public class Demo {
    //  `demo_id` bigint NOT NULL AUTO_INCREMENT, 
    //  `float` float,
    //  `varchar` varchar,
    //  `bool` boolean,
    //  `char` nchar,
    //  `date` date,

    private Integer id;
    private float myFloat;
    private String varchar;
    
    private boolean myBool;
    private char myChar;
    private Date myDate;
    
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
        Demo other = (Demo) obj;
        return Objects.equals(id, other.id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getVarchar() {
        return varchar;
    }

    public void setVarchar(String varchar) {
        this.varchar = varchar;
    }
    
    public float getMyFloat() {
        return myFloat;
    }

    public void setMyFloat(float myFloat) {
        this.myFloat = myFloat;
    }
    
    public boolean isMyBool() {
        return myBool;
    }

    public void setMyBool(boolean myBool) {
        this.myBool = myBool;
    }

    public char getMyChar() {
        return myChar;
    }

    public void setMyChar(char myChar) {
        this.myChar = myChar;
    }

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }
    
}

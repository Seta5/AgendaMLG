package agendamlg;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Super_Usuario extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellidos;
    private String dni;
    private Long telefono;
    private String domicilio;
    
    private Date fecha_nacimiento;

    @Column(nullable = false)
    public String getNombre(){
        return nombre;
    }
    @Column(nullable = false)
    public String getApellidos(){
        return apellidos;
    }
    @Column(nullable = false)
    public String getDni(){
        return dni;
    }
    @Column(nullable = false)
    public Long getTelefono(){
        return telefono;
    }
    public String getDomicilio(){
        return domicilio;
    }
    @Temporal(TemporalType.DATE)
    public Date getFecha_nacimiento(){
        return fecha_nacimiento;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    public void setDni(String dni){
        this.dni = dni;
    }
    public void setTelefono(Long telefono){
        this.telefono = telefono;
    }
    public void setDomicilio(String domicilio){
        this.domicilio = domicilio;
    }
    public void setFecha_nacimiento(Date fecha_nacimiento){
        this.fecha_nacimiento = fecha_nacimiento;
    }
}

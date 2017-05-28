package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String cuenta;
    private String password;
    private String email;
//    @ManyToMany
//    @JoinTable(name = "userEvent", joinColumns = @JoinColumn(name = "usuario_fk"),
//            inverseJoinColumns = @JoinColumn(name = "evento_fk"))
//    private List<Evento> asistencia;

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<Evento> getAsistencia() {
//        return asistencia;
//    }

//    public void setAsistencia(List<Evento> asistencia) {
//        this.asistencia = asistencia;
//    }
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuenta != null ? cuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.cuenta == null && other.cuenta != null) || (this.cuenta != null && !this.cuenta.equals(other.cuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usuario[ cuenta=" + cuenta + " ]";
    }
}

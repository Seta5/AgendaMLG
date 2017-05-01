package entities;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class UsuarioAutorizado extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String Organizacion;

    public String getOrganizacion() {
        return Organizacion;
    }

    public void setOrganizacion(String Organizacion) {
        this.Organizacion = Organizacion;
    }
    
}

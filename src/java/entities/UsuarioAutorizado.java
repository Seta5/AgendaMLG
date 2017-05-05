package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class UsuarioAutorizado extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String organizacion;

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }
}

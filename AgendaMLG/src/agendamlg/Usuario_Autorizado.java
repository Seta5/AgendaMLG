package agendamlg;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Usuario_Autorizado extends Usuario implements Serializable{

    private static final long serialVersionUID = 1L;
    private String organizacion;
    
    @Column(nullable = false)
    public String getOrganizacion(){
        return organizacion;
    }

    public void setOrganizacion(String organizacion){
        this.organizacion = organizacion;
    }
}

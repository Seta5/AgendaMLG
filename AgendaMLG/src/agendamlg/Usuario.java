package agendamlg;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre_usuario;
    private String password;
    private List<Evento> asistencia;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId_usuario() {
        return id;
    }
    @Column(nullable = false)
    public String getNombre_usuario(){
        return nombre_usuario;
    }
    @Column(nullable = false)
    public String getPassword(){
        return password;
    }
    @ManyToMany
    @JoinTable(name = "user_event", joinColumns = @JoinColumn(name = "usuario_fk"),
            inverseJoinColumns = @JoinColumn(name = "evento_fk"))
    public List<Evento> getAsistencia(){
        return asistencia;
    }

    public void setId_usuario(Long id) {
        this.id = id;
    }
    public void setNombre_usuario(String nombre){
        this.nombre_usuario = nombre;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setAsistencia(List<Evento> asistencia){
        this.asistencia = asistencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "agendamlg.Usuario[ id=" + id + " ]";
    }
    
}

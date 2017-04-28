package agendamlg;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Date fecha_inicio;
    private Date fecha_fin;  
    private Date fecha_entrada;       
    private int valoracion;
    private boolean permanente;
    private boolean destacado;
    private boolean validado;
    private String ubicacion;
    private List<Usuario> apuntados;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha_Inicio		(Date dat)	{this.fecha_inicio	= dat;}
    public void setFecha_Fin		(Date dat)	{this.fecha_fin		= dat;}
    public void setFecha_Entrada	(Date dat)	{this.fecha_entrada	= dat;}
    public void setValoracion(int num){ this.valoracion= num;}
    public void setPermanente	(boolean chck){this.permanente	= chck;}
    public void setDestacado	(boolean chck){this.destacado	= chck;}
    public void setValidado		(boolean chck){this.validado	= chck;}
    public void setUbicacion (String ubicacion){this.ubicacion = ubicacion;}
    public void setApuntados (List<Usuario> apuntados){this.apuntados = apuntados;}
    
    @Temporal(TemporalType.DATE)
    public Date getFecha_Inicio		(){return fecha_inicio	;}
    @Temporal(TemporalType.DATE)
    public Date getFecha_Fin		(){return fecha_fin		;}
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getFecha_Entrada	(){return fecha_entrada	;}
    @Column(nullable = false)
    public boolean getPermanente	(){return permanente;}
    @Column(nullable = false)
    public boolean getDestacado		(){return destacado	;}
    @Column(nullable = false)
    public boolean getValidado		(){return validado	;}	
    public int getValoracion 		(){return valoracion;}
    public String getUbicacion      (){return ubicacion;}
    @ManyToMany(mappedBy = "asistencia")
    public List<Usuario> getApuntados  (){return apuntados;}
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "agendamlg.Evento[ id=" + id + " ]";
    }
    
}

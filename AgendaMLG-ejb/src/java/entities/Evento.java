package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;  
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaEntrada;       
    private int valoracion;
    @Column(nullable = false)
    private boolean permanente;
    @Column(nullable = false)
    private boolean destacado;
    @Column(nullable = false)
    private boolean validado;
    private String ubicacion;
    @ManyToMany(mappedBy = "asistencia")
    private List<Usuario> apuntados;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public int getValoracion() {
        return valoracion;
    }

    public boolean isPermanente() {
        return permanente;
    }

    public boolean isDestacado() {
        return destacado;
    }

    public boolean isValidado() {
        return validado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public List<Usuario> getApuntados() {
        return apuntados;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public void setPermanente(boolean permanente) {
        this.permanente = permanente;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setApuntados(List<Usuario> apuntados) {
        this.apuntados = apuntados;
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
        return "entities.Evento[ id=" + id + " ]";
    }
    
}

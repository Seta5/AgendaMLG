package beans;

import entity.Evento;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "controlEvento")
@ViewScoped
public class ControlEvento implements Serializable{
    private Evento evento;
    private boolean temporal;
    
    public ControlEvento(){
        evento = new Evento();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }
    
    public String siguiente(){
        if(temporal) return null;
        else return "main.xhtml";
    }
    public String enviar(){
        if (evento.getFechaInicio().after(evento.getFechaFin())){
            FacesMessage fm = new FacesMessage("Fecha de finalización anterior a la de inicio.");
            FacesContext.getCurrentInstance().addMessage("evento:fin",fm);
            return null;
        }
        return "main.xhtml";
    }
}

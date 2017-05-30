package beans;

import ejb.EventException;
import ejb.NegocioLocal;
import entity.Evento;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "controlEvento")
@ViewScoped
public class ControlEvento implements Serializable{
    private Evento evento;
    private boolean temporal;
    
    @Inject
    private NegocioLocal negocio;
    
    
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
        if(!temporal){
            try{
                negocio.registrarEvento(evento);
                return "main.xhtml";
            }catch(EventException e){
                
            }
        }
        return null;
    }
    public String enviar(){
        if (!evento.getFechaInicio().after(evento.getFechaFin())){
            try{
                negocio.registrarEvento(evento);
                return "main.xhtml";
            }catch(EventException e){
                FacesMessage fm = new FacesMessage("Nombre de evento en uso.");
                FacesContext.getCurrentInstance().addMessage("evento:nombre", fm);
            }
        }else{
            FacesMessage fm = new FacesMessage("Fecha de finalización anterior a la de inicio.");
            FacesContext.getCurrentInstance().addMessage("evento:fin",fm);
        }
        return null;
    }
}

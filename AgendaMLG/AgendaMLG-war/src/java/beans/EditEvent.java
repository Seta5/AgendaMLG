import beans.Sesion;
import ejb.EventException;
import ejb.NegocioLocal;
import entity.Evento;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "editEvento")
@SessionScoped
public class EditEvent implements Serializable{
    private Evento evento;
    private boolean temporal;
    
    @Inject
    private NegocioLocal negocio;
    @Inject
    private Sesion sesion;
    
    
    public EditEvent(){
        temporal = false;
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
        return null;
    }
        
    public String modificarEvento(Evento evento){
        setEvento(evento);
        return "editEvent.xhtml";
        
    }
    
    public String enviar(){
        evento.setPermanente(!temporal);
        
        if(evento.isPermanente()){
            evento.setFechaInicio(null);
            evento.setFechaFin(null);
            negocio.modificarEvento(evento);
            return "main.xhtml";
        }else{
            if((evento.getFechaInicio()==null)||(evento.getFechaFin()==null)){
                if(evento.getFechaInicio()==null){
                    FacesMessage fm = new FacesMessage("Indica fecha de inicio del evento.");
                    FacesContext.getCurrentInstance().addMessage("editEvento:inicio", fm);					
                }
                if(evento.getFechaFin()==null){
                    FacesMessage fm = new FacesMessage("Indica fecha de finalizacion del evento.");
                    FacesContext.getCurrentInstance().addMessage("editEvento:fin", fm);
                }
            }else if(evento.getFechaInicio().after(evento.getFechaFin())){
                FacesMessage fm = new FacesMessage("Fecha de finalización anterior a la de inicio.");
                FacesContext.getCurrentInstance().addMessage("editEvento:fin",fm);
            }else if(evento.getFechaEntrada().after(evento.getFechaFin())){
                FacesMessage fm = new FacesMessage("Evento ya ha terminado.");
                FacesContext.getCurrentInstance().addMessage("editEvento:fin",fm);
            }else{
                negocio.modificarEvento(evento);
                return "main.xhtml";
            }
            return null;			
        }
    }
}
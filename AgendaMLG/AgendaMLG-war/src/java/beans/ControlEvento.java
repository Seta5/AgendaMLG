package beans;

import ejb.EventException;
import ejb.NegocioLocal;
import entity.Evento;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    @Inject
    private Sesion sesion;
    
    
    public ControlEvento(){
        evento = new Evento();
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
    public String enviar(){
        evento.setFechaEntrada(new Date());
        evento.setDestacado(false);
        evento.setPermanente(!temporal);
        evento.setValidado(!sesion.isLimitado());
        
        if(evento.isPermanente()){
            evento.setFechaInicio(null);
            evento.setFechaFin(null);
            try {
                negocio.registrarEvento(evento);
            } catch (EventException ex) {
            }
            return "main.xhtml";
        }else{
            if((evento.getFechaInicio()==null)||(evento.getFechaFin()==null)){
                if(evento.getFechaInicio()==null){
                    FacesMessage fm = new FacesMessage("Indica fecha de inicio del evento.");
                    FacesContext.getCurrentInstance().addMessage("evento:inicio", fm);					
                }
                if(evento.getFechaFin()==null){
                    FacesMessage fm = new FacesMessage("Indica fecha de finalizacion del evento.");
                    FacesContext.getCurrentInstance().addMessage("evento:fin", fm);
                }
            }else if(evento.getFechaInicio().after(evento.getFechaFin())){
                FacesMessage fm = new FacesMessage("Fecha de finalización anterior a la de inicio.");
                FacesContext.getCurrentInstance().addMessage("evento:fin",fm);
            }else if(evento.getFechaEntrada().after(evento.getFechaFin())){
                FacesMessage fm = new FacesMessage("Evento ya ha terminado.");
                FacesContext.getCurrentInstance().addMessage("evento:fin",fm);
            }else{
                try {
                    negocio.registrarEvento(evento);
                } catch (EventException ex) {
                }
                return "main.xhtml";
            }
            return null;			
        }
    }
    public List<Evento> listEvent(){
        return negocio.listaEventos();
    }
    public String borrarEvento(Evento evento){
        try{
            negocio.borrarEvento(evento);
        }catch(EventException e){
            FacesMessage fm = new FacesMessage("Evento no encontrado en la base de datos");
            FacesContext.getCurrentInstance().addMessage("lista:nodelete", fm);
        }
        
        return "main.xhtml";
    }
    
}

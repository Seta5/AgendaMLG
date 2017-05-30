package beans;

import ejb.EventException;
import ejb.NegocioLocal;
import entity.Evento;
import static entity.Usuario.Rol.*;
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
        if(!temporal){
            return "main.xhtml";            
        }
        return null;
    }
    public String enviar(){
        if (!evento.getFechaInicio().after(evento.getFechaFin())){
            try{
                varEvento();
                evento.setPermanente(!temporal);
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
    public List<Evento> listEvent(){
        List<Evento> list = null;
        try{
            list = negocio.listaEventos();
        }catch(EventException e){
            FacesMessage fm = new FacesMessage("Lista de eventos vacía");
            FacesContext.getCurrentInstance().addMessage("lista:nolist", fm);
        }
        return list;
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
    
    public void varEvento(){
        evento.setFechaEntrada(new Date());
        evento.setDestacado(false);
        if(sesion.getUsuario().getRol()!=LIMITADO){
            evento.setValidado(true);
        }else{
            evento.setValidado(false);
        }
    }
    
}

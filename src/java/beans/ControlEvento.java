package beans;

import entities.Evento;
import java.io.Serializable;
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
}

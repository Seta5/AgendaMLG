package beans;

import entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

@Named(value = "sesion")
@SessionScoped
public class Sesion implements Serializable {
    private Usuario usuario;
    
    public Sesion() {
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public synchronized String logout(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "main.xhtml";
    }
}

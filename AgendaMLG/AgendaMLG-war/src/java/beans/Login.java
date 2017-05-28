package beans;

import ejb.CuentaException;
import entity.Usuario;
import ejb.NegocioLocal;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "login")
@RequestScoped
public class Login{
    private Usuario usuario;
    
    @Inject
    private NegocioLocal negocio;
    @Inject
    private Sesion sesion;
    
    public Login() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String entrar(){
        try{
            negocio.comprobarUsuario(usuario);
            sesion.setUsuario(usuario);
        }catch(CuentaException e){
            FacesMessage fm = new FacesMessage("Usuario o contraseña incorrecto.");
            FacesContext.getCurrentInstance().addMessage("formInicio:user", fm);
            return null;
        }
        return "main.xhtml";
    }
}

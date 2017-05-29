package beans;

import ejb.CuentaException;
import ejb.NegocioLocal;
import entity.Usuario;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Registro {
    private String password2;
    private String email2;
    private Usuario usuario;
    
    @Inject
    private NegocioLocal negocio;
    
    public Registro(){
        usuario = new Usuario();
        usuario.setRol(Usuario.Rol.LIMITADO);
    }
    
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String registrarse(){
        if(!usuario.getPassword().equals(password2)){
            FacesMessage fm = new FacesMessage("Las contraseñas no coinciden.");
            FacesContext.getCurrentInstance().addMessage("formRegistro:pass2", fm);
        }else if(!usuario.getEmail().equals(email2)){
            FacesMessage fm = new FacesMessage("Los email no coinciden.");
            FacesContext.getCurrentInstance().addMessage("formRegistro:email2", fm);
        }else{
            try{
                negocio.registrarse(usuario);
                return "login.xhtml";
            }catch(CuentaException e){
                FacesMessage fm = new FacesMessage("Nombre de usuario en uso.");
                FacesContext.getCurrentInstance().addMessage("formRegistro:user", fm);
            }
        }
        return null;
    }
}

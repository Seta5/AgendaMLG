package view;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import entities.Usuario;

@Named(value = "login")
@RequestScoped
public class Login {
    private Usuario usuario;
    
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
        return " login.xhtml ";
    }
    
}

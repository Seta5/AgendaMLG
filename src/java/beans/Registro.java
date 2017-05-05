package beans;

import entities.Usuario;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "registro")
@RequestScoped
public class Registro {
    private String cuenta;
    private String password;
    private String password2;
    private String email;
    private String email2;
    
    @Inject
    private Sesion sesion;
    
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }
    
    public String registrarse(){
        Usuario personilla = null;
        if(password.equals(password2)){
            if(email.equals(email2)){
                personilla = new Usuario();
                personilla.setCuenta(cuenta);
                personilla.setEmail(email);
                personilla.setPassword(password);
            }else{
                
            }
        }else{
            
        }
        sesion.setUsuario(personilla);
        return sesion.iniciarSesion();
    }
}

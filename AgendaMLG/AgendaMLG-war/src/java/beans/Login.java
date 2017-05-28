package beans;

import entity.Usuario;
import ejb.NegocioLocal;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "login")
@RequestScoped
public class Login{
    private String cuenta;
    private String password;
    
    @Inject
    private NegocioLocal negocio;
    @Inject
    private Sesion sesion;
    
    public Login() {
    }

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

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }
    
//    public String entrar(){
//        Iterator<Usuario> iterator = usuarios.iterator();
//        boolean find = false;
//        Usuario personilla = null;
//        while(iterator.hasNext()&&!find){
//            Usuario user = iterator.next();
//            if(cuenta.equals(user.getCuenta())){
//                find = true;
//                if(password.equals(user.getPassword())){
//                    personilla = user;
//                }
//            }
//        }
//        sesion.setUsuario(personilla);
//        return sesion.iniciarSesion();
//    }
    
    public String entrar(){
        negocio.comprobarUsuario(cuenta,password);
        return null;
    }
}

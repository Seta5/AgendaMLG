package beans;

import entities.Usuario;
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
    private List<Usuario> usuarios;
    
    @Inject
    private Sesion sesion;
    
    public Login() {
        System.out.println("logeando");
        usuarios = new ArrayList<>();
        Usuario user = new Usuario();
        user.setCuenta("saltamontes");
        user.setPassword("aguantetelegram");
        usuarios.add(user);
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
    
    public String entrar(){
        Iterator<Usuario> iterator = usuarios.iterator();
        boolean find = false;
        Usuario personilla = null;
        while(iterator.hasNext()&&!find){
            System.out.println("hei!");
            Usuario user = iterator.next();
            if(cuenta.equals(user.getCuenta())){
                find = true;
                if(password.equals(user.getPassword())){
                    personilla = user;
                }
            }
        }
        sesion.setUsuario(personilla);
        return sesion.iniciarSesion();
    }
}

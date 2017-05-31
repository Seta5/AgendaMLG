package beans;

import ejb.NegocioLocal;
import entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "sesion")
@SessionScoped
public class Sesion implements Serializable {
    private Usuario usuario;
    
    @Inject
    private NegocioLocal negocio;
    
    private Usuario modificado;
    private String pass;
    private boolean editando;
    
    public Sesion() {
        editando = false;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getModificado() {
        return modificado;
    }

    public void setModificado(Usuario modificado) {
        this.modificado = modificado;
    }

    public synchronized boolean isEditando() {
        return editando;
    }

    public synchronized void setEditando(boolean editando) {
        this.editando = editando;
    }

    public synchronized String getPass() {
        return pass;
    }

    public synchronized void setPass(String pass) {
        this.pass = pass;
    }
    
    public boolean isLimitado(){
        return(usuario.getRol() == Usuario.Rol.LIMITADO);        
    }

    public boolean isAutorizado(){
        return(usuario.getRol() == Usuario.Rol.AUTORIZADO);        
    }

    public boolean isAdministrador(){
        return(usuario.getRol() == Usuario.Rol.ADMINISTRADOR);        
    }

    public boolean isProfesional(){
        return(usuario.getRol() == Usuario.Rol.PROFESIONAL);        
    }
    
    public synchronized String comenzarEdicion(){
        editando = true;
        
        modificado = new Usuario();
        modificado.setCuenta(usuario.getCuenta());
        modificado.setDireccion(usuario.getDireccion());
        modificado.setDni(usuario.getDni());
        modificado.setEmail(usuario.getEmail());
        modificado.setFecha_nacimiento(usuario.getFecha_nacimiento());
        modificado.setNombre(usuario.getNombre());
        modificado.setOrganizacion(usuario.getOrganizacion());
        modificado.setPassword(usuario.getPassword());
        modificado.setTelefono(usuario.getTelefono());
        
        return null;
    }
    
    public synchronized String enviarEdicion(){
        if(!usuario.getPassword().equals(pass)){
            System.out.println(usuario.getPassword()+" y " + pass);
            FacesMessage fm = new FacesMessage("Contraseña incorrecta.");
            FacesContext.getCurrentInstance().addMessage("edicion:pass1", fm);
            return null;
        }
        
        usuario.setCuenta(modificado.getCuenta());
        usuario.setDireccion(modificado.getDireccion());
        usuario.setDni(modificado.getDni());
        usuario.setEmail(modificado.getEmail());
        usuario.setFecha_nacimiento(modificado.getFecha_nacimiento());
        usuario.setNombre(modificado.getNombre());
        usuario.setOrganizacion(modificado.getOrganizacion());
        usuario.setPassword(modificado.getPassword());
        usuario.setTelefono(modificado.getTelefono());
        editando = false;
        
        negocio.modificarUsuario(usuario);
        return "perfil.xhtml";
    }
    
    public synchronized String logout(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "main.xhtml";
    }
}

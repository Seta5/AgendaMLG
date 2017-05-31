package beans;

import ejb.CuentaException;
import ejb.NegocioLocal;
import entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
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
    private String newPass;
    private boolean editando;
    private boolean administrando;
    
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

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public boolean isAdministrando() {
        return administrando;
    }

    public void setAdministrando(boolean administrando) {
        this.administrando = administrando;
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
    
    public void copiaUsuario(Usuario user1, Usuario user2){
        user1.setCuenta(user2.getCuenta());
        user1.setDireccion(user2.getDireccion());
        user1.setDni(user2.getDni());
        user1.setEmail(user2.getEmail());
        user1.setFecha_nacimiento(user2.getFecha_nacimiento());
        user1.setNombre(user2.getNombre());
        user1.setOrganizacion(user2.getOrganizacion());
        user1.setTelefono(user2.getTelefono());
        user1.setRol(user2.getRol());
    }
    
    public synchronized String comenzarEdicion(Usuario user, boolean administrando){
        editando = true;
        this.administrando = administrando;
        
        modificado = new Usuario();
        copiaUsuario(modificado, user);
        modificado.setPassword(user.getPassword());
        
        return "perfil.xhtml";
    }
    
    public synchronized String comenzarEdicion(){
        return comenzarEdicion(usuario, false);
    }
    
    public synchronized String cancelarEdicion(){
            editando = false;
            if(administrando){
                administrando = false;
                return "listaUsuarios.xhtml";
            }
        return null;
    }
    
    public synchronized String enviarEdicion(){
        if(pass.isEmpty()||pass == null){
            FacesMessage fm = new FacesMessage("Debe introducir la contraseña.");
            FacesContext.getCurrentInstance().addMessage("edicion:pass1", fm);
            return null;
        }
        if(!usuario.getPassword().equals(pass)){
            FacesMessage fm = new FacesMessage("Contraseña incorrecta.");
            FacesContext.getCurrentInstance().addMessage("edicion:pass1", fm);
            return null;
        }
        editando = false;
        if(!newPass.isEmpty()) modificado.setPassword(newPass);
        
        if(administrando){
            try{
                negocio.modificarUsuario(modificado);
            }catch(CuentaException ex){}
            administrando = false;
            modificado = null;
            return "listaUsuarios.xhtml";
        }else{
            copiaUsuario(usuario,modificado);
            usuario.setPassword(modificado.getPassword());
            try {
                negocio.modificarUsuario(usuario);
            } catch (CuentaException ex) {}
            modificado = null;
            return "perfil.xhtml";
        }
    }
    
    public synchronized String logout(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        editando = false;
        administrando = false;
        return "main.xhtml";
    }
    
    public synchronized String cambiarRol(Usuario user, int rol){
        switch(rol){
            case 1: user.setRol(Usuario.Rol.PROFESIONAL);
            break;
            case 2: user.setRol(Usuario.Rol.AUTORIZADO);
            break;
            case 3: user.setRol(Usuario.Rol.LIMITADO);
            break;
        }
        try{
            negocio.modificarUsuario(user);
        }catch(CuentaException ex){}
        return null;
    }
    
    public List<Usuario> listaUsuarios(){
        if(isAdministrador()) return negocio.listaUsuarios();
        else return negocio.listaReducida();
    }
}

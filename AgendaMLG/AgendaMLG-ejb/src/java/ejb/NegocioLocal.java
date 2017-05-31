package ejb;

import entity.Evento;
import entity.Usuario;
import java.util.List;
import javax.ejb.Local;

@Local
public interface NegocioLocal {
    public void registrarse (Usuario usuario) throws CuentaException;
    public Usuario comprobarUsuario(Usuario usuario) throws CuentaException;
    public void modificarUsuario(Usuario usuario)throws CuentaException;
    public void borrarUsuario(Usuario usuario) throws CuentaException;
    public void registrarEvento(Evento evento) throws EventException;
    public void modificarEvento(Evento evento) throws EventException;
    public void borrarEvento(Evento evento) throws EventException;
    public List<Evento> listaEventos() throws EventException;
    public List<Evento> listaActividades() throws EventException;
    public List<Evento> listaNoVerificada() throws EventException;
    public List<Usuario> listaUsuarios() throws CuentaException;
}

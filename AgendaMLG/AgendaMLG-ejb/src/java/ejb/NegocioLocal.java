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
    public void registrarEvento(Evento evento);
    public void modificarEvento(Evento evento);
    public void borrarEvento(Evento evento) throws EventException;
    public List<Evento> listaEventos();
    public List<Evento> listaActividades();
    public List<Evento> listaNoVerificada();
    public List<Usuario> listaUsuarios();
    public List<Usuario> listaReducida();
}

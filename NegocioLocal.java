package ejb;

import entity.Evento;
import entity.Usuario;
import javax.ejb.Local;

@Local
public interface NegocioLocal {
    public void registrarse (Usuario usuario) throws CuentaException;
    public void comprobarUsuario(Usuario usuario) throws CuentaException;
    public void modificarUsuario(Usuario usuario) throws CuentaException;
    public void borrarUsuario(Usuario usuario) throws CuentaException;
    public void autorizarUsuario(Usuario usuario) throws CuentaException;
    public void registrarEvento(Evento evento) throws EventException;
    public void modificarEvento(Evento evento) throws EventException;
    public void borrarEvento(Evento evento) throws EventException;
    public void listaEventos() throws EventException;
}

package ejb;

import entity.Usuario;
import javax.ejb.Local;

@Local
public interface NegocioLocal {
    public void registrarse (Usuario usuario) throws CuentaException;
    public Usuario comprobarUsuario(Usuario usuario) throws CuentaException;
}

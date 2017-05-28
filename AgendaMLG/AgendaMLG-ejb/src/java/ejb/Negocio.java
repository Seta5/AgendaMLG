package ejb;

import entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Negocio implements NegocioLocal {

    @PersistenceContext(unitName = "AgendaMLG-ejbPU")
    private EntityManager em;
    
    @Override
    public void registrarse(Usuario usuario) throws CuentaException{
        Usuario user = em.find(Usuario.class, usuario.getCuenta());
        if(user != null){
            throw new CuentaException();
        }
        em.persist(usuario);
    }
    
    @Override
    public void comprobarUsuario(String cuenta, String password){
        Usuario user = em.find(Usuario.class, cuenta);
        if(user == null){
//            throw new CuentaException();
        }
    }
}

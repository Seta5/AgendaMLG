package ejb;

import entity.Evento;
import entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    public Usuario comprobarUsuario(Usuario usuario) throws CuentaException{
        Usuario registrado = em.find(Usuario.class, usuario.getCuenta());
        if(registrado == null || !registrado.getPassword().equals(usuario.getPassword())){
            throw new CuentaException();
        }
        return registrado;
    }
    @Override
    public void modificarUsuario(Usuario usuario) throws CuentaException{
        Usuario user = em.find(Usuario.class, usuario.getCuenta());
        if(user == null){
            throw new CuentaException();
        }
        em.merge(usuario);
    }

    @Override
    public void borrarUsuario(Usuario usuario) throws CuentaException {
        Usuario user = em.find(Usuario.class, usuario.getCuenta());
        if(user == null){
            throw new CuentaException();
        }
        em.remove(user);
    }


    @Override
    public void registrarEvento(Evento evento) throws EventException {
        em.persist(evento);
    }

    @Override
    public void modificarEvento(Evento evento) throws EventException {

        em.merge(evento);
    }

    @Override
    public void borrarEvento(Evento evento) throws EventException {
        Evento event = em.find(Evento.class, evento.getId());
        if(event == null){
            throw new EventException();            
        }
        em.remove(event);
    }

    @Override
    public List<Evento> listaEventos() throws EventException {
        Query query = em.createQuery("SELECT e FROM Evento e WHERE e.validado=true and e.permanente=false");        
        return query.getResultList();        
    }

    public List<Evento> listaActividades() throws EventException {
        Query query = em.createQuery("SELECT e FROM Evento e WHERE e.validado=true and e.permanente=true");        
        return query.getResultList();
    }
    
    @Override
    public List<Evento> listaNoVerificada() throws EventException {
        Query query = em.createQuery("SELECT e FROM Evento e WHERE e.validado=false");
        return query.getResultList();
    }

    @Override
    public List<Usuario> listaUsuarios() throws CuentaException {
        Query query = em.createQuery("SELECT u FROM Usuario u");        
        return query.getResultList();        
    }
}

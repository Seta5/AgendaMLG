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
    public void registrarEvento(Evento evento){
        em.persist(evento);
    }

    @Override
    public void modificarEvento(Evento evento){
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
    public List<Evento> listaEventos(){
        Query query = em.createQuery("SELECT e FROM Evento e WHERE e.validado=true and e.permanente=false ORDER BY e.fechaFin ASC");
        return query.getResultList();        
    }

    @Override
    public List<Evento> listaActividades(){
        Query query = em.createQuery("SELECT e FROM Evento e WHERE e.validado=true and e.permanente=true ORDER BY e.nombre ASC");        
        return query.getResultList();
    }

    @Override
    public List<Evento> listaNoVerificada(){
        Query query = em.createQuery("SELECT e FROM Evento e WHERE e.validado=false ORDER BY e.fechaEntrada ASC");
        return query.getResultList();
    }

    @Override
    public List<Usuario> listaUsuarios() {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.rol <> 0 ORDER BY u.rol ASC, u.cuenta ASC");        
        return query.getResultList();        
    }
    
    @Override
    public List<Usuario> listaReducida() {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.rol=3 or u.rol=2");
        return query.getResultList();
    }
}

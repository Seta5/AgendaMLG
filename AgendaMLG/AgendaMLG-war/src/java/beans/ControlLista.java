package beans;

import ejb.NegocioLocal;
import entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "controlLista")
@ViewScoped
public class ControlLista implements Serializable{

    @Inject
    private NegocioLocal negocio;
    
    public ControlLista() {
    }  
}

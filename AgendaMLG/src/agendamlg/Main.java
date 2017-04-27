package agendamlg;
import javax.persistence.*;

public class Main {


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaMLG");
        EntityManager em = emf.createEntityManager();
        
        em.close();
        emf.close();
    }
    
}

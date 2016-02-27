package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.MembreSociete;

@Stateless
public class MembreSocieteDao {
    public void creer( MembreSociete ms ) throws DAOException {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        	EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist( ms );
            em.flush();
            em.close();
            emf.close();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}

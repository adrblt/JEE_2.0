package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Societe;

@Stateless
public class SocieteDao {
	private static final String JPQL_SELECT_ALL = "SELECT s FROM Societe s";
	
    public List<Societe> findAll() throws DAOException {
    	List<Societe> societes;
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
    	EntityManager em = emf.createEntityManager();
    	TypedQuery<Societe> query = em.createQuery( JPQL_SELECT_ALL, Societe.class );
        try {
        	societes = (List<Societe>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return societes;
    }
    
    public void creer( Societe societe ) throws DAOException {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        	EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist( societe );
            em.flush();
            em.close();
            emf.close();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

}

package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Secteur;

@Stateless
public class SecteurDao {
	private static final String JPQL_SELECT_ALL = "SELECT s FROM Secteur s";
	
    public List<Secteur> findAll() throws DAOException {
    	List<Secteur> secteurs;
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
    	EntityManager em = emf.createEntityManager();
    	TypedQuery<Secteur> query = em.createQuery( JPQL_SELECT_ALL, Secteur.class );
        try {
        	secteurs = (List<Secteur>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return secteurs;
    }
}

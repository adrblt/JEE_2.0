package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Utilisateur;

@Stateless
public class UtilisateurDao {
    private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email=:email";
    private static final String PARAM_EMAIL           = "email";
    
    // Enregistrement d'un nouvel utilisateur
    public void creer( Utilisateur utilisateur ) throws DAOException {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        	EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist( utilisateur );
            em.flush();
            em.close();
            emf.close();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    // Recherche d'un utilisateur à partir de son adresse email
    public Utilisateur trouver( String email ) throws DAOException {
        Utilisateur utilisateur = null;
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
    	EntityManager em = emf.createEntityManager();
        Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
        requete.setParameter( PARAM_EMAIL, email );
        try {
            utilisateur = (Utilisateur) requete.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return utilisateur;
    }
}
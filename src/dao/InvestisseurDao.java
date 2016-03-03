package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Investisseur;
import entities.Utilisateur;

@Stateless
public class InvestisseurDao {
	private static final String JPQL_SELECT_NOT_VALIDED = "select u from Utilisateur u, Role r where u.idRole = r.idRole and r.vRole = 'Investisseur' and u.idUtilisateur NOT IN (select idUtilisateur from Investisseur)";
	private static final String JPQL_SELECT_IDUTIL = "SELECT i.idInvestisseur FROM Investisseur i where i.idUtilisateur=:idUtil";
    
	public int getIdInvestisseur(int idUtilisateur){
		int resultat=0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_IDUTIL);
		requete.setParameter( "idUtil", idUtilisateur );
        try {
        	resultat = (int) requete.getSingleResult();
        } catch ( NoResultException e ) {
            return 0;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return resultat;
	}
	
	public List<Utilisateur> findNotV() throws DAOException {
    	List<Utilisateur> Ninvestisseurs;
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
    	EntityManager em = emf.createEntityManager();
    	TypedQuery<Utilisateur> query = em.createQuery( JPQL_SELECT_NOT_VALIDED, Utilisateur.class );
        try {
        	Ninvestisseurs = (List<Utilisateur>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return Ninvestisseurs;
    }
	
    public void creer( Investisseur investisseur) throws DAOException {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        	EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist( investisseur );
            em.flush();
            em.close();
            emf.close();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

}

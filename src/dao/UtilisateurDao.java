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

import entities.Secteur;
import entities.Utilisateur;

@Stateless
public class UtilisateurDao {
	private static final String JPQL_SELECT_ALL = "SELECT u FROM Utilisateur u";
    private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email=:email";
    private static final String PARAM_EMAIL           = "email";
    private static final String JPQL_SELECT_INVEST = "SELECT u.nom, u.prenom, u.email, u.dateInscription, i.vCompte FROM Investisseur i, Utilisateur u WHERE u.idUtilisateur=i.idUtilisateur";
    private static final String JPQL_SELECT_MS = "SELECT u.nom, u.prenom, u.email, u.dateInscription, s.nom FROM MembreSociete ms, Utilisateur u, Societe s WHERE u.idUtilisateur=ms.idUtilisateur and ms.idSociete=s.idSociete";
    
    public List<Utilisateur> findAll() throws DAOException {
    	List<Utilisateur> utilisateurs;
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
    	EntityManager em = emf.createEntityManager();
    	TypedQuery<Utilisateur> query = em.createQuery( JPQL_SELECT_ALL, Utilisateur.class );
        try {
        	utilisateurs = (List<Utilisateur>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return utilisateurs;
    }
    
	public List<Map<String, String> > rechercheAdminI() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_INVEST);
		List<Object[]> resultList = requete.getResultList();
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		for (Object[] res : resultList) {
			Map<String, String> parametres = new HashMap<String, String>();
			parametres.put("nomUtilisateur", String.valueOf(res[0]));
			parametres.put("prenomUtilisateur", String.valueOf(res[1]));
			parametres.put("email", String.valueOf(res[2]));
			parametres.put("dateInscription", String.valueOf(res[3]));
			parametres.put("vCompte", String.valueOf(res[4]));
			resultat.add(parametres);
		}
		return resultat;
	}
	
	public List<Map<String, String> > rechercheAdminMS() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_MS);
		List<Object[]> resultList = requete.getResultList();
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		for (Object[] res : resultList) {
			Map<String, String> parametres = new HashMap<String, String>();
			parametres.put("nomUtilisateur", String.valueOf(res[0]));
			parametres.put("prenomUtilisateur", String.valueOf(res[1]));
			parametres.put("email", String.valueOf(res[2]));
			parametres.put("dateInscription", String.valueOf(res[3]));
			parametres.put("societe", String.valueOf(res[4]));
			resultat.add(parametres);
		}
		return resultat;
	}
    
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
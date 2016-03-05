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

import entities.Utilisateur;

@Stateless
public class ContratDao {
	private static final String JPQL_SELECT_CONTRATS_P = "SELECT so.nom, se.vSecteur, p.dateAchat, p.prixAchat, u.nom, u.prenom FROM Contrat c, Possession p, Investisseur i, Utilisateur u, Societe so, Secteur se WHERE se.idSecteur=so.idSecteur and so.idSociete=c.idSociete and c.idContrat=p.idContrat and p.idInvestisseur=i.idInvestisseur and i.idUtilisateur=u.idUtilisateur";
	private static final String JPQL_SELECT_CONTRATS_V = "SELECT so.nom, se.vSecteur, v.dateDepart, v.prixDepart, u.nom, u.prenom FROM Contrat c, Vente v, Investisseur i, Utilisateur u, Societe so, Secteur se WHERE se.idSecteur=so.idSecteur and so.idSociete=c.idSociete and c.idContrat=v.idContrat and v.idInvestisseur=i.idInvestisseur and i.idUtilisateur=u.idUtilisateur";
	private static final String JPQL_SELECT_CONTRATS_L = "SELECT so.nom, se.vSecteur, l.dateLog, l.prixLog, l.vLog, u.nom, u.prenom FROM Contrat c, Log l, Investisseur i, Utilisateur u, Societe so, Secteur se WHERE se.idSecteur=so.idSecteur and so.idSociete=c.idSociete and c.idContrat=l.idContrat and l.idInvestisseur=i.idInvestisseur and i.idUtilisateur=u.idUtilisateur";
	private static final String JPQL_SELECT_CONTRATS_LID = "SELECT so.nom, se.vSecteur, l.dateLog, l.prixLog, l.vLog FROM Contrat c, Log l, Investisseur i, Utilisateur u, Societe so, Secteur se WHERE se.idSecteur=so.idSecteur and so.idSociete=c.idSociete and c.idContrat=l.idContrat and l.idInvestisseur=i.idInvestisseur and i.idUtilisateur=u.idUtilisateur and u.email=:email ORDER BY l.dateLog DESC";
	private static final String JPQL_SELECT_CONTRATS_PID = "SELECT so.nom, se.vSecteur, p.dateAchat, p.prixAchat, c.idContrat FROM Contrat c, Possession p, Investisseur i, Utilisateur u, Societe so, Secteur se WHERE se.idSecteur=so.idSecteur and so.idSociete=c.idSociete and c.idContrat=p.idContrat and p.idInvestisseur=i.idInvestisseur and i.idUtilisateur=u.idUtilisateur and i.idUtilisateur=:idU";
	private static final String JPQL_SELECT_ENCHERES = "SELECT so.nom, se.vSecteur, v.dateDepart, v.prixDepart, e.prixFinal, e.dateFinal, e.nbEncheres, c.idContrat FROM Contrat c, Vente v, Investisseur i, Societe so, Secteur se, Enchere e WHERE se.idSecteur=so.idSecteur and so.idSociete=c.idSociete and c.idContrat=v.idContrat and v.idInvestisseur=i.idInvestisseur and e.idContrat=v.idContrat and v.idInvestisseur<>:idInvest ORDER BY v.dateDepart DESC";
	private static final String JPQL_SELECT_MESENCHERES = "SELECT so.nom, se.vSecteur, v.dateDepart, v.prixDepart, e.prixFinal, e.dateFinal, e.nbEncheres FROM Contrat c, Vente v, Investisseur i, Societe so, Secteur se, Enchere e WHERE se.idSecteur=so.idSecteur and so.idSociete=c.idSociete and c.idContrat=v.idContrat and v.idInvestisseur=i.idInvestisseur and e.idContrat=v.idContrat and v.idInvestisseur=:idInvest ORDER BY v.dateDepart DESC";
	private static final String JPQL_SELECT_ACHATSI = "SELECT so.nom, se.vSecteur, v.dateDepart, v.prixDepart, c.idContrat, v.idInvestisseur FROM Contrat c, Vente v, Investisseur i, Societe so, Secteur se WHERE se.idSecteur=so.idSecteur and so.idSociete=c.idSociete and c.idContrat=v.idContrat and v.idInvestisseur=i.idInvestisseur and v.idInvestisseur<>:idInvest and v.idContrat NOT IN(SELECT e.idContrat FROM Enchere e) ORDER BY v.dateDepart DESC";
	private static final String JPQL_SELECT_MESACHATSI = "SELECT so.nom, se.vSecteur, v.dateDepart, v.prixDepart FROM Contrat c, Vente v, Investisseur i, Societe so, Secteur se WHERE se.idSecteur=so.idSecteur and so.idSociete=c.idSociete and c.idContrat=v.idContrat and v.idInvestisseur=i.idInvestisseur and v.idInvestisseur=:idInvest and v.idContrat NOT IN(SELECT e.idContrat FROM Enchere e) ORDER BY v.dateDepart DESC";
	
	public List<Map<String, String> > rechercheAdminP() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_CONTRATS_P);
		List<Object[]> resultList = requete.getResultList();
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		for (Object[] res : resultList) {
			Map<String, String> parametres = new HashMap<String, String>();
			parametres.put("societe", String.valueOf(res[0]));
			parametres.put("secteur", String.valueOf(res[1]));
			parametres.put("dateAchat", String.valueOf(res[2]));
			parametres.put("prixAchat", String.valueOf(res[3]));
			parametres.put("nomUtilisateur", String.valueOf(res[4]));
			parametres.put("prenomUtilisateur", String.valueOf(res[5]));
			resultat.add(parametres);
		}
		return resultat;
	}
	
	public List<Map<String, String> > rechercheAdminV() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_CONTRATS_V);
		List<Object[]> resultList = requete.getResultList();
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		for (Object[] res : resultList) {
			Map<String, String> parametres = new HashMap<String, String>();
			parametres.put("societe", String.valueOf(res[0]));
			parametres.put("secteur", String.valueOf(res[1]));
			parametres.put("dateDepart", String.valueOf(res[2]));
			parametres.put("prixDepart", String.valueOf(res[3]));
			parametres.put("nomUtilisateur", String.valueOf(res[4]));
			parametres.put("prenomUtilisateur", String.valueOf(res[5]));
			resultat.add(parametres);
		}
		return resultat;
	}
	
	public List<Map<String, String> > rechercheAdminL() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_CONTRATS_L);
		List<Object[]> resultList = requete.getResultList();
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		for (Object[] res : resultList) {
			Map<String, String> parametres = new HashMap<String, String>();
			parametres.put("societe", String.valueOf(res[0]));
			parametres.put("secteur", String.valueOf(res[1]));
			parametres.put("dateLog", String.valueOf(res[2]));
			parametres.put("prixLog", String.valueOf(res[3]));
			parametres.put("vLog", String.valueOf(res[4]));
			parametres.put("nomUtilisateur", String.valueOf(res[5]));
			parametres.put("prenomUtilisateur", String.valueOf(res[6]));
			resultat.add(parametres);
		}
		return resultat;
	}
	
	public List<Map<String, String> > rechercheInvestL(String email) {
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_CONTRATS_LID);
		requete.setParameter( "email", email );
        try {
        	List<Object[]> resultList = requete.getResultList();
    		for (Object[] res : resultList) {
    			Map<String, String> parametres = new HashMap<String, String>();
    			parametres.put("societe", String.valueOf(res[0]));
    			parametres.put("secteur", String.valueOf(res[1]));
    			parametres.put("dateLog", String.valueOf(res[2]));
    			parametres.put("prixLog", String.valueOf(res[3]));
    			parametres.put("vLog", String.valueOf(res[4]));
    			resultat.add(parametres);
    		}
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }		
		return resultat;
	}
	
	public List<Map<String, String> > rechercheInvestP(int idU) {
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_CONTRATS_PID);
		requete.setParameter( "idU", idU );
        try {
        	List<Object[]> resultList = requete.getResultList();
    		for (Object[] res : resultList) {
    			Map<String, String> parametres = new HashMap<String, String>();
    			parametres.put("societe", String.valueOf(res[0]));
    			parametres.put("secteur", String.valueOf(res[1]));
    			parametres.put("dateAchat", String.valueOf(res[2]));
    			parametres.put("prixAchat", String.valueOf(res[3]));
    			parametres.put("idContrat", String.valueOf(res[4]));
    			resultat.add(parametres);
    		}
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }		
		return resultat;
	}
	
	public List<Map<String, String> > rechercheInvestE(int idInvest) {
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_ENCHERES);
		requete.setParameter( "idInvest", idInvest );
        try {
        	List<Object[]> resultList = requete.getResultList();
    		for (Object[] res : resultList) {
    			Map<String, String> parametres = new HashMap<String, String>();
    			parametres.put("societe", String.valueOf(res[0]));
    			parametres.put("secteur", String.valueOf(res[1]));
    			parametres.put("dateDepart", String.valueOf(res[2]));
    			parametres.put("prixDepart", String.valueOf(res[3]));
    			parametres.put("prixActuel", String.valueOf(res[4]));
    			parametres.put("dateLimite", String.valueOf(res[5]));
    			parametres.put("nbEncheres", String.valueOf(res[6]));
    			parametres.put("idContrat", String.valueOf(res[7]));
    			resultat.add(parametres);
    		}
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }		
		return resultat;
	}
	
	public List<Map<String, String> > rechercheInvestME(int idInvest) {
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_MESENCHERES);
		requete.setParameter( "idInvest", idInvest );
        try {
        	List<Object[]> resultList = requete.getResultList();
    		for (Object[] res : resultList) {
    			Map<String, String> parametres = new HashMap<String, String>();
    			parametres.put("societe", String.valueOf(res[0]));
    			parametres.put("secteur", String.valueOf(res[1]));
    			parametres.put("dateDepart", String.valueOf(res[2]));
    			parametres.put("prixDepart", String.valueOf(res[3]));
    			parametres.put("prixActuel", String.valueOf(res[4]));
    			parametres.put("dateLimite", String.valueOf(res[5]));
    			parametres.put("nbEncheres", String.valueOf(res[6]));
    			resultat.add(parametres);
    		}
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }		
		return resultat;
	}
	
	public List<Map<String, String> > rechercheInvestAI(int idInvest) {
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_ACHATSI);
		requete.setParameter( "idInvest", idInvest );
        try {
        	List<Object[]> resultList = requete.getResultList();
    		for (Object[] res : resultList) {
    			Map<String, String> parametres = new HashMap<String, String>();
    			parametres.put("societe", String.valueOf(res[0]));
    			parametres.put("secteur", String.valueOf(res[1]));
    			parametres.put("dateDepart", String.valueOf(res[2]));
    			parametres.put("prixDepart", String.valueOf(res[3]));
    			parametres.put("idContrat", String.valueOf(res[4]));
    			parametres.put("idInvestisseur", String.valueOf(res[5]));
    			resultat.add(parametres);
    		}
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }		
		return resultat;
	}
	
	public List<Map<String, String> > rechercheInvestMAI(int idInvest) {
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_MESACHATSI);
		requete.setParameter( "idInvest", idInvest );
        try {
        	List<Object[]> resultList = requete.getResultList();
    		for (Object[] res : resultList) {
    			Map<String, String> parametres = new HashMap<String, String>();
    			parametres.put("societe", String.valueOf(res[0]));
    			parametres.put("secteur", String.valueOf(res[1]));
    			parametres.put("dateDepart", String.valueOf(res[2]));
    			parametres.put("prixDepart", String.valueOf(res[3]));
    			resultat.add(parametres);
    		}
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }		
		return resultat;
	}
	
}

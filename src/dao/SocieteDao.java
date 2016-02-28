package dao;

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

import entities.Societe;
import entities.Utilisateur;

@Stateless
public class SocieteDao {
	private static final String JPQL_SELECT_ALL = "SELECT s FROM Societe s";
	private static final String JPQL_SELECT_PAR_IDUTILISATEUR = "SELECT s FROM Societe s, MembreSociete ms WHERE ms.idUtilisateur=:idUtilisateur and ms.idSociete=s.idSociete";
	private static final String JPQL_SELECT_GROUPBY_NBCONTRATS = "SELECT so.nom, se.vSecteur, COUNT(c.idContrat), so.description  FROM Societe so, Contrat c, Secteur se WHERE so.idSociete=c.idSociete and so.idSecteur=se.idSecteur GROUP BY so.nom";

	public List<Societe> findAll() throws DAOException {
		List<Societe> societes;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Societe> query = em.createQuery(JPQL_SELECT_ALL, Societe.class);
		try {
			societes = (List<Societe>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return societes;
	}

	public Societe findByIdUtilisateur(int idUtilisateur) {
		Societe societe = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_PAR_IDUTILISATEUR);
		requete.setParameter("idUtilisateur", idUtilisateur);
		try {
			societe = (Societe) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return societe;
	}

	public Map<String, Map<String, String> > rechercheAdmin() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_GROUPBY_NBCONTRATS);
		List<Object[]> resultList = requete.getResultList();
		Map<String, Map<String, String> > resultat = new HashMap<String, Map<String, String>>();
		for (Object[] res : resultList) {
			Map<String, String> parametres = new HashMap<String, String>();
			parametres.put("secteur", String.valueOf(res[1]));
			parametres.put("nbContrats", String.valueOf(res[2]));
			parametres.put("description", String.valueOf(res[3]));
			resultat.put(String.valueOf(res[0]), parametres);
		}
		return resultat;
	}

	public void creer(Societe societe) throws DAOException {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(societe);
			em.flush();
			em.close();
			emf.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}

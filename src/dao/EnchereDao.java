package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Enchere;
import entities.Investisseur;
import entities.Vente;

@Stateless
public class EnchereDao {
	private static final String JPQL_FIND = "SELECT e FROM Enchere e where e.idContrat=:idContrat";
	
	public void creer(Enchere enchere) throws DAOException {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(enchere);
			em.flush();
			em.close();
			emf.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void update(int idContrat, int idPossesseur, float offre){
		try {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_FIND);
		requete.setParameter("idContrat", idContrat);
		Enchere enchere = (Enchere) requete.getSingleResult();
		em.getTransaction().begin();
		enchere.setIdPossesseur(idPossesseur);
		enchere.setPrixFinal(offre);
		enchere.incNbEncheres();
		em.flush();
		em.close();
		emf.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}

package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Investisseur;
import entities.Possession;

@Stateless
public class PossessionDao {
	private static final String FIND = "SELECT p FROM Possession p WHERE p.idInvestisseur=:idInvest and p.idContrat=:idContrat";

	public void delete(int idInvest, int idContrat) throws DAOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(FIND);
		requete.setParameter("idInvest", idInvest);
		requete.setParameter("idContrat", idContrat);
		try {
			Possession possession = (Possession) requete.getSingleResult();
			em.getTransaction().begin();
			em.remove(possession);
            em.flush();
            em.close();
            emf.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void creer(Possession possession) throws DAOException {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(possession);
			em.flush();
			em.close();
			emf.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}

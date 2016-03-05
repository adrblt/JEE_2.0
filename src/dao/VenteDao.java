package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Possession;
import entities.Vente;

@Stateless
public class VenteDao {
	private static final String FIND = "SELECT v FROM Vente v WHERE v.idInvestisseur=:idInvest and v.idContrat=:idContrat";
	
	public void creer(Vente vente) throws DAOException {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(vente);
			em.flush();
			em.close();
			emf.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void delete(int idInvest, int idContrat) throws DAOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(FIND);
		requete.setParameter("idInvest", idInvest);
		requete.setParameter("idContrat", idContrat);
		try {
			Vente vente = (Vente) requete.getSingleResult();
			em.getTransaction().begin();
			em.remove(vente);
            em.flush();
            em.close();
            emf.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}

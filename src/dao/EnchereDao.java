package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Enchere;
import entities.Vente;

@Stateless
public class EnchereDao {
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
}

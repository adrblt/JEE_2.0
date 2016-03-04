package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Info;

@Stateless
public class InfoDao {
	
	private static final String JPQL_SELECT_INFOS = "SELECT i.dateInfo, i.vInfo, so.nom, se.vSecteur FROM Secteur se, Info i, Societe so WHERE se.idSecteur=so.idSecteur and i.idSociete=so.idSociete";
	
	public List<Map<String, String> > rechercheInfos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery(JPQL_SELECT_INFOS);
		List<Object[]> resultList = requete.getResultList();
		List<Map<String, String> > resultat = new ArrayList<Map<String, String> >();
		for (Object[] res : resultList) {
			Map<String, String> parametres = new HashMap<String, String>();
			parametres.put("dateInfo", String.valueOf(res[0]));
			parametres.put("info", String.valueOf(res[1]));
			parametres.put("societe", String.valueOf(res[2]));
			parametres.put("secteur", String.valueOf(res[3]));
			resultat.add(parametres);
		}
		return resultat;
	}
	
    public void creer( Info info ) throws DAOException {
        try {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        	EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist( info );
            em.flush();
            em.close();
            emf.close();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}

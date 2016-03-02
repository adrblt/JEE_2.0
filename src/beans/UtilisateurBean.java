package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.UtilisateurDao;
import entities.Utilisateur;

@ManagedBean
@ViewScoped
public class UtilisateurBean {
	private List<Utilisateur> utilisateurs;
	
	@EJB
    private UtilisateurDao utilisateurDao;
	
    @PostConstruct
    public void init() {
    	utilisateurs = utilisateurDao.findAll();
    }
    
    public List<Utilisateur> getUtilisateurs(){
    	return utilisateurs;
    }
}

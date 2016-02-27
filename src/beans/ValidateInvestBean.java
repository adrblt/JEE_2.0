package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.InvestisseurDao;
import entities.Investisseur;
import entities.MembreSociete;
import entities.Utilisateur;

@ManagedBean
@ViewScoped
public class ValidateInvestBean {

	List<Utilisateur> Ninvestisseurs;
	List<Integer> Vinvestisseurs;

	@EJB
	private InvestisseurDao investisseurDao;

	@PostConstruct
	public void init() {
		Ninvestisseurs = investisseurDao.findNotV();
	}
	
	public void validate(){
		for(Integer idUtil : Vinvestisseurs){
    		Investisseur investisseur = new Investisseur();
    		investisseur.setIdUtilisateur(idUtil);
    		investisseurDao.creer(investisseur);
		}
	}

	public List<Utilisateur> getNinvestisseurs() {
		return Ninvestisseurs;
	}

	public List<Integer> getVinvestisseurs() {
		return Vinvestisseurs;
	}
	
	public void setVinvestisseurs(List<Integer> list) {
		this.Vinvestisseurs=list;
	}
}

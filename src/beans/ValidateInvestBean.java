package beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	
	public void validate() throws IOException{
		if(!Vinvestisseurs.isEmpty()){
			int nbValidations = Vinvestisseurs.size();
			for(Integer idUtil : Vinvestisseurs){
	    		Investisseur investisseur = new Investisseur();
	    		investisseur.setIdUtilisateur(idUtil);
	    		investisseur.setvCompte(0);
	    		investisseurDao.creer(investisseur);
			}
			FacesMessage message = new FacesMessage( "Profil validé !" );
			if(nbValidations>1)
				message = new FacesMessage( "Profils validés !" );
			FacesContext.getCurrentInstance().addMessage( null, message );
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/JEE_2.0/adminFolder/accAdmin.xhtml");
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

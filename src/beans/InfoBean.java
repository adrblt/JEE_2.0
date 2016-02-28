package beans;

import java.io.IOException;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.InfoDao;
import dao.SocieteDao;
import entities.Info;
import entities.Societe;
import entities.Utilisateur;

@ManagedBean
@ViewScoped
public class InfoBean {
	
	private Info info;
	private Utilisateur utilisateur;
	
	@EJB
    private InfoDao infoDao;
	@EJB
    private SocieteDao societeDao;
	
	public InfoBean(){
		info = new Info();
	}
	
	public void publier() throws IOException{
		initialiserDatePublication();
		Societe societe = societeDao.findByIdUtilisateur(utilisateur.getIdUtilisateur());
		info.setIdSociete(societe.getIdSociete());
		infoDao.creer(info);
		FacesMessage message = new FacesMessage( "Succès de la publication !" );
		FacesContext.getCurrentInstance().addMessage( null, message );
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext().redirect("/JEE_2.0/msFolder/accMS.xhtml");
	}
	
    private void initialiserDatePublication() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        info.setDateInfo(date);
    }
    
    public Info getInfo(){
    	return info;
    }
    
    public Utilisateur getUtilisateur(){
    	return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur){
    	this.utilisateur = utilisateur;
    }
}

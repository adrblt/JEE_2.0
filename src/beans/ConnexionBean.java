package beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UtilisateurDao;
import entities.Utilisateur;

@ManagedBean
@SessionScoped
public class ConnexionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Utilisateur       utilisateur;
	
	private boolean loggedIn;

    @EJB
    private UtilisateurDao    utilisateurDao;

    public ConnexionBean() {
        utilisateur = new Utilisateur();
        loggedIn = false;
    }

    public void connecter() throws IOException {
    	Utilisateur tmpU = utilisateurDao.trouver( utilisateur.getEmail() );
    	FacesMessage message = new FacesMessage( "Email ou mot de passe incorrect !" );
    	if(tmpU != null){
			if(tmpU.getMdp().equals(utilisateur.getMdp())){
				utilisateur = tmpU;
				loggedIn = true;
				if(utilisateur.getIdRole()==1)
					FacesContext.getCurrentInstance().getExternalContext().redirect("/JEE_2.0/adminFolder/accAdmin.xhtml");
				else if(utilisateur.getIdRole()==2)
					FacesContext.getCurrentInstance().getExternalContext().redirect("/JEE_2.0/investFolder/accInvest.xhtml");
				else
					FacesContext.getCurrentInstance().getExternalContext().redirect("/JEE_2.0/msFolder/accMS.xhtml");
			}
    	} 		
        FacesContext.getCurrentInstance().addMessage( null, message );
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public boolean isLoggedIn(){
    	return loggedIn;
    }
}
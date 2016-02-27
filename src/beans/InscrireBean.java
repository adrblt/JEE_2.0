package beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.MembreSocieteDao;
import dao.SocieteDao;
import dao.UtilisateurDao;
import entities.MembreSociete;
import entities.Societe;
import entities.Utilisateur;

@ManagedBean
@SessionScoped
public class InscrireBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Utilisateur utilisateur;
    private int idSociete;
    private Societe societe;

    @EJB
    private UtilisateurDao    utilisateurDao;
    @EJB
    private SocieteDao    societeDao;
    @EJB
    private MembreSocieteDao    msDao;

    public InscrireBean() {
        utilisateur = new Utilisateur();
        idSociete = 0;
        societe = new Societe();
    }

    public void inscrire() {
        initialiserDateInscription();
        utilisateurDao.creer( utilisateur );
        if(utilisateur.getIdRole()==3){ // MEMBRE SOCIETE
        	if(idSociete>0){
        		MembreSociete ms = new MembreSociete();
        		ms.setIdUtilisateur(utilisateur.getIdUtilisateur());
        		ms.setIdSociete(idSociete);
        		msDao.creer(ms);
        	}
        	else{
        		societeDao.creer(societe);
        		MembreSociete ms = new MembreSociete();
        		ms.setIdUtilisateur(utilisateur.getIdUtilisateur());
        		ms.setIdSociete(societe.getIdSociete());
        		msDao.creer(ms);
        	}
        }
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public Societe getSociete(){
    	return societe;
    }
    
    public int getIdSociete(){
    	return idSociete;
    }
    
    public void setIdSociete(int id){
    	idSociete = id;
    }

    private void initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        utilisateur.setDateInscription( date );
    }
}
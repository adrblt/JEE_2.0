package beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.UtilisateurDao;
import entities.Utilisateur;

@ManagedBean
@ViewScoped
public class InscrireBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Utilisateur       utilisateur;

    @EJB
    private UtilisateurDao    utilisateurDao;

    public InscrireBean() {
        utilisateur = new Utilisateur();
    }

    public void inscrire() {
        initialiserDateInscription();
        utilisateurDao.creer( utilisateur );
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    private void initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        utilisateur.setDateInscription( date );
    }
}
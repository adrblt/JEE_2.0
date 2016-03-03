package beans;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.ContratDao;
import dao.InvestisseurDao;
import dao.LogDao;
import dao.PossessionDao;
import dao.VenteDao;
import entities.Log;
import entities.Utilisateur;
import entities.Vente;

@ManagedBean
@SessionScoped
public class AchatVenteBean {
	
	private Utilisateur utilisateur;
	private int idInvestisseur = 0;
	private String secteurL = " ";
	private String firmL = " ";
	private String secteurP = " ";
	private String firmP = " ";
	
	private List<List<String>> resultatL;
	private List<Map<String, String>> rechercheCL = new ArrayList<Map<String, String>>();
	
	private int idContrat=0;
	private String dateAchat;
	private float prixAchat;
	
	private float prix=0;
	private List<List<String>> resultatP;
	private List<Map<String, String>> recherchePossessions = new ArrayList<Map<String, String>>();
	
	@EJB
	private ContratDao contratDao;
	@EJB
	private InvestisseurDao investisseurDao;
	@EJB
    private LogDao logDao;
	@EJB
    private PossessionDao possessionDao;
	@EJB
    private VenteDao venteDao;
	
	public void rechercheIdInvest() {
		if(idInvestisseur==0)
			idInvestisseur = investisseurDao.getIdInvestisseur(utilisateur.getIdUtilisateur());
	}

	public void recherchePossessions(){
		resultatP = new ArrayList<List<String>>();
		if (recherchePossessions.isEmpty())
			recherchePossessions = contratDao.rechercheInvestP(utilisateur.getIdUtilisateur());
		for (Map<String, String> entry : recherchePossessions) {
			if ((entry.containsValue(secteurP) || secteurP.equals(" "))
					&& (entry.containsValue(firmP) || firmP.equals(" "))) {
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String valeurParam = entryParam.getValue();
					res.add(valeurParam);
				}
				resultatP.add(res);
			}
		}
	}
	
	public void rechercheContratL() {
		resultatL = new ArrayList<List<String>>();
		if (rechercheCL.isEmpty())
			rechercheCL = contratDao.rechercheInvestL(utilisateur.getEmail());
		for (Map<String, String> entry : rechercheCL) {
			if ((entry.containsValue(secteurL) || secteurL.equals(" "))
					&& (entry.containsValue(firmL) || firmL.equals(" "))) {
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String valeurParam = entryParam.getValue();
					res.add(valeurParam);
				}
				resultatL.add(res);
			}
		}
	}
	
	public void vendre() throws ParseException{
		Log log = new Log();
		log.setIdContrat(idContrat);
		log.setIdInvestisseur(idInvestisseur);
		log.setVLog((byte)1);
		log.setPrixLog(prixAchat);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Date parsedDate = dateFormat.parse(dateAchat);
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    log.setDateLog(timestamp);
	    // CREATION LOG
	    logDao.creer(log);
	    // SUPPRESSION POSSESSION
	    possessionDao.delete(idInvestisseur, idContrat);
	    // CREATION VENTE
	    Timestamp date = new Timestamp( System.currentTimeMillis() );
	    Vente vente = new Vente();
	    vente.setDateDepart(date);
	    vente.setIdContrat(idContrat);
	    vente.setIdInvestisseur(idInvestisseur);
	    vente.setPrixDepart(prix);
	    venteDao.creer(vente);
	    recherchePossessions = new ArrayList<Map<String, String>>();
	    rechercheCL = new ArrayList<Map<String, String>>();
	    rechercheContratL();
	    recherchePossessions();
	    idContrat=0;
	    prix=0;
	}
	
	public int getIdContrat() {
		return idContrat;
	}

	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}

	public float getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getSecteurL() {
		return secteurL;
	}

	public String getFirmL() {
		return firmL;
	}

	public void setSecteurL(String secteurL) {
		this.secteurL = secteurL;
	}

	public void setFirmL(String firmL) {
		this.firmL = firmL;
	}
	
	public String getSecteurP() {
		return secteurP;
	}

	public String getFirmP() {
		return firmP;
	}

	public void setSecteurP(String secteurP) {
		this.secteurP = secteurP;
	}

	public void setFirmP(String firmP) {
		this.firmP = firmP;
	}

	public List<List<String>> getResultatL() {
		return resultatL;
	}

	public List<List<String>> getResultatP() {
		return resultatP;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}

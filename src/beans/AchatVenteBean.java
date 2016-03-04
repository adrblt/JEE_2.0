package beans;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import dao.ContratDao;
import dao.EnchereDao;
import dao.InfoDao;
import dao.InvestisseurDao;
import dao.LogDao;
import dao.PossessionDao;
import dao.VenteDao;
import entities.Enchere;
import entities.Investisseur;
import entities.Log;
import entities.Utilisateur;
import entities.Vente;

@ManagedBean
@SessionScoped
public class AchatVenteBean {

	private Utilisateur utilisateur;
	private Investisseur investisseur;
	private String secteurL = " ";
	private String firmL = " ";
	private String secteurP = " ";
	private String firmP = " ";
	private String secteurI = " ";
	private String firmI = " ";
	private String secteurA = " ";
	private String firmA = " ";

	private List<List<String>> resultatL;
	private List<Map<String, String>> rechercheCL = new ArrayList<Map<String, String>>();
	
	private List<List<String>> resultatI;
	private List<Map<String, String>> rechercheI = new ArrayList<Map<String, String>>();

	private List<List<String>> resultatP;
	private List<Map<String, String>> recherchePossessions = new ArrayList<Map<String, String>>();
	
	private List<List<String>> resultatEncheres;
	private List<Map<String, String>> rechercheEncheres = new ArrayList<Map<String, String>>();
	
	private List<List<String>> resultatAchatI;
	private List<Map<String, String>> rechercheAchatI = new ArrayList<Map<String, String>>();

	private float prix = 0;
	private int idContrat = 0;
	private String dateAchat;
	private float prixAchat;
	private boolean estEnchere = false;
	
	private float offre = 0;
	private int idContratA = 0;

	@NotNull(message = "Veuillez saisir une date")
	@Pattern(regexp = "((20)[0-9]{2})-((0?[1-9])|1[012])-((0?[1-9])|(1[0-9])|(2[0-9])|(3[01]))", message = "Merci de saisir une date valide")
	private String dateEnchere;

	@NotNull(message = "Veuillez saisir une heure")
	@Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]", message = "Merci de saisir une heure valide")
	private String heureEnchere;



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
	@EJB
	private InfoDao infoDao;
	@EJB
	private EnchereDao enchereDao;

	public void rechercheInvest() {
		if (investisseur == null)
			investisseur = investisseurDao.getIdInvestisseur(utilisateur.getIdUtilisateur());
	}
	
	public void rechercheInfos() {
		resultatI = new ArrayList<List<String>>();
		if (rechercheI.isEmpty())
			rechercheI = infoDao.rechercheInfos();
		for (Map<String, String> entry : rechercheI) {
			if ((entry.containsValue(secteurI) || secteurI.equals(" "))
					&& (entry.containsValue(firmI) || firmI.equals(" "))) {
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String valeurParam = entryParam.getValue();
					res.add(valeurParam);
				}
				resultatI.add(res);
			}
		}
	}

	public void recherchePossessions() {
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
	
	public void rechercheEnchere(){
		resultatEncheres = new ArrayList<List<String>>();
		if (rechercheEncheres.isEmpty())
			rechercheEncheres = contratDao.rechercheInvestE();
		for (Map<String, String> entry : rechercheEncheres) {
			if ((entry.containsValue(secteurA) || secteurA.equals(" "))
					&& (entry.containsValue(firmA) || firmA.equals(" "))) {
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String valeurParam = entryParam.getValue();
					res.add(valeurParam);
				}
				resultatEncheres.add(res);
			}
		}
	}
	
	public void rechercheAchat(){
		rechercheEnchere();
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

	public void vendre() throws ParseException {
		Log log = new Log();
		log.setIdContrat(idContrat);
		log.setIdInvestisseur(investisseur.getIdInvestisseur());
		log.setVLog((byte) 1);
		log.setPrixLog(prixAchat);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parsedDate = dateFormat.parse(dateAchat);
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		log.setDateLog(timestamp);
		// CREATION LOG
		logDao.creer(log);
		// SUPPRESSION POSSESSION
		possessionDao.delete(investisseur.getIdInvestisseur(), idContrat);
		// CREATION VENTE
		Timestamp date = new Timestamp(System.currentTimeMillis());
		Vente vente = new Vente();
		vente.setDateDepart(date);
		vente.setIdContrat(idContrat);
		vente.setIdInvestisseur(investisseur.getIdInvestisseur());
		vente.setPrixDepart(prix);
		venteDao.creer(vente);
		if (estEnchere == true) {
			Enchere enchere = new Enchere();
			String full_date=dateEnchere+" "+heureEnchere;
			parsedDate = dateFormat.parse(full_date);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
			enchere.setDateFinal(timestamp);
			enchere.setIdContrat(idContrat);
			enchere.setIdPossesseur(investisseur.getIdInvestisseur());
			enchere.setNbEncheres(0);
			enchere.setPrixFinal(prix);
			enchereDao.creer(enchere);
			estEnchere = false;
		}
		recherchePossessions = new ArrayList<Map<String, String>>();
		rechercheCL = new ArrayList<Map<String, String>>();
		rechercheContratL();
		recherchePossessions();
		idContrat = 0;
		prix = 0;

	}
	
	public void acheter(){
		investisseurDao.updateCompte(investisseur.getIdInvestisseur(), 700);
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

	public String getSecteurA() {
		return secteurA;
	}

	public void setSecteurA(String secteurA) {
		this.secteurA = secteurA;
	}

	public String getFirmA() {
		return firmA;
	}

	public void setFirmA(String firmA) {
		this.firmA = firmA;
	}

	public String getFirmI() {
		return firmI;
	}

	public void setFirmI(String firmI) {
		this.firmI = firmI;
	}

	public String getSecteurI() {
		return secteurI;
	}

	public void setSecteurI(String secteurI) {
		this.secteurI = secteurI;
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

	public boolean getEstEnchere() {
		return estEnchere;
	}

	public void setEstEnchere(boolean estEnchere) {
		this.estEnchere = estEnchere;
	}

	public String getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(String dateE) {
		this.dateEnchere = dateE;
	}

	public String getHeureEnchere() {
		return heureEnchere;
	}

	public void setHeureEnchere(String heureEnchere) {
		this.heureEnchere = heureEnchere;
	}

	public float getOffre() {
		return offre;
	}

	public void setOffre(float offre) {
		this.offre = offre;
	}

	public int getIdContratA() {
		return idContratA;
	}

	public void setIdContratA(int idContratA) {
		this.idContratA = idContratA;
	}

	public List<List<String>> getResultatL() {
		return resultatL;
	}

	public List<List<String>> getResultatP() {
		return resultatP;
	}
	
	public List<List<String>> getResultatI() {
		return resultatI;
	}
	
	public List<List<String>> getResultatEncheres() {
		return resultatEncheres;
	}
	
	public List<List<String>> getResultatAchatI() {
		return resultatAchatI;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}

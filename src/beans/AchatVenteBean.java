package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.ContratDao;
import dao.InvestisseurDao;
import entities.Utilisateur;

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
	
	private int misEnVentes=0;
	private float prix=0;
	private List<List<String>> resultatP;
	private List<Map<String, String>> recherchePossessions = new ArrayList<Map<String, String>>();
	
	@EJB
	private ContratDao contratDao;
	@EJB
	private InvestisseurDao investisseurDao;
	
	public void rechercheIdInvest() {
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
	
	public void vendre(){
		
	}
	
	public int getMisEnVentes() {
		return misEnVentes;
	}

	public void setMisEnVentes(int misEnVentes) {
		this.misEnVentes = misEnVentes;
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

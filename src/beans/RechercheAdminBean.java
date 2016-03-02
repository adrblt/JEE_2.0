package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.ContratDao;
import dao.SocieteDao;
import dao.UtilisateurDao;

@ManagedBean
@SessionScoped
public class RechercheAdminBean {
	private int choix;

	private String secteur = " ";
	private String firm = " ";
	private String nomU = " ";
	private String prenomU = " ";
	private int nbContratsMin = 0;

	private List<Map<String, String>> rechercheE = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> rechercheCP = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> rechercheCV = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> rechercheCL = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> rechercheI = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> rechercheMS = new ArrayList<Map<String, String>>();

	private List<List<String>> resultatS;
	private List<List<String>> resultatP;
	private List<List<String>> resultatV;
	private List<List<String>> resultatL;
	private List<List<String>> resultatI;
	private List<List<String>> resultatMS;

	@EJB
	private SocieteDao societeDao;
	@EJB
	private ContratDao contratDao;
	@EJB
	private UtilisateurDao utilisateurDao;

	public void rechercher() {
		if (choix == 1)
			rechercheSociete();
		else if (choix == 2) {
			rechercheInvestisseur();
			rechercheMS();
		} else if (choix == 3) {
			rechercheContratP();
			rechercheContratV();
			rechercheContratL();
		}
	}

	public void rechercheSociete() {
		resultatS = new ArrayList<List<String>>();
		if (rechercheE.isEmpty())
			rechercheE = societeDao.rechercheAdmin();
		for (Map<String, String> entry : rechercheE) {
			if ((entry.containsValue(secteur) || secteur.equals(" "))
					&& (entry.containsValue(firm) || firm.equals(" "))) {
				boolean add = true;
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String nomParam = entryParam.getKey();
					String valeurParam = entryParam.getValue();
					if (nomParam.equals("nbContrats") && Integer.parseInt(valeurParam) < nbContratsMin) {
						add = false;
						break;
					}
					res.add(valeurParam);
				}
				if (add == true)
					resultatS.add(res);
			}
		}
	}

	public void rechercheInvestisseur() {
		resultatI = new ArrayList<List<String>>();
		if (rechercheI.isEmpty())
			rechercheI = utilisateurDao.rechercheAdminI();
		for (Map<String, String> entry : rechercheI) {
			boolean add = true;
			List<String> res = new ArrayList<String>();
			for (Map.Entry<String, String> entryParam : entry.entrySet()) {
				String nomParam = entryParam.getKey();
				String valeurParam = entryParam.getValue();
				if (!nomU.equals(" ") && nomParam.equals("nomUtilisateur") && !valeurParam.equals(nomU)) {
					add = false;
					break;
				}
				if (!prenomU.equals(" ") && nomParam.equals("prenomUtilisateur") && !valeurParam.equals(prenomU)) {
					add = false;
					break;
				}
				res.add(valeurParam);
			}
			if (add == true)
				resultatI.add(res);
		}
	}

	public void rechercheMS() {
		resultatMS = new ArrayList<List<String>>();
		if (rechercheMS.isEmpty())
			rechercheMS = utilisateurDao.rechercheAdminMS();
		for (Map<String, String> entry : rechercheMS) {
			if ((entry.containsValue(secteur) || secteur.equals(" "))
					&& (entry.containsValue(firm) || firm.equals(" "))) {
				boolean add = true;
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String nomParam = entryParam.getKey();
					String valeurParam = entryParam.getValue();
					if (!nomU.equals(" ") && nomParam.equals("nomUtilisateur") && !valeurParam.equals(nomU)) {
						add = false;
						break;
					}
					if (!prenomU.equals(" ") && nomParam.equals("prenomUtilisateur") && !valeurParam.equals(prenomU)) {
						add = false;
						break;
					}
					res.add(valeurParam);
				}
				if (add == true)
					resultatMS.add(res);
			}
		}
	}

	public void rechercheContratP() {
		resultatP = new ArrayList<List<String>>();
		if (rechercheCP.isEmpty())
			rechercheCP = contratDao.rechercheAdminP();
		for (Map<String, String> entry : rechercheCP) {
			if ((entry.containsValue(secteur) || secteur.equals(" "))
					&& (entry.containsValue(firm) || firm.equals(" "))) {
				boolean add = true;
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String nomParam = entryParam.getKey();
					String valeurParam = entryParam.getValue();
					if (!nomU.equals(" ") && nomParam.equals("nomUtilisateur") && !valeurParam.equals(nomU)) {
						add = false;
						break;
					}
					if (!prenomU.equals(" ") && nomParam.equals("prenomUtilisateur") && !valeurParam.equals(prenomU)) {
						add = false;
						break;
					}
					res.add(valeurParam);
				}
				if (add == true)
					resultatP.add(res);
			}
		}
	}

	public void rechercheContratV() {
		resultatV = new ArrayList<List<String>>();
		if (rechercheCV.isEmpty())
			rechercheCV = contratDao.rechercheAdminV();
		for (Map<String, String> entry : rechercheCV) {
			if ((entry.containsValue(secteur) || secteur.equals(" "))
					&& (entry.containsValue(firm) || firm.equals(" "))) {
				boolean add = true;
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String nomParam = entryParam.getKey();
					String valeurParam = entryParam.getValue();
					if (!nomU.equals(" ") && nomParam.equals("nomUtilisateur") && !valeurParam.equals(nomU)) {
						add = false;
						break;
					}
					if (!prenomU.equals(" ") && nomParam.equals("prenomUtilisateur") && !valeurParam.equals(prenomU)) {
						add = false;
						break;
					}
					res.add(valeurParam);
				}
				if (add == true)
					resultatV.add(res);
			}
		}
	}

	public void rechercheContratL() {
		resultatL = new ArrayList<List<String>>();
		if (rechercheCL.isEmpty())
			rechercheCL = contratDao.rechercheAdminL();
		for (Map<String, String> entry : rechercheCL) {
			if ((entry.containsValue(secteur) || secteur.equals(" "))
					&& (entry.containsValue(firm) || firm.equals(" "))) {
				boolean add = true;
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String nomParam = entryParam.getKey();
					String valeurParam = entryParam.getValue();
					if (!nomU.equals(" ") && nomParam.equals("nomUtilisateur") && !valeurParam.equals(nomU)) {
						add = false;
						break;
					}
					if (!prenomU.equals(" ") && nomParam.equals("prenomUtilisateur") && !valeurParam.equals(prenomU)) {
						add = false;
						break;
					}
					res.add(valeurParam);
				}
				if (add == true)
					resultatL.add(res);
			}
		}
	}

	public int getChoix() {
		return choix;
	}

	public void setChoix(int choix) {
		this.choix = choix;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getNomU() {
		return nomU;
	}

	public void setNomU(String nomU) {
		this.nomU = nomU;
	}

	public String getPrenomU() {
		return prenomU;
	}

	public void setPrenomU(String prenomU) {
		this.prenomU = prenomU;
	}

	public int getNbContratsMin() {
		return nbContratsMin;
	}

	public void setNbContratsMin(int nbContratsMin) {
		this.nbContratsMin = nbContratsMin;
	}

	public List<List<String>> getResultatS() {
		return resultatS;
	}

	public List<List<String>> getResultatP() {
		return resultatP;
	}

	public List<List<String>> getResultatV() {
		return resultatV;
	}

	public List<List<String>> getResultatL() {
		return resultatL;
	}

	public List<List<String>> getResultatI() {
		return resultatI;
	}

	public List<List<String>> getResultatMS() {
		return resultatMS;
	}
}

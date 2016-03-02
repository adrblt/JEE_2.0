package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.ContratDao;

@ManagedBean
@SessionScoped
public class AchatVenteBean {
	
	private String email;
	private String secteur = " ";
	private String firm = " ";
	
	private List<List<String>> resultatL;
	private List<Map<String, String>> rechercheCL = new ArrayList<Map<String, String>>();
	
	@EJB
	private ContratDao contratDao;

	public void rechercheContratL() {
		resultatL = new ArrayList<List<String>>();
		if (rechercheCL.isEmpty())
			rechercheCL = contratDao.rechercheInvestL(email);
		for (Map<String, String> entry : rechercheCL) {
			if ((entry.containsValue(secteur) || secteur.equals(" "))
					&& (entry.containsValue(firm) || firm.equals(" "))) {
				List<String> res = new ArrayList<String>();
				for (Map.Entry<String, String> entryParam : entry.entrySet()) {
					String valeurParam = entryParam.getValue();
					res.add(valeurParam);
				}
				resultatL.add(res);
			}
		}
	}
	
	public String getSecteur() {
		return secteur;
	}

	public String getFirm() {
		return firm;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public List<List<String>> getResultatL() {
		return resultatL;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

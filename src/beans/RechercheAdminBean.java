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

import dao.SocieteDao;

@ManagedBean
@SessionScoped
public class RechercheAdminBean {
	private int choix;
	
	private String secteur = " ";
	private int nbContratsMin;
	
	private Map<String, Map<String, String> > recherche;
	
	private List<String> resultat;
	
	@EJB
    private SocieteDao    societeDao;
	
    @PostConstruct
    public void init() {
    	resultat = new ArrayList<String>();
    	recherche = societeDao.rechercheAdmin();
    	for (Map.Entry<String, Map<String, String> > entry : recherche.entrySet()) {
			String nom = String.valueOf(entry.getKey());
			String res = nom;
			Map<String, String> parametres = entry.getValue();
			for (Map.Entry<String, String> entryParam : parametres.entrySet()) {
				String nomParam = String.valueOf(entryParam.getKey());
				String valeurParam = String.valueOf(entryParam.getValue());
				res += " - " + valeurParam;
			}
			resultat.add(res);
		}
    }
	
	public void rechercher() throws IOException{
		resultat = new ArrayList<String>();
		resultat.add(" ");
		recherche = societeDao.rechercheAdmin();
		for (Map.Entry<String, Map<String, String> > entry : recherche.entrySet()) {
			String nom = entry.getKey();
			if(entry.getValue().containsValue(secteur) || secteur.equals(" ")){
				String res = nom;
				Map<String, String> parametres = entry.getValue();
				for (Map.Entry<String, String> entryParam : parametres.entrySet()) {
					String nomParam = entryParam.getKey();
					String valeurParam = entryParam.getValue();
					res += " " + valeurParam;
				}
				resultat.add(res);
			}
		}
	}

	public int getChoix() {
		return choix;
	}
	public void setChoix(int choix) {
		this.choix = choix;
	}
	public Map<String, Map<String, String>> getRecherche() {
		return recherche;
	}
	public void setRecherche(Map<String, Map<String, String>> recherche) {
		this.recherche = recherche;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public int getNbContratsMin() {
		return nbContratsMin;
	}

	public void setNbContratsMin(int nbContratsMin) {
		this.nbContratsMin = nbContratsMin;
	}

	public List<String> getResultat() {
		return resultat;
	}

	public void setResultat(List<String> resultat) {
		this.resultat = resultat;
	}
}

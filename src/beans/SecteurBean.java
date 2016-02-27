package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.SecteurDao;
import entities.Secteur;

@ManagedBean
@ViewScoped
public class SecteurBean {
	private List<Secteur> secteurs;
	
	@EJB
    private SecteurDao    secteurDao;
    
    @PostConstruct
    public void init() {
    	 secteurs = secteurDao.findAll();
    }
    
    public List<Secteur> getSocietes(){
    	return secteurs;
    }
}

package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.SocieteDao;
import entities.Societe;

@ManagedBean
@ViewScoped
public class SocieteBean {
	private List<Societe> societes;
	
	@EJB
    private SocieteDao    societeDao;
    
    @PostConstruct
    public void init() {
    	 societes = societeDao.findAll();
    }
    
    public List<Societe> getSocietes(){
    	return societes;
    }
}

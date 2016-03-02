package validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dao.DAOException;
import dao.SocieteDao;

@ManagedBean
@RequestScoped
public class ExistenceNomValidator implements Validator {
	private static final String NOM_EXISTE_DEJA = "Ce nom est déjà utilisée";
	
	@EJB
	private SocieteDao societeDao;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String nom = (String) value;
		try {
			if (societeDao.trouver(nom) != null) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, NOM_EXISTE_DEJA, null));
			}
		} catch (DAOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(component.getClientId(facesContext), message);
		}
	}

}

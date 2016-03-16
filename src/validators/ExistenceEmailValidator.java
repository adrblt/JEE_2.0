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
import dao.UtilisateurDao;

@ManagedBean
@RequestScoped
public class ExistenceEmailValidator implements Validator {

	private static final String EMAIL_EXISTE_DEJA = "This email is already used";

	@EJB
	private UtilisateurDao utilisateurDao;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email = (String) value;
		try {
			if (utilisateurDao.trouver(email) != null) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, EMAIL_EXISTE_DEJA, null));
			}
		} catch (DAOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(component.getClientId(facesContext), message);
		}
	}
}
package validators;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dao.DAOException;

@ManagedBean
@RequestScoped
public class VenteValidator implements Validator {
	private static final String ERR_VENTE = "Veuillez saisir un prix > 0";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			float prix = (float) value;
			try {
				if (prix <= 0) {
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ERR_VENTE, null));
				}
			} catch (DAOException e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(component.getClientId(facesContext), message);
			}
		}
	}

}

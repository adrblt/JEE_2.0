package validators;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "achatValidator")
public class AchatValidator implements Validator {
	private static final String CHAMP_PRIX = "prixActuel";
	private static final String CHAMP_COMPTE = "compte";
	private static final String PRIX_INVALIDE = "Offre différente au prix annoncé";
	private static final String COMPTE_INVALIDE = "Ressources insuffisantes";
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		float prix = (float) component.getValueExpression(CHAMP_PRIX).getValue(context.getELContext());
		float compte = (float) component.getValueExpression(CHAMP_COMPTE).getValue(context.getELContext());
		Float offre = (Float) value;
		if (offre != null) {
			if (offre != prix)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, PRIX_INVALIDE, null));
			else if (compte - offre < 0)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, COMPTE_INVALIDE, null));
		}
	}
}

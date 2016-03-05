package validators;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "enchereValidator")
public class EncherirValidator implements Validator {
	private static final String CHAMP_DATE = "dateActuelle";
	private static final String CHAMP_PRIX = "prixActuel";
	private static final String CHAMP_COMPTE = "compte";
	private static final String DATE_INVALIDE = "Enchères terminées";
	private static final String PRIX_INVALIDE = "Offre inférieur au prix actuel";
	private static final String COMPTE_INVALIDE = "Ressources insuffisantes";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String date = (String) component.getValueExpression(CHAMP_DATE).getValue(context.getELContext());
		float prix = (float) component.getValueExpression(CHAMP_PRIX).getValue(context.getELContext());
		float compte = (float) component.getValueExpression(CHAMP_COMPTE).getValue(context.getELContext());
		Float offre = (Float) value;
		if (offre != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedDate;
			try {
				parsedDate = dateFormat.parse(date);
				Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if (now.after(timestamp))
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, DATE_INVALIDE, null));
				if (offre < prix)
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, PRIX_INVALIDE, null));
				else if (compte - offre < 0)
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, COMPTE_INVALIDE, null));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
}

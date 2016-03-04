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

@FacesValidator( value = "dateValidator" )
public class DateValidator implements Validator{

    private static final String CHAMP_DATE       = "composantDate";
    private static final String DATE_INVALIDE = "Date passée";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput composantDate = (UIInput) component.getAttributes().get( CHAMP_DATE );
        String date = (String) composantDate.getValue();
        String heure = (String) value;
        
        if ( heure != null ) {
        	String full_date=date+" "+heure;
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	    Date parsedDate;
			try {
				parsedDate = dateFormat.parse(full_date);
				Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
				Timestamp now = new Timestamp( System.currentTimeMillis() );
				if(now.after(timestamp))
					throw new ValidatorException(
		            		new FacesMessage( FacesMessage.SEVERITY_ERROR, DATE_INVALIDE, null ) );
			} catch (ParseException e) {
				e.printStackTrace();
			}           
        }
		
	}

}

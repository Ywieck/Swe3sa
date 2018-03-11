package validierer;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "bestandValidator")
public class bestandsValidator implements Validator{
	
	/**
	 * Um einen Artikel in den Warenkorb zu legen muss der Kunde 
	 * eine Anzahl angeben. 
	 * Sollte die Anzahl nicht >0 sein, wird ein fehler mit dem unten angegbenen String ausgegeben.
	 */

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String kknr = String.valueOf(value);
		int anzahl = Integer.parseInt(kknr);

		FacesMessage message;

		if (anzahl <= 0) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Sie müssen eine Anzahl angeben");
			throw new ValidatorException(message);
		}
		
	}

}

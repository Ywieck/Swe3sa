package Validierer;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "eigenerValidator")
public class zahlenValidator implements Validator {

	/**
	 * Der Hersteller eines Artikel hat nur Großbuchstaben im Namen
	 * 
	 */
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String gg = String.valueOf(value);
		ArrayList<Integer> kkintarray = new ArrayList<Integer>();

		for (int i = 0; i < gg.length(); i++) {
			kkintarray.add(gg.charAt(i) - 48);
		}
		if (gg.matches("[a-z]+")) {

			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Validation Error",
							value
									+ ":  //(Es sind nur Großbuchstaben zulässig (einschließlich Zahlen))"));

		}
	}

	public String getValidatorId() {
		return "eigenerValidator";
	}
}
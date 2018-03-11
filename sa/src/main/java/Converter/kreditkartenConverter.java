package Converter;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "kreditkartenValidator")
public class kreditkartenConverter implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String kknr = String.valueOf(value);
		ArrayList<Integer> kkintarray = new ArrayList<Integer>();

		FacesMessage message;

		for (int i = 0; i < kknr.length(); i++) {
			kkintarray.add(kknr.charAt(i) - 48);
		}
		
		
		/*
		 * Die Kreditkartennummer darf nur aus den Ziffern 0-9 bestehen!
		 */
		if (!kknr.matches("[0-9]+")) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ungültige Kreditkartennummer",
					"Die Kreditkartennummer darf nur Ziffern enthalten");
			throw new ValidatorException(message);
		
		/*
		 * Die Kreditkartennummer muss mindestens 12 Zeichen lang und maximal 16 Zeichen lang sein!
		 */
		} else if (kknr.length() < 12 || kknr.length() > 16) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ungültige Kreditkartennummer",
					"Die Kreditkartennummer ist zu kurz/zu lang");
			throw new ValidatorException(message);
		}

	}
	/*
	 * Überprüft, ob die Länge der Kreditkartennummer grade ist 
	 */
	@SuppressWarnings("unused")
	private boolean check(ArrayList<Integer> digits) {

		int sum = 0;
		int length = digits.size();

		for (int i = 0; i < length; i++) {

			Integer digit = digits.get(length - i - 1);

			if (i % 2 == 1) {
				digit *= 2;
			}
			sum += digit > 9 ? digit - 9 : digit;
		}
		return sum % 10 == 0;
	}
}
package validierer;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "kreditkartenConverter")
public class kreditkartenValidator implements Converter {
	
	/*
	 * Sollte ein Leerzeichen, ein Komma oder ein Bindestrich in der Kreditkartennummer enthalten sein,
	 * wird mit diese mit dem Kreditkartenvalidator ein eine einheitliche Nummer umgewandelt.
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}
		String s = value;
		if (value.contains(" ")) {
			s = value.replace(" ", "");
		}
		if (value.contains("-")) {
			s = value.replace("-", "");
		}
		return s;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return (String) value;
	}

}
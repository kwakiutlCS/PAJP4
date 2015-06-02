package projecto4.grupo1.albertoricardo.validators;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordvalidate")
public class PasswordValidator implements Validator {


	public PasswordValidator() {
	}



	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String password = (String) value;

		UIInput confirmComponent = (UIInput) component.getAttributes().get("confirmPassword");
		String confirm = (String) confirmComponent.getSubmittedValue();

		if (password == null || confirm == null) return;

		if (!password.equals(confirm)) {
			FacesMessage msg = new FacesMessage("Password", 
					"Passwords não coincidem.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

		if (password.length() < 4) {
			FacesMessage msg = new FacesMessage("Password", 
					"Password fraca: mínimo de 5 caracteres.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

		if (password.length() > 12) {
			FacesMessage msg = new FacesMessage("Password", 
					"Password muito comprida (máx. 12 caracteres).");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}



}

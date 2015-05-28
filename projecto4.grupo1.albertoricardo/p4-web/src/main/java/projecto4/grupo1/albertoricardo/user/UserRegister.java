package projecto4.grupo1.albertoricardo.user;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projecto4.grupo1.albertoricardo.UserEJBLocal;

@Named
@RequestScoped
public class UserRegister implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UserEJBLocal userejb;
	
	@Inject
	private LoginChoose lc;

	private String email;
	private String emailConfirm;
	private String password;
	private String passwordConfirm;
	private String name;
	private String result = "";
	

	public UserRegister() {
		super();
	}



	public String addNewUser() {
		String destiny = "login";
		if (passwordConfirm.equals(password) && emailConfirm.equals(email)) {
			try {
				userejb.registerUser(email, password, name);
				result = "Utilizador '"+emailConfirm+"' criado com sucesso!";
				lc.toggle();
				destiny="login.xhtml?faces-redirect=true";
			} catch(Exception e) {
				result = "'"+emailConfirm+"' já existe, escolhe um e-mail diferente.";
				destiny="";
			}
		} else if (passwordConfirm.equals(password) && !emailConfirm.equals(email)) {
			result = "E-mails não correspondem.";
			destiny="";
		} else if (!passwordConfirm.equals(password) && emailConfirm.equals(email)) {
			result = "Passwords não correspondem.";
			destiny="";
		} else {
			result = "E-mails & Password não correspondem.";
			destiny="";
		}

		return destiny;
	}



	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getEmailConfirm() {
		return emailConfirm;
	}



	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}



	public String getPasswordConfirm() {
		return passwordConfirm;
	}



	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}





}

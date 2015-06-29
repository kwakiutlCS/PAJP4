package projecto4.grupo1.albertoricardo.user;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.UserEJBLocal;
import projecto4.grupo1.albertoricardo.roles.Role;
import projecto4.grupo1.albertoricardo.security.PasswordEncryptor;

@Named
@SessionScoped
public class Login implements Serializable {

	/**
	 * 
	 */
	
	@Inject
	UserLogged user;
	@EJB
	UserEJBLocal ejb;
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(Login.class);
	private String email;
	private String password;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		String result = "";
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			log.trace("DADOS: \n"
					+ "email: "+email+"\n"
							+ "password: "+password);
			request.login(email, password);
			user.setUser(ejb.getUserEntity(email));
			for (Role r:user.getUser().getRoles()) System.out.println("Roles: "+r);
			if (user.getUser().getRoles().contains(Role.USER)) {
				result = "Authorized/entry.xhtml?faces-redirect=true";
			} else if (user.getUser().getRoles().contains(Role.ADMIN)) {
				result = "Admin/index.xhtml";
			} else result = "Admin/index.xhtml";
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "loginerror";
		}
		System.out.println(result);
		return result;
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			request.logout();
			return "";
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Logout falhado"));
		}
		return null;
	}
	
	
	

}

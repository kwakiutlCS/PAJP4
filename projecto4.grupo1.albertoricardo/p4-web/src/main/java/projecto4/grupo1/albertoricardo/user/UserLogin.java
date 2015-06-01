package projecto4.grupo1.albertoricardo.user;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import projecto4.grupo1.albertoricardo.UserEJBLocal;

@Named
@SessionScoped
public class UserLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UserEJBLocal userejb;
	
	@SuppressWarnings("unused")
	@Inject
	private LoginChoose lc;

	@Inject
	private UserLogged userlog;

	private int id;
	private String email;
	private String password;
	private String result = "";

	public String doLogin() {
		String destiny = "";
		if (userejb.verifyLogin(this.email, this.password)) {
			userlog.setUser(userejb.getUserEntity(email));
			setFacesContext();
			destiny="/Authorized/entry.xhtml?faces-redirect=true";
			result = "Login válido";
		} else { 
			result = "Login inválido!";
			destiny = "";
		}
		return destiny;
	}

	public void setFacesContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ext.getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute("logged", "yes");
		System.out.println(session.getAttribute("logged") == null);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}





}

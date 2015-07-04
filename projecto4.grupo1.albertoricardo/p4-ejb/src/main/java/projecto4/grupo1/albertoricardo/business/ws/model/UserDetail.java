package projecto4.grupo1.albertoricardo.business.ws.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
public class UserDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 123456789L;
	private int id;
	private String email;
	private String name;
	@XmlTransient
	private String password;
	private List<String> roles;
	
	public UserDetail() {
	}
	
	
	
	public UserDetail(String email, String name, int id) {
		super();
		this.email = email;
		this.name = name;
		this.id = id;
	}



	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	@Override
	public String toString() {
		return "Utilizador:\n"
				+ "E-mail: "+email+"\n"
						+ "Nome: "+name+"\n"
								+ "(ID: "+id+")";
	}


	@XmlTransient
	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	public boolean equals(Object other) {
		if (other instanceof UserDetail) {
			UserDetail u = (UserDetail) other;
			return u.getId() == id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}



	public List<String> getRoles() {
		return roles;
	}



	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}

package projecto4.grupo1.albertoricardo.ws;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UserDetail {
	private int id;
	private String email;
	private String name;
	
	public UserDetail() {
		// TODO Auto-generated constructor stub
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

}

package projecto4.grupo1.albertoricardo.ws;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ListUserEntities {
	
	private Collection<UserDetail> listOfUsers;
	
	public ListUserEntities() {
	}

	public Collection<UserDetail> getListUsers() {
		return listOfUsers;
	}

	public void setListUsers(Collection<UserDetail> listUsers) {
		this.listOfUsers = listUsers;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (UserDetail ue : listOfUsers) {
			sb.append("Nome: "+ue.getEmail()+" (ID="+ue.getId()+")\n");
		}
		return sb.toString();
	}
	
	

}

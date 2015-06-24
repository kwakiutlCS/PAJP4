package projecto4.grupo1.albertoricardo.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import projecto4.grupo1.albertoricardo.UserEntity;


@XmlRootElement
public class ListUserEntities {
	
	private List<UserEntity> listOfUsers;
	
	public ListUserEntities() {
	}

	public List<UserEntity> getListUsers() {
		return listOfUsers;
	}

	public void setListUsers(List<UserEntity> listUsers) {
		this.listOfUsers = listUsers;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (UserEntity ue:listOfUsers) {
			sb.append("Nome: "+ue.getEmail()+" (ID="+ue.getId()+")\n");
		}
		return sb.toString();
	}
	
	

}

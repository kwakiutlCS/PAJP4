package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ListUserEntities {
	
	private List<UserEntity> listOfUsers;
	
	public ListUserEntities() {
		// TODO Auto-generated constructor stub
		listOfUsers = new ArrayList<>();
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

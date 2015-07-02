package projecto4.grupo1.albertoricardo.logged;

import java.util.ArrayList;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import projecto4.grupo1.albertoricardo.ws.ListUserEntities;
import projecto4.grupo1.albertoricardo.ws.UserDetail;

@ApplicationScoped
public class LoggedIn {
	
	private  int count = 0;
	private  ListUserEntities users = new ListUserEntities();
	
	public LoggedIn() {
		users.setListUsers(new HashSet<UserDetail>());
	}

	public  int getCount() {
		return count;
	}

	public  void setCount(int count) {
		this.count = count;
	}

	public  void add(UserDetail ud) {
		users.getListUsers().add(ud);
		count++;
	}

	public  void remove(UserDetail ud) {
		if (count > 0) {
			users.getListUsers().remove(ud);
			count--;
		}
	}

	public  ListUserEntities getUsers() {
		return users;
	}

	public  void setUsers(ListUserEntities users) {
		this.users = users;
	}

}

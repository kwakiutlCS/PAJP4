package projecto4.grupo1.albertoricardo.hit.counter;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;

import projecto4.grupo1.albertoricardo.ws.ListUserEntities;
import projecto4.grupo1.albertoricardo.ws.UserDetail;

@ApplicationScoped
public class LoggedInUsers {
	static int count = 0;
	static ListUserEntities users = new ListUserEntities();

	public LoggedInUsers() {
		// TODO Auto-generated constructor stub
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		LoggedInUsers.count = count;
	}

	public static void add(UserDetail ud) {
		if (users.getListUsers() == null) users.setListUsers(new ArrayList<UserDetail>());
		users.getListUsers().add(ud);
		count++;
	}
	
	public static void remove(UserDetail ud) {
		if (count > 0) {
			users.getListUsers().remove(ud);
			count--;
		}
	}

	public static ListUserEntities getUsers() {
		return users;
	}

	public static void setUsers(ListUserEntities users) {
		LoggedInUsers.users = users;
	}
	
	

}

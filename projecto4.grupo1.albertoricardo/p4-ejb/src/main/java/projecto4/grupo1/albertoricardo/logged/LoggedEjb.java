package projecto4.grupo1.albertoricardo.logged;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import projecto4.grupo1.albertoricardo.UserEntity;
import projecto4.grupo1.albertoricardo.ws.ListUserEntities;
import projecto4.grupo1.albertoricardo.ws.UserDetail;

@Singleton
@Startup
public class LoggedEjb {
	private List<UserDetail> users = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		System.out.println(this);
		System.out.println(LoggedEjb.class.getClassLoader());
	}

	@Lock(LockType.WRITE)
	public void addUser(UserEntity userEntity) {
		Mapper mapper = new DozerBeanMapper();
		UserDetail user = mapper.map(userEntity, UserDetail.class);
		users.remove(user);
		users.add(user);
		System.out.println(users);
	}
	
	@Lock(LockType.WRITE)
	public void remove(UserEntity userEntity) {
		Mapper mapper = new DozerBeanMapper();
		UserDetail user = mapper.map(userEntity, UserDetail.class);
		users.remove(user);
	}
	
	@Lock(LockType.READ)
	public int getTotal() {
		System.out.println(users);
		return users.size();
	}
	
	@Lock(LockType.READ)
	public ListUserEntities getUsers() {
		System.out.println(users);
		ListUserEntities list = new ListUserEntities();
		list.setListUsers(users);
		return list;
	}
	
	@Lock(LockType.WRITE)
	public void setUsers(List<UserDetail> users) {
		this.users = users;
	}
}

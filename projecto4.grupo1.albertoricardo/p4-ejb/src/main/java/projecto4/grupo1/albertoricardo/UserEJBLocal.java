package projecto4.grupo1.albertoricardo;


import java.util.List;

import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.business.ws.model.UserDetail;

@Local
public interface UserEJBLocal {

	public abstract boolean verifyLogin(String email, String password);

	public abstract void registerUser(String username, String password, String name) throws Exception;

	int getUserID(String username);

	String getName(String username);
	
	UserEntity getUserEntity(String username);

	boolean changeUser(UserEntity user);

	boolean deleteUser(UserEntity user);

	List<UserDetail> getAllUsers();

	UserDetail findToDTO(Object id);

	boolean changePassword(UserDetail ud, String newpassword);
	
	boolean remove(int id);

	UserDetail getUD(UserEntity ue);

//	void logged(UserEntity ue, boolean loggedin);

}
package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import projecto4.grupo1.albertoricardo.security.PasswordEncryptor;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
public class UserEJB implements UserEJBLocal {
	@PersistenceContext(name="Playlist")
	EntityManager em;

	/**
	 * Default constructor. 
	 */
	public UserEJB() {

	}
	
	@Override
	public boolean verifyLogin(String email, String password) {
		boolean verified;
		Query q = em.createQuery("select u from UserEntity u where u.email like :e")
		.setParameter("e", email);
		try {
			UserEntity usr = (UserEntity) q.getSingleResult();
			PasswordEncryptor pe = new PasswordEncryptor();
			if (pe.check(password, usr.getPassword())) verified = true;
			else verified = false;
		} catch (NoResultException nre) {
			nre.printStackTrace();
			verified = false;
		}

		return verified;
	}
	
	@Override
	public void registerUser(String username, String password, String name) {
		PasswordEncryptor pe = new PasswordEncryptor();
		String ePassword = pe.encrypt(password);
		UserEntity u = new UserEntity(username, ePassword, name);
		em.persist(u);
	}

	@Override
	public int getUserID(String username) {
		int id = -1;
		Query q = em.createQuery("select u from UserEntity u where u.email like :e");
		q.setParameter("e", username);
		UserEntity u = (UserEntity) q.getSingleResult();
		id = u.getId();
		return id;
	}
	
	@Override
	public String getName(String username) {
		Query q = em.createQuery("select u from UserEntity u where u.email like :e");
		q.setParameter("e", username);
		UserEntity u = (UserEntity) q.getSingleResult();
		String name = u.getName();
		return name;
	}

}

package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projecto4.grupo1.albertoricardo.roles.Role;
import projecto4.grupo1.albertoricardo.security.PasswordEncryptor;
import projecto4.grupo1.albertoricardo.ws.UserDetail;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
public class UserEJB implements UserEJBLocal {
	@PersistenceContext(name="Playlist")
	EntityManager em;

	@EJB
	private UserCRUD crud;
	@EJB
	private MusicListEJBLocal mlistejb;
	
	private static Logger log = LoggerFactory.getLogger(UserEJB.class);
	
	public UserEJB() {

	}

	@Override
	public boolean verifyLogin(String email, String password) {
		boolean verified;
		Query q = em.createQuery("select u from UserEntity u where u.email like :e");
		q.setParameter("e", email);
		try {
			UserEntity usr = (UserEntity) q.getSingleResult();
			PasswordEncryptor pe = new PasswordEncryptor();
			if (pe.check(password, usr.getPassword())) verified = true;
			else verified = false;
		} catch (NoResultException nre) {
			verified = false;
			nre.printStackTrace();
			log.warn("catch exception : verifyLogin method", nre);
		}

		return verified;
	}

	@Override
	public void registerUser(String username, String password, String name) throws Exception {
		PasswordEncryptor pe = new PasswordEncryptor();
		String ePassword = pe.encrypt(password);
		UserEntity u = new UserEntity(username, ePassword, name);
		u.addRole(Role.USER);
		crud.create(u);
		try {
			em.flush();
		}
		catch(PersistenceException e) {
			throw new Exception("error");
		}
		log.info("Novo utilizador registado: "+username);
	}

	@Override
	public boolean changeUser(UserEntity user) {
		boolean success = false;
		try {
			crud.update(user);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;

	}

	@Override
	public boolean deleteUser(UserEntity user) {
		boolean success = false;
		try {
			if (!mlistejb.removerUserOwnership(user)) {
				crud.remove(user);
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public int getUserID(String username) {
		Query q = em.createQuery("select u.id from UserEntity u where u.email like :e");
		q.setParameter("e", username);
		int id = (Integer) q.getSingleResult();
		return id;
	}

	@Override
	public String getName(String username) {
		String name = null;
		try {
			Query q = em.createQuery("select u from UserEntity u where u.email like :e");
			q.setParameter("e", username);
			UserEntity u = (UserEntity) q.getSingleResult();
			name = u.getName();
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage("Login", 
					"Utilizador inexistente.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		return name;
	}

	@Override
	public UserEntity getUserEntity(String username) {
		UserEntity u = null;
		try {
			Query q = em.createQuery("select u from UserEntity u where u.email like :e");
			q.setParameter("e", username);
			u = (UserEntity) q.getSingleResult();
		} catch(NoResultException nre) {
			nre.printStackTrace();
		}
		return u;

	}
	
	@Override
	public List<UserDetail> getAllUsers() {
		TypedQuery<UserEntity> q = em.createQuery("select u from UserEntity u", UserEntity.class);
		
		List<UserDetail> listUserDetail = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		
		for (UserEntity ue : q.getResultList()) {
			listUserDetail.add(mapper.map(ue, UserDetail.class));
		}
		
		return listUserDetail;
	}
	
	@Override
	public UserDetail findToDTO(Object id) {
		Mapper mapper = new DozerBeanMapper();
		UserEntity ue = crud.find(id);
		if (ue != null) {
			UserDetail ud = mapper.map(ue, UserDetail.class);
			return ud;
		}
		return null;
	}
	
	@Override
	public boolean changePassword(UserDetail ud, String newpassword) {
		PasswordEncryptor pe = new PasswordEncryptor();
		String ePassword = pe.encrypt(newpassword);
		ud.setPassword(ePassword);
		List<String> dozermapping = new ArrayList<>();
		dozermapping.add("META-INF/playlistmapping.xml");
		Mapper mapper = new DozerBeanMapper();
		UserEntity ue = mapper.map(ud,UserEntity.class);
		try {
			crud.update(ue);
			return true;
		} catch (Exception e) {
			log.error("Erro ao alterar a password no Web Service");
			return false;
		}
	}

	@Override
	public boolean remove(int id) {
		UserEntity u = crud.find(id);
		if (u != null) return deleteUser(u);
		return false;
	}

}

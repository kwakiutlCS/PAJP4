package projecto4.grupo1.albertoricardo;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/users")
public class UserService {
	@Inject
	UserEJBLocal user;
	
	// test
	@GET
	@Path("{id: \\d+}")
	@Produces(MediaType.APPLICATION_XML)
	public UserEntity find(@PathParam("id") int id) {
		return user.find(id);
	}
	
	
	// EX 2
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ListUserEntities find() {
		ListUserEntities users = new ListUserEntities();
		List<UserEntity> list = user.getAllUsers();
		
		for (UserEntity u : list) {
			users.getListUsers().add(u);
		}
		
		return users;
	}
	
	
	// EX 4
	
	
	
	// Ex 14
	@DELETE
	@Path("{id: \\d+}")
	public void remove(@PathParam("id") int id) {
		user.remove(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public void create(projecto4.grupo1.albertoricardo.entities.UserEntity newUser) {
		user.registerUser(newUser.getName(), newUser.getEmail(), newUser.getPassword());
	}
	
}
package projecto4.grupo1.albertoricardo.app;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import projecto4.grupo1.albertoricardo.ListUserEntities;
import projecto4.grupo1.albertoricardo.UserEJBLocal;
import projecto4.grupo1.albertoricardo.UserEntity;


@Stateless
@Path("/users")
public class Users {

	@EJB
	UserEJBLocal userejb;

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response listUsers() {
		ListUserEntities lue = new ListUserEntities();
		lue.setListUsers(userejb.getAllUsers());
		return Response.ok(lue).build();
	}

	@GET
	@Path("/um")
	@Produces({MediaType.APPLICATION_JSON})
	public UserEntity getUm() {
		return userejb.getUserEntity("jesus@email.com");
	}


}

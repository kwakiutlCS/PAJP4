package projecto4.grupo1.albertoricardo.app;



import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.UserEJBLocal;
import projecto4.grupo1.albertoricardo.ws.ListUserEntities;
import projecto4.grupo1.albertoricardo.ws.UserDetail;


@Stateless
@Path("/users")
public class Users {
	
	private static Logger log = LoggerFactory.getLogger(Users.class);
	
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
	@Path("/total")
	@Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
	public Response nUsers() {
		int n = userejb.getAllUsers().size();
		String nUsers = "Número de utilizadores registados: " + n;
		log.info("nUsers chamado");
		return Response.ok(nUsers).build();
		
	}
	
	@GET
	@Path("/get/{id: \\d+}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response userDetails(@PathParam("id") int id) {
		UserDetail ud = userejb.findToDTO(id);
		if (ud != null) {
			return Response.ok(ud).build();
		} else return Response.status(Response.Status.NOT_FOUND).entity("Utilizador não encontrado com o id: "+id).type(MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Path("/{id}/changepassword/{newpassword}")
	@Produces({MediaType.TEXT_PLAIN})
	public Response changePassword(@PathParam("id") int id,
			@PathParam("newpassword") String password) {
		UserDetail ud = userejb.findToDTO(id);
		if (ud != null) {
			if (userejb.changePassword(ud, password)) return Response.status(Response.Status.OK).entity("Password do utilizador com o id "+id+" alterada com sucesso").type(MediaType.TEXT_PLAIN).build();
			else return Response.notModified().build();
		} else return Response.status(Response.Status.NOT_FOUND).entity("Utilizador com o id "+id+" nao existe").type(MediaType.TEXT_PLAIN).build();
	}


	@DELETE
	@Path("{id: \\d+}")
	public Response remove(@PathParam("id") int id) {
		if (userejb.remove(id))
			return Response.ok("Utilizador removido").build();
		return Response.status(Response.Status.NOT_FOUND).entity("Utilizador não existe").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response create(UserDetail newUser) {
		try {
			userejb.registerUser(newUser.getEmail(), newUser.getPassword(), newUser.getName());
			return Response.ok().build();
		}
		catch(Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		}	
	}
	
	
}

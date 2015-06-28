package projecto4.grupo1.albertoricardo.app;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import projecto4.grupo1.albertoricardo.UserEJBLocal;
import projecto4.grupo1.albertoricardo.logged.LoggedEjb;
import projecto4.grupo1.albertoricardo.ws.ListUserEntities;
import projecto4.grupo1.albertoricardo.ws.UserDetail;


@Stateless
@Path("/users")
public class Users {

	@EJB
	UserEJBLocal userejb;
	@EJB
	LoggedEjb loggedEjb;

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
		return Response.ok(nUsers).build();
		
	}
	
	@GET
	@Path("/logged/total")
	@Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
	public Response nLogged() {
		int n = loggedEjb.getTotal();
		String nUsers = "Número de utilizadores logados: " + n;
		return Response.ok(nUsers).build();
	}
	
	@Path("/logged")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response listLogged() {
		ListUserEntities lue = loggedEjb.getUsers();
		return Response.ok(lue).build();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response userDetails(@PathParam("id") int id) {
		UserDetail ud = userejb.findToDTO(id);
		if (ud != null) {
			return Response.ok(ud).build();
		} else return Response.status(Response.Status.NOT_FOUND).entity("Utilizador não encontrado com o id: "+id).type(MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Path("changepassword")
	@Produces({MediaType.TEXT_PLAIN})
	@Consumes("application/x-www-form-urlencoded")
	public Response changePassword(@FormParam("id") String id, @FormParam("password") String password) {
		UserDetail ud = userejb.findToDTO(Integer.parseInt(id));
		
		if (ud != null) {
			if (userejb.changePassword(ud, password)) return Response.status(Response.Status.OK).entity("Password do utilizador com o id "+id+" alterada com sucesso").type(MediaType.TEXT_PLAIN).build();
			else return Response.notModified().build();
		} else return Response.status(Response.Status.NOT_FOUND).entity("Utilizador com o id "+id+" nao existe").type(MediaType.TEXT_PLAIN).build();
	}


	@DELETE
	@Path("{id: \\d+}")
	public Response remove(@PathParam("id") int id) {
		System.out.println("14a");
		System.out.println(id);
		if (userejb.remove(id))
			return Response.ok("Utilizador removido").build();
		return Response.status(Response.Status.NOT_FOUND).entity("Utilizador não existe").build();
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response create(@FormParam("username") String email, @FormParam("name") String name, @FormParam("password") String password) {
		try {
			userejb.registerUser(email, password, name);
			return Response.ok().build();
		}
		catch(Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		}	
	}
}

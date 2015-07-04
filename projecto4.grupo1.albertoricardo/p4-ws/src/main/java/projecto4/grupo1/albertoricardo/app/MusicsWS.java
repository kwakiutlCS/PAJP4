package projecto4.grupo1.albertoricardo.app;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import projecto4.grupo1.albertoricardo.MusicListEJBLocal;
import projecto4.grupo1.albertoricardo.business.ws.model.ListMusicEntities;
import projecto4.grupo1.albertoricardo.business.ws.model.MusicDetail;

@Stateless
@Path("/musics")
public class MusicsWS {
	
	@EJB
	MusicListEJBLocal mejb;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response getAllMusics() {
		ListMusicEntities lme = mejb.getAllMusics();
		if (lme != null) {
			return Response.status(Response.Status.OK).entity(lme).build();
		} else return Response.status(Response.Status.NO_CONTENT).entity("Nenhuma musica encontrada.").type(MediaType.TEXT_PLAIN).build();
	}
	
	@GET
	@Path("/user/{id: [1-9][0-9]*}") // Números maiores que 0
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response getMusicsFromUser(@PathParam("id") int id) {
		ListMusicEntities lme = mejb.getAllMusicsFromUser(id);
		if (lme != null) {
			return Response.status(Response.Status.OK).entity(lme).build();
		} else return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma musica encontrada do utilizador com id "+id).type(MediaType.TEXT_PLAIN).build();
	}
	
	
	@GET
	@Path("/total")
	@Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
	public Response count() {
		String s = "Número total de músicas: "+ mejb.getAllMusics().getListOfMusics().size();
		return Response.ok(s).build();
	}
	
	
	@GET
	@Path("{id: \\d+}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response find(@PathParam("id") int id) {
		MusicDetail m = mejb.find(id);
		if (m == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.status(Response.Status.OK).entity(m).build();
	}
	
	
	@DELETE
	@Path("/user/{id: \\d+}")
	public Response delete(@PathParam("id") int id) {
		if (mejb.removerUserOwnership(id)) 
			return Response.ok().build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}

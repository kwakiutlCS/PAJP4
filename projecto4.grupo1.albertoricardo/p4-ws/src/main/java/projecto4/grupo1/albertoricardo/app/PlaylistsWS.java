package projecto4.grupo1.albertoricardo.app;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import projecto4.grupo1.albertoricardo.PlaylistEJBLocal;
import projecto4.grupo1.albertoricardo.ws.AllPlaylists;
import projecto4.grupo1.albertoricardo.ws.ListPlaylists;
import projecto4.grupo1.albertoricardo.ws.MusicDetail;


@Stateless
@Path("/playlists")
public class PlaylistsWS {

	@EJB
	PlaylistEJBLocal plejb;

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response teste() {
		ListPlaylists lp = plejb.getAllPlaylists();
		if (lp != null) {
			return Response.status(Response.Status.OK).entity(lp).build();
		} else return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma playlist encontrada").type(MediaType.TEXT_PLAIN).build();
	}

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response getPlaylist(@PathParam("id") int id) {
		AllPlaylists ap = plejb.findToDTO(id);
		if (ap != null) {
			return Response.ok(ap).build();
		} else return Response.status(Response.Status.NOT_FOUND).entity("Playlist nao encontrada").type(MediaType.TEXT_PLAIN).build();
	}
	
	@GET
	@Path("/user/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response getPlaylistsFromUser(@PathParam("id") int id ) {
		ListPlaylists lp = plejb.getPlaylistsFromUser(id);
		if (lp != null) {
			return Response.status(Response.Status.OK).entity(lp).build();
		} else return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma playlist encontrada para o utilizador com o id: "+id).type(MediaType.TEXT_PLAIN).build();
	}
	
	@POST
	@Path("/{id}/remove")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeAllMusics(@PathParam("id") int id) {
		AllPlaylists ap = plejb.findToDTO(id);
		ap.getListOfMusics().clear();
		if (plejb.updateFromDTO(ap)) {
			return Response.status(Response.Status.OK).entity("Músicas removidas com sucesso da playlist "+ap.getName()).type(MediaType.TEXT_PLAIN).build();
		} else return Response.notModified().build();
	}
	
	@POST
	@Path("/{id}/remove/{musicid}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeSpecificMusic(@PathParam("id") int id, @PathParam("musicid") int musicid) {
		AllPlaylists ap = plejb.findToDTO(id);
		List<MusicDetail> mdl = ap.getListOfMusics();
		MusicDetail music2removeDetail = null;
		for (MusicDetail md:mdl) {
			if (md.getId() == musicid) {
				music2removeDetail = md;
				break;
			}
		}
		if (music2removeDetail != null) {
			System.out.println(ap.getListOfMusics());
			ap.getListOfMusics().remove(music2removeDetail);
			System.out.println(ap.getListOfMusics());
			plejb.updateFromDTO(ap);
			return Response.status(Response.Status.OK).entity("Música "+music2removeDetail.getTitle()+" removida com sucesso da playlist "+ap.getName()).type(MediaType.TEXT_PLAIN).build();
		} else return Response.notModified().build();
	}

}

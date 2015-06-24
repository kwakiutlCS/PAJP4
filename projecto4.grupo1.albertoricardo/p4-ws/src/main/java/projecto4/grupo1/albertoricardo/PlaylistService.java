package projecto4.grupo1.albertoricardo;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import projecto4.grupo1.albertoricardo.entities.ListMusicEntities;

@Stateless
@Path("/playlists")
public class PlaylistService {
	@Inject
	PlaylistEJBLocal playlist;
	@Inject
	PlaylistCRUD crud;
	
	
	// EX 6
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public int count() {
		return playlist.getPlaylists().size();
	}
	
	
	// EX 8
	@GET
	@Path("/{id: \\d+}")
	@Produces(MediaType.APPLICATION_XML)
	public ListMusicEntities findAll(@PathParam("id") int id) {
		ListMusicEntities list = new ListMusicEntities();
		PlaylistEntity p = crud.find(id);
		
		for (MusicEntity m : p.getMusics()) {
			list.getList().add(m);
		}
		return list;
	}
	
}
package projecto4.grupo1.albertoricardo;



import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/musics")
public class MusicService {
	@Inject
	MusicListEJBLocal musicList;
	@Inject
	MusicEJBLocal music;
	
	// EX 10  
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public int count() {
		return musicList.listMusics().size();
	}
	
	// EX 12
	@GET
	@Path("{id: \\d+}")
	@Produces(MediaType.APPLICATION_XML)
	public MusicEntity find(@PathParam("id") int id) {
		return music.find(id);
	}
	
	
}
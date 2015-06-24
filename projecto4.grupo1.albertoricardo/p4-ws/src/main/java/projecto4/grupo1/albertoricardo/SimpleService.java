package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/ws")
public class SimpleService {
	@Inject
	UserEJBLocal user;
	
	@GET
    @Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    public String getMessage() {
        //TODO return proper representation object
        return "Simple!";
    }
	
	
	@GET
	@Path("/echo/{param}")
	@Produces(MediaType.TEXT_HTML) 
	public String json(@PathParam("param") String msg) {
		return msg;
	}
	
	
	
}
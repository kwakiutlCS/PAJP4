package projecto4.grupo1.albertoricardo.listener;

import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import projecto4.grupo1.albertoricardo.business.ws.model.UserDetail;
import projecto4.grupo1.albertoricardo.logged.LoggedIn;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
@WebListener
public class MyListener implements HttpSessionListener {
	@Inject
	private LoggedIn logged;
    /**
     * Default constructor. 
     */
    public MyListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	UserDetail ud = (UserDetail) se.getSession().getAttribute("user");
    	logged.remove(ud);
    }
	
}

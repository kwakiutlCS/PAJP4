package projecto4.grupo1.albertoricardo.logged;

import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;

import pt.uc.dei.aor.paj.proj4.group1.business.ws.model.ListUserEntities;
import pt.uc.dei.aor.paj.proj4.group1.business.ws.model.UserDetail;

@ApplicationScoped
public class LoggedIn {

    private ListUserEntities users = new ListUserEntities();

    public LoggedIn() {
        users.setListUsers(new HashSet<UserDetail>());
    }

    public int getCount() {
        return users.getListUsers().size();
    }

    public void add(UserDetail ud) {
        users.getListUsers().add(ud);
    }

    public void remove(UserDetail ud) {
        if (getCount() > 0)
            users.getListUsers().remove(ud);
    }

    public ListUserEntities getUsers() {
        return users;
    }

    public void setUsers(ListUserEntities users) {
        this.users = users;
    }

}

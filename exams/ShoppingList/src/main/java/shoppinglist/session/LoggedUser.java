package shoppinglist.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import shoppinglist.entity.User;

@Component
@SessionScope
public class LoggedUser {

    private long id;
    private String username;

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();

    }

    public void logout() {
        this.id = 0;
        this.username = null;

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}

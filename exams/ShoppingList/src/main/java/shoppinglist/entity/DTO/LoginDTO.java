package shoppinglist.entity.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginDTO {

    @NotEmpty
    @Size(min = 3, max = 10)
    private String username;

    @NotEmpty
    @Size(min = 3)
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
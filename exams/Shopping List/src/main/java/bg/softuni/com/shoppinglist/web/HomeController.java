package bg.softuni.com.shoppinglist.web;

import bg.softuni.com.shoppinglist.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private LoggedUser loggedUser;

    @GetMapping("home")
    public String home() {

        return "home";
    }


}

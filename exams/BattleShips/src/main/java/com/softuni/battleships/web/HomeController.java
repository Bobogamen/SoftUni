package com.softuni.battleships.web;

import com.softuni.battleships.session.LoggedUser;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private LoggedUser loggedUser;

    public HomeController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/home")
    public String loggedInIndex() {
        if (loggedUser.getId() > 0) {
            return "home";
        }

        return "redirect:/";
    }
}

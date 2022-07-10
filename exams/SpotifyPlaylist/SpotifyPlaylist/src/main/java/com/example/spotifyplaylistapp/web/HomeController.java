package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import com.example.spotifyplaylistapp.view.PlayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private LoggedUser loggedUser;
    private SongService songService;

    public HomeController(LoggedUser loggedUser, SongService songService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }

        model.addAttribute("songs", this.songService.getSongs());

        return "/home";
    }
}

package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    private LoggedUser loggedUser;
    private SongService songService;
    private UserService userService;

    public HomeController(LoggedUser loggedUser, SongService songService, UserService userService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }

        List<Song> userSongs = this.userService.getUser(loggedUser.getId()).getSongs();

        int totalDurationInSec = (int) userSongs.stream().mapToDouble(Song::getDurationInt).sum();

        String totalDuration = getTotalDuration(totalDurationInSec);


        System.out.println();

        model.addAttribute("songs", this.songService.getSongs());
        model.addAttribute("playlist", userSongs);
        model.addAttribute("totalDuration", totalDuration);

        return "/home";
    }

    private String getTotalDuration(int seconds) {
        int totalMinutes = seconds / 60;
        int totalSeconds = seconds - (totalMinutes * 60);

        if (totalSeconds > 9) {
            return totalMinutes + ":" + totalSeconds;
        }

        return totalMinutes + ":0" + totalSeconds;
    }
}

package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class SongController {

    private SongService songService;
    private LoggedUser loggedUser;
    private SongRepository songRepository;
    private UserRepository userRepository;

    public SongController(SongService songService, LoggedUser loggedUser, SongRepository songRepository, UserRepository userRepository, UserRepository userRepository1) {
        this.songService = songService;
        this.loggedUser = loggedUser;
        this.songRepository = songRepository;
        this.userRepository = userRepository1;
    }

    @ModelAttribute("addSongDTO")
    public AddSongDTO initAddSongDTO() {
        return new AddSongDTO();
    }

    @GetMapping("/add-song")
    public String addSong() {

        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }

        return "song-add";
    }

    @PostMapping("/add-song")
    public String addSong(@Valid AddSongDTO addSongDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors() || this.songService.songUniqueName(addSongDTO)) {
            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", bindingResult);

            return "redirect:/song-add";
        }

        songService.addSong(addSongDTO);

        return "redirect:/home";
    }
    @Transactional
    @GetMapping("/to-playlist/{id}")
    public String toPlaylist(@PathVariable UUID id) {

        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }

        songService.addSongToPlaylist(id);

        return "redirect:/home";
    }

    @Transactional
    @GetMapping("/remove-all")
    public String removeAll() {

        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }

        userRepository.findUserById(loggedUser.getId()).getSongs().clear();

        return "redirect:/home";
    }
}

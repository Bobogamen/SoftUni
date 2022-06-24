package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
import com.example.spotifyplaylistapp.view.SongListViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private SongRepository songRepository;
    private LoggedUser loggedUser;
    private UserRepository userRepository;
    private StyleRepository styleRepository;

    public SongService(SongRepository songRepository, LoggedUser loggedUser, UserRepository userRepository, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
    }

    public boolean songUniqueName(AddSongDTO addSongDTO) {

        Optional<Song> productOpt = this.songRepository.findSongByPerformer(addSongDTO.getName());

        return productOpt.isPresent();
    }

    public void addSong(AddSongDTO addSongDTO) {

        StyleEnum type = StyleEnum.POP;

        for (StyleEnum value : StyleEnum.values()) {
            if (addSongDTO.getStyle().equals(value.name())) {
                type = StyleEnum.valueOf(addSongDTO.getStyle());
                break;
            }
        }

        Style style = this.styleRepository.findByStyleName(type);
        User user = this.userRepository.findUserById(loggedUser.getId());


        Song song = new Song();

        song.setPerformer(addSongDTO.getName());
        song.setTitle(addSongDTO.getTitle());
        song.setReleaseDate(addSongDTO.getReleaseDate());
        song.setDuration(addSongDTO.getDuration());
        song.setUser(user);
        song.setStyle(style);

        this.songRepository.save(song);
    }

    public SongListViewModel getSongs() {
        return new SongListViewModel(this.songRepository.findAllSongs());
    }

}

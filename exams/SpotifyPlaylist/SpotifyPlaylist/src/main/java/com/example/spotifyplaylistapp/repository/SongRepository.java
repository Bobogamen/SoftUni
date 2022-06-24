package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.view.SongViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findSongByPerformer(String name);
    @Query("SELECT new com.example.spotifyplaylistapp.view.SongViewModel(s.id, s.performer, s.title, s.duration, st.styleName) " +
            "FROM Song AS s " +
            "JOIN Style AS st c")
    List<SongViewModel> findAllSongs();

}

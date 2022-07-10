package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findSongByPerformer(String name);
    List<Song> findAllByUser_Id(long id);

    Song findSongById(UUID id);
}

package com.example.spotifyplaylistapp.view;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;

import java.util.List;
import java.util.stream.Collectors;

public class SongListViewModel {
    private List<Song> pop;
    private List<Song> jazz;
    private List<Song> rock;
    private String totalDuration;

    public SongListViewModel(List<Song> songList) {
        this.pop = getList(songList, StyleEnum.POP);
        this.jazz = getList(songList, StyleEnum.JAZZ);
        this.rock = getList(songList, StyleEnum.ROCK);

        this.totalDuration = getTotalDuration(getDuration(this.pop) + getDuration(this.jazz) + getDuration(this.rock));
    }

    public List<Song> getPop() {
        return pop;
    }

    public void setPop(List<Song> pop) {
        this.pop = pop;
    }

    public List<Song> getJazz() {
        return jazz;
    }

    public void setJazz(List<Song> jazz) {
        this.jazz = jazz;
    }

    public List<Song> getRock() {
        return rock;
    }

    public void setRock(List<Song> rock) {
        this.rock = rock;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    private String getTotalDuration(int seconds) {
        int totalMinutes = seconds / 60;
        int totalSeconds = seconds - (totalMinutes * 60);

        if (totalSeconds > 9) {
            return totalMinutes + ":" + totalSeconds;
        }

        return totalMinutes + ":0" + totalSeconds;
    }

    private int getDuration(List<Song> songs) {
        return (int) songs.stream().mapToDouble(Song::getDurationInt).sum();
    }

    private List<Song> getList(List<Song> songItems, StyleEnum style) {


        return songItems.stream().filter(s -> s.getStyleEnum().equals(style)).collect(Collectors.toList());
    }
}

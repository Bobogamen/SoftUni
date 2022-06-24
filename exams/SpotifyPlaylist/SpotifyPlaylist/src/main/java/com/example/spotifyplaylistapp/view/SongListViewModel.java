package com.example.spotifyplaylistapp.view;

import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;

import java.util.List;
import java.util.stream.Collectors;

public class SongListViewModel {
    private List<SongViewModel> pop;
    private List<SongViewModel> jazz;
    private List<SongViewModel> rock;
    private float totalDuration;

    public SongListViewModel(List<SongViewModel> songItems) {
        this.pop = getList(songItems, StyleEnum.POP);
        this.jazz = getList(songItems, StyleEnum.JAZZ);;
        this.rock = getList(songItems, StyleEnum.ROCK);;
        this.totalDuration = songItems.stream().map(SongViewModel::getDuration)
                .reduce((total, dur) -> total += dur).orElse(null);
    }

    private List<SongViewModel> getList(List<SongViewModel> songItems, StyleEnum style) {
        return songItems.stream().filter(s -> s.getStyleName().equals(style)).collect(Collectors.toList());
    }

    public List<SongViewModel> getPop() {
        return pop;
    }

    public void setPop(List<SongViewModel> pop) {
        this.pop = pop;
    }

    public List<SongViewModel> getJazz() {
        return jazz;
    }

    public void setJazz(List<SongViewModel> jazz) {
        this.jazz = jazz;
    }

    public List<SongViewModel> getRock() {
        return rock;
    }

    public void setRock(List<SongViewModel> rock) {
        this.rock = rock;
    }

    public float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(float totalDuration) {
        this.totalDuration = totalDuration;
    }
}

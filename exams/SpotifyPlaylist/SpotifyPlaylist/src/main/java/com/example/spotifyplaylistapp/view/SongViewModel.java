package com.example.spotifyplaylistapp.view;

import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;

public class SongViewModel {

    private long id;
    private String performer;

    private String title;

    private float duration;

    private StyleEnum styleName;

    public SongViewModel(long id, String performer, String title, float duration, StyleEnum styleName) {
        this.id = id;
        this.performer = performer;
        this.title = title;
        this.duration = duration;
        this.styleName = styleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public StyleEnum getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleEnum styleName) {
        this.styleName = styleName;
    }
}

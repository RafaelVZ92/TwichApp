package com.example.twichapp.twitchgames;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamesViewModel {

    private String id;
    private String name;
    private String boxArtUrl;

    public GamesViewModel(String id, String name, String boxArtUrl) {
        this.id = id;
        this.name = name;
        this.boxArtUrl = boxArtUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoxArtUrl() {
        return boxArtUrl;
    }

    public void setBoxArtUrl(String boxArtUrl) {
        this.boxArtUrl = boxArtUrl;
    }
}

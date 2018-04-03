package com.simpleplayer.railianmaksym.simpleplayer.repository;

import java.util.List;

/**
 * Created by raili on 03.04.2018.
 */

public class Playlist {
    int id;
    String name;
    List<Audio> playlist;

    public Playlist(int id, String name, List<Audio> playlist) {
        this.id = id;
        this.name = name;
        this.playlist = playlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Audio> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Audio> playlist) {
        this.playlist = playlist;
    }
}

package com.simpleplayer.railianmaksym.simpleplayer.repository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Playlist extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Audio> playlist;

    public Playlist(int id, String name, RealmList<Audio> playlist) {
        this.id = id;
        this.name = name;
        this.playlist = playlist;
    }

    public Playlist() {
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

    public void setPlaylist(RealmList<Audio> playlist) {
        this.playlist = playlist;
    }
}

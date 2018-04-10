package com.simpleplayer.railianmaksym.simpleplayer.managers;


import com.simpleplayer.railianmaksym.simpleplayer.interfaces.iDatabase;
import com.simpleplayer.railianmaksym.simpleplayer.repository.Audio;
import com.simpleplayer.railianmaksym.simpleplayer.repository.Playlist;

import java.util.List;

import io.realm.Realm;

/**
 * Created by raili on 04.04.2018.
 */

public class RealmDatabaseManager implements iDatabase {

    private Realm realm;


    public RealmDatabaseManager(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void addSongs(List<Audio> audios) {
    realm.beginTransaction();
    for (Audio audio:audios){
        realm.insertOrUpdate(audio);
    }
    realm.commitTransaction();
    }

    @Override
    public void addPlaylist(Playlist playlist) {
        realm.beginTransaction();
        realm.insertOrUpdate(playlist);
        realm.commitTransaction();
    }

    @Override
    public List<Playlist> getPlaylists() {
        realm.beginTransaction();
        List<Playlist>playlists=realm.where(Playlist.class).findAll();
        realm.commitTransaction();
        return playlists;
    }

    @Override
    public Playlist getPlaylist(int id) {
        realm.beginTransaction();
        Playlist playlist = realm.where(Playlist.class).equalTo("id",id).findFirst();
        realm.commitTransaction();
        return playlist;
    }

    @Override
    public List<Audio> getAudios() {
        realm.beginTransaction();
        List<Audio> audios =realm.where(Audio.class).findAll();
        realm.commitTransaction();
        return audios;
    }

    @Override
    public Audio getAudio(int id) {
        realm.beginTransaction();
        Audio audio = realm.where(Audio.class).equalTo("id",id).findFirst();
        realm.commitTransaction();
        return audio;
    }

    @Override
    public void deletePlaylist(int id) {
        realm.beginTransaction();
        Playlist playlist = realm.where(Playlist.class).equalTo("id",id).findFirst();
        playlist.deleteFromRealm();
        realm.commitTransaction();
    }
}

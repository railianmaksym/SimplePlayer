package com.simpleplayer.railianmaksym.simpleplayer.interfaces;


import com.simpleplayer.railianmaksym.simpleplayer.repository.Audio;
import com.simpleplayer.railianmaksym.simpleplayer.repository.Playlist;

import java.util.List;

public interface iDatabase {

    void addSongs(List<Audio> audios);
    void addPlaylist(Playlist playlist);
    List<Playlist> getPlaylists();
    Playlist getPlaylist(int id);
    List<Audio> getAudios();
    Audio getAudio(int id);
    void deletePlaylist(int id);
}

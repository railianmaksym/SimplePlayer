package com.simpleplayer.railianmaksym.simpleplayer.di;

import com.simpleplayer.railianmaksym.simpleplayer.managers.AudioManager;
import com.simpleplayer.railianmaksym.simpleplayer.services.MediaPlayerService;
import com.simpleplayer.railianmaksym.simpleplayer.ui.MainActivity;
import com.simpleplayer.railianmaksym.simpleplayer.ui.fragments.MusicFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by raili on 04.04.2018.
 */
@Singleton
@Component(modules = AudioModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject (MediaPlayerService mediaPlayerService);
    void inject (AudioManager audioManager);
    void inject(MusicFragment musicFragment);
}

package com.simpleplayer.railianmaksym.simpleplayer.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import com.simpleplayer.railianmaksym.simpleplayer.managers.AudioManager;
import com.simpleplayer.railianmaksym.simpleplayer.managers.RealmDatabaseManager;
import com.simpleplayer.railianmaksym.simpleplayer.managers.SharedPreferencesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;


@Module
public class AudioModule {
    private Application application;

    public AudioModule(Application application) {

        this.application = application;
    }

    @Provides
    @Singleton
    MediaPlayer providesMediaPlayer(){
        return new MediaPlayer();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    SharedPreferencesManager providesSharedPreferencesManager(Application application){
        return new SharedPreferencesManager(application.getApplicationContext());
    }

    @Provides
    @Singleton
    AudioManager providesAudioManager(){
        return new AudioManager(application.getApplicationContext());
    }

    @Provides
    @Singleton
    Realm providesRealm(){
        Realm.init(application.getApplicationContext());
        RealmConfiguration configuration=new RealmConfiguration.Builder().name("audio.realm").build();
        Realm.setDefaultConfiguration(configuration);
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    RealmDatabaseManager providesRealmDatabaseManager(Realm realm){
        return new RealmDatabaseManager(realm);
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }
}

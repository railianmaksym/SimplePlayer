package com.simpleplayer.railianmaksym.simpleplayer.ui;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.simpleplayer.railianmaksym.simpleplayer.MyApplication;
import com.simpleplayer.railianmaksym.simpleplayer.R;
import com.simpleplayer.railianmaksym.simpleplayer.managers.AudioManager;
import com.simpleplayer.railianmaksym.simpleplayer.repository.Audio;
import com.simpleplayer.railianmaksym.simpleplayer.services.MediaPlayerService;
import com.simpleplayer.railianmaksym.simpleplayer.ui.fragments.MusicFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.playButton)
    ImageButton playButton;
    @BindView(R.id.nextButton)
    ImageButton nextButton;
    @BindView(R.id.prevButton)
    ImageButton prevButton;

    private FrameLayout container;
    boolean serviceBound = false;
    @Inject
    AudioManager audioManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_music:
                    goToFragment(container, new MusicFragment());
                    return true;

                case R.id.navigation_playlists:
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MyApplication) getApplicationContext()).getAppComponent().inject(this);
        container =findViewById(R.id.fragments_container);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    public  void goToFragment(View Conteiner, android.support.v4.app.Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(Conteiner.getId(),fragment);
        ft.commit();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("ServiceState", serviceBound);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        serviceBound = savedInstanceState.getBoolean("ServiceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audioManager.unBoundService();
    }

    @OnClick(R.id.nextButton)
    void playNextTrack(){
        audioManager.playNextTrack();
    }

    @OnClick(R.id.prevButton)
    void playPrevTrack(){
        audioManager.playPrevTrack();
    }

    @OnClick(R.id.playButton)
    void pauseTrack(){
        audioManager.pauseTrack();
    }

}

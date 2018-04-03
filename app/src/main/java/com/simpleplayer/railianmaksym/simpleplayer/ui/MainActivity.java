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
import android.widget.Toast;

import com.simpleplayer.railianmaksym.simpleplayer.R;
import com.simpleplayer.railianmaksym.simpleplayer.managers.AudioManager;
import com.simpleplayer.railianmaksym.simpleplayer.repository.Audio;
import com.simpleplayer.railianmaksym.simpleplayer.services.MediaPlayerService;
import com.simpleplayer.railianmaksym.simpleplayer.ui.fragments.MusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private FrameLayout container;
    boolean serviceBound = false;
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

        container =findViewById(R.id.fragments_container);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        audioManager=new AudioManager(this);
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

}

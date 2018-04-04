package com.simpleplayer.railianmaksym.simpleplayer.managers;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.widget.Toast;

import com.simpleplayer.railianmaksym.simpleplayer.MyApplication;
import com.simpleplayer.railianmaksym.simpleplayer.R;
import com.simpleplayer.railianmaksym.simpleplayer.repository.Audio;
import com.simpleplayer.railianmaksym.simpleplayer.services.MediaPlayerService;
import com.simpleplayer.railianmaksym.simpleplayer.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class AudioManager {
    private Context context;
    private List<Audio> audioList = new ArrayList<>();
    private MediaPlayerService player;
    @Inject
    MediaPlayer mediaPlayer;
    private boolean serviceBound = false;
    private int playedTrackID;
    private int audioListSize;
    int resumePosition =0;
    @Inject
    SharedPreferencesManager sharedPreferencesManager;
    @Inject
    RealmDatabaseManager realmDatabaseManager;

    public AudioManager(Context context) {
        this.context = context;
        ((MyApplication)context.getApplicationContext()).getAppComponent().inject(this);
    }

    public void loadAudio() {
        if (sharedPreferencesManager.getBoolean(context.getResources().getString(R.string.tracksLoaded))){
            audioList=realmDatabaseManager.getAudios();
        }else {
            ContentResolver contentResolver = context.getContentResolver();

            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
            String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
            Cursor cursor = contentResolver.query(uri, null, selection, null, sortOrder);

            if (cursor != null && cursor.getCount() > 0) {
                audioList = new ArrayList<>();
                int i=0;
                while (cursor.moveToNext()) {
                    int id =i;
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

                    // Save to audioList
                    audioList.add(new Audio(id,data, title, album, artist));
                    realmDatabaseManager.addSongs(audioList);
                    sharedPreferencesManager.putBoolean(context.getString(R.string.tracksLoaded), true);
                    i+=1;
                }
            }
            cursor.close();
        }
    }
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            player = binder.getService();
            serviceBound = true;

            Toast.makeText(context, "Service Bound", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };

    public void playAudioFromDevice(int id) {
        //Check is service is active
        if (!serviceBound) {
            Intent playerIntent = new Intent(context, MediaPlayerService.class);
            playerIntent.putExtra("media", audioList.get(id).getData());
            context.startService(playerIntent);
            context.bindService(playerIntent,serviceConnection , Context.BIND_AUTO_CREATE);
            playedTrackID = id+1;
        } else {
            //Service is active
            //Send media with BroadcastReceiver
        }
    }

    public void unBoundService(){
        if (serviceBound) {
            context.unbindService(serviceConnection);
            //service is active
            player.stopSelf();
        }
    }

    public List<Audio> getAudioList() {
        if (audioList.isEmpty()) loadAudio();
        return audioList;
    }

    public void playNextTrack(){
        if (mediaPlayer.isPlaying()) {
            if (playedTrackID == audioList.size()) {
                playAudioFromDevice(0);
            } else playAudioFromDevice(playedTrackID);
        }
    }
    public void playPrevTrack(){
        if (mediaPlayer.isPlaying()) {
            if (playedTrackID == 0) {
                playAudioFromDevice(audioList.size());
            } else playAudioFromDevice(playedTrackID - 2);
        }
    }
    public void pauseTrack(){
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            resumePosition = mediaPlayer.getCurrentPosition();
        }else  if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(resumePosition);
            mediaPlayer.start();
        }
    }
}

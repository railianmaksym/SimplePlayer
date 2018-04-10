package com.simpleplayer.railianmaksym.simpleplayer.ui.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simpleplayer.railianmaksym.simpleplayer.R;
import com.simpleplayer.railianmaksym.simpleplayer.managers.AudioManager;
import com.simpleplayer.railianmaksym.simpleplayer.repository.Audio;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raili on 03.04.2018.
 */

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {
    private AudioManager audioManager;
    private List<Audio> audioList;

    AudioAdapter(AudioManager audioManager, List<Audio> audioList) {
        this.audioManager = audioManager;
        this.audioList = audioList;
    }

    @Override
    public AudioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_card,parent,false));
    }

    @Override
    public void onBindViewHolder(AudioAdapter.ViewHolder holder, int position) {
    holder.song_name.setText(audioList.get(position).getTitle());
    holder.music_card.setOnClickListener(view->{
    audioManager.playAudioFromDevice(position);
    });
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.song_name)
        TextView song_name;
        @BindView(R.id.music_card)
        ConstraintLayout music_card;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

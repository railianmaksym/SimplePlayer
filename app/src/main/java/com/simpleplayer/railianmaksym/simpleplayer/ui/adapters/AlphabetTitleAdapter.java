package com.simpleplayer.railianmaksym.simpleplayer.ui.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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

public class AlphabetTitleAdapter extends RecyclerView.Adapter<AlphabetTitleAdapter.ViewHolder> {

    List<String> titles;
    List<Audio> audioList;
    Context context;
    AudioManager audioManager;

    public AlphabetTitleAdapter(List<String> titles, List<Audio> audioList, Context context, AudioManager audioManager) {
        this.titles = titles;
        this.audioList = audioList;
        this.context = context;
        this.audioManager = audioManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_rv_element,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    holder.music_alphabet_title.setText(titles.get(position));
    holder.music_recyclerview.setLayoutManager(new LinearLayoutManager(context));
    holder.music_recyclerview.setAdapter(new AudioAdapter(audioManager,context,audioList));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.music_alphabet_title)
        TextView music_alphabet_title;
        @BindView(R.id.music_recyclerview)
        RecyclerView music_recyclerview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

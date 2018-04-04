package com.simpleplayer.railianmaksym.simpleplayer.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simpleplayer.railianmaksym.simpleplayer.MyApplication;
import com.simpleplayer.railianmaksym.simpleplayer.R;
import com.simpleplayer.railianmaksym.simpleplayer.managers.AudioManager;
import com.simpleplayer.railianmaksym.simpleplayer.ui.adapters.AlphabetTitleAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MusicFragment extends BaseFragment {
    @Inject
    AudioManager audioManager;
    RecyclerView music_RV;
    List<String> titles=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getContext().getApplicationContext()).getAppComponent().inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        music_RV=view.findViewById(R.id.music_RV);
        titles.add("A");
        //audioManager.playAudio("http://mg1.i.ua/g/3a160501de692253d4ce290412469ebf/5ac34931/music3/3/1/1282813");
        music_RV.setLayoutManager(new LinearLayoutManager(getContext()));
        music_RV.setAdapter(new AlphabetTitleAdapter(titles,audioManager.getAudioList(),getContext(),audioManager));
        return view;
    }


}

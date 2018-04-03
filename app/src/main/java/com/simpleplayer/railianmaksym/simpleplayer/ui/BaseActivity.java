package com.simpleplayer.railianmaksym.simpleplayer.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;


public class BaseActivity extends AppCompatActivity {
    private CompositeDisposable mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscription = new CompositeDisposable();
    }

    @Override
    protected void onDestroy() {
        if (mSubscription != null)
            mSubscription.dispose();
        super.onDestroy();
    }

    public CompositeDisposable getSubscription() {
        return mSubscription;
    }

}
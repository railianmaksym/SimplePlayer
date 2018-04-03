package com.simpleplayer.railianmaksym.simpleplayer.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by raili on 02.04.2018.
 */

public class BaseFragment extends android.support.v4.app.Fragment {

    private CompositeDisposable mSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscription = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null)
            mSubscription.dispose();
        super.onDestroy();
    }

    public CompositeDisposable getSubscription() {
        return mSubscription;
    }
}

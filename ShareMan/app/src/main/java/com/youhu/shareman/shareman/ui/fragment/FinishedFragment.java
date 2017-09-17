package com.youhu.shareman.shareman.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youhu.shareman.shareman.R;

/**
 * Created by Touch on 2017/8/29.
 */

public class FinishedFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_finished,container,false);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }
}

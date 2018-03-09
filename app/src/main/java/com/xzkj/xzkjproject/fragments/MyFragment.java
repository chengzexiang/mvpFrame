package com.xzkj.xzkjproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xzkj.xzkjproject.R;
import com.xzkj.xzkjproject.base.BaseFragment;
import com.xzkj.xzkjproject.custom.TitleBarView;

public class MyFragment extends BaseFragment {
    private TitleBarView mTitle_bar_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initView() {
        mTitle_bar_view = mView.findViewById(R.id.title_bar_view);
    }

    @Override
    public void initData() {
        mTitle_bar_view.setTitle(getResources().getString(R.string.my_text));
    }

    @Override
    public void setListener() {

    }


    @Override
    public void loadData() {

    }

    @Override
    public void onSelectedFragment(boolean state) {
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my_view;
    }
}

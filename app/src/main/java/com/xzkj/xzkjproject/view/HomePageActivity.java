package com.xzkj.xzkjproject.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.xzkj.xzkjproject.adapters.HomeFragmentAdapter;
import com.xzkj.xzkjproject.base.BaseActivity;
import com.xzkj.xzkjproject.custom.BottomTitleViewPager;
import com.xzkj.xzkjproject.R;
import com.xzkj.xzkjproject.fragments.LoanFragment;
import com.xzkj.xzkjproject.fragments.MyFragment;
import com.xzkj.xzkjproject.fragments.RepaymentFragment;
import com.xzkj.xzkjproject.request.OkGoUtil;
import com.xzkj.xzkjproject.utils.statusbar.Eyes;

public class HomePageActivity extends BaseActivity {
    public BottomTitleViewPager mBottomVp;
    private Fragment[] fragments;
    private String[] titleTabs;
    private LoanFragment mLoanFragment;
    private RepaymentFragment mRepaymentFragment;
    private MyFragment mMyfragment;
    private int currentPositiion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Eyes.setStatusBarLightMode(this, Color.WHITE);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home_page;
    }

    @Override
    public void initView() {
        mBottomVp = findViewById(R.id.bottom_vp);
    }

    @Override
    public void initData() {
        if (mLoanFragment == null) {
            mLoanFragment = new LoanFragment();
        }
        if (mRepaymentFragment == null) {
            mRepaymentFragment = new RepaymentFragment();
        }
        if (mMyfragment == null) {
            mMyfragment = new MyFragment();
        }

        fragments = new Fragment[]{mLoanFragment, mRepaymentFragment, mMyfragment};
        titleTabs = getResources().getStringArray(R.array.home_text_arr);
        FragmentManager fm = getSupportFragmentManager();
        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(fm, fragments);
        mBottomVp.setTitles(titleTabs);
        mBottomVp.setAdapter(homeFragmentAdapter);
        mBottomVp.setCurrentItem(currentPositiion);
    }

    @Override
    public void setListener() {
        mBottomVp.setOnPageSelectListener((position) -> {
            if (currentPositiion == position) {
                return;
            }
            currentPositiion = position;
            mLoanFragment.onSelectedFragment(position == 0);
            mRepaymentFragment.onSelectedFragment(position == 1);
            mMyfragment.onSelectedFragment(position == 2);
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            FragmentManager fm = getSupportFragmentManager();
            if (mLoanFragment != null) {
                fm.putFragment(outState, "mLoanFragment", mLoanFragment);
            }
            if (mRepaymentFragment != null) {
                fm.putFragment(outState, "mRepaymentFragment", mRepaymentFragment);
            }
            if (mMyfragment != null) {
                fm.putFragment(outState, "mMyfragment", mMyfragment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment;
            fragment = fm.getFragment(savedInstanceState, "mLoanFragment");
            if (fragment != null && fragment instanceof LoanFragment) {
                mLoanFragment = (LoanFragment) fragment;
            }
            fragment = fm.getFragment(savedInstanceState, "mRepaymentFragment");
            if (fragment != null && fragment instanceof RepaymentFragment) {
                mRepaymentFragment = (RepaymentFragment) fragment;
            }
            fragment = fm.getFragment(savedInstanceState, "mMyfragment");
            if (fragment != null && fragment instanceof MyFragment) {
                mMyfragment = (MyFragment) fragment;
            }
            initView();
            initData();
            setListener();
            loadData();
        }
    }
}

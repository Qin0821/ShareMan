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

public class FeedbackFragment extends BaseFragment {

//    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_feedbacking,container,false);

        return view;
    }

    @Override
    protected void initUI() {
        //获取控件
//        ViewPager viewpager=getView().findViewById(R.id.fragment_viewpager);
//        //设置适配器
//        adapter = new MyAdapter(getChildFragmentManager());
//        viewpager.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    @Override
    public void showMessage(String message) {

    }


    //适配器
//    class MyAdapter extends FragmentStatePagerAdapter {
//
//        public MyAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            BaseFragment fragment = FragmentFactory.getChartFragment(position);
//            return fragment;
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return super.getPageTitle(position);
//        }
//    }
}

package com.youhu.shareman.shareman.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.MyApplyingPagerAdapter;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Touch on 2017/9/8.
 */

public class ApplyingFragment extends ViewPagerBaseFragment {


    private ViewPager pager;
    private List<View> viewList = new ArrayList<View>();
    private MyApplyingPagerAdapter viewAdapter;
    private LayoutInflater inflater;
    private View view;
    private TextView textView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_applying, container, false);
        //获取ViewPager
        pager = (ViewPager) view.findViewById(R.id.applying_viewpager);
        pager.setPageMargin((int)getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));

        //获取view
        //查找布局文件用LayoutInflater.inflate
        View view1 = inflater.inflate(R.layout.viewpager_applying_item, null);
        View view2 = inflater.inflate(R.layout.viewpager_applying_item, null);
        View view3 = inflater.inflate(R.layout.viewpager_applying_item, null);

        //将View加入到集合
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        //实例化适配器
        viewAdapter = new MyApplyingPagerAdapter(getContext(),viewList);

        //设置适配器
        pager.setAdapter(viewAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}

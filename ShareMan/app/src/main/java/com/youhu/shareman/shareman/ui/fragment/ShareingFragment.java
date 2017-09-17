package com.youhu.shareman.shareman.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Touch on 2017/9/8.
 */

public class ShareingFragment extends ViewPagerBaseFragment {
    private ViewPager pager;
    private List<View> viewList = new ArrayList<View>();
    private MyViewPagerAdapter viewAdapter;
    private LayoutInflater inflater;
    private View view;
    private TextView textView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_shareing, container, false);
        //获取ViewPager
        pager = (ViewPager) view.findViewById(R.id.shareing_viewpager);
        pager.setPageMargin((int)getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));

        //获取view
        //查找布局文件用LayoutInflater.inflate
        View view1 = inflater.inflate(R.layout.viewpager_shareing_item, null);
        View view2 = inflater.inflate(R.layout.viewpager_shareing_item, null);
        View view3 = inflater.inflate(R.layout.viewpager_shareing_item, null);

        //将View加入到集合
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        //实例化适配器
        viewAdapter = new MyViewPagerAdapter(viewList);

        //设置适配器
        pager.setAdapter(viewAdapter);
        return view;
    }

}

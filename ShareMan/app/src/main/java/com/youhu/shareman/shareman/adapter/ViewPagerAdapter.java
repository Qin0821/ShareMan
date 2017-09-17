package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Touch on 2017/8/22.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<View> pages;
    public String[] mTitleArray;

    public ViewPagerAdapter(Context context, List<View> pages, String[] mTitleArray) {
        this.context = context;
        this.pages = pages;
        this.mTitleArray = mTitleArray;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mTitleArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

//        View view=View.inflate(context,R.layout.layout_list_item,null);
        View page = pages.get(position);

        //设置显示图片
        container.addView(page, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
//        container.addView(view);
        return page;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleArray[position];
    }

}

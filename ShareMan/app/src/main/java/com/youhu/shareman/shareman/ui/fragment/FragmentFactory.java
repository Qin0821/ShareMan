package com.youhu.shareman.shareman.ui.fragment;

/**
 * Created by Touch on 2017/8/29.
 */

import android.support.v4.util.SparseArrayCompat;


public class FragmentFactory {

    static SparseArrayCompat<BaseFragment> cachesFragment = new SparseArrayCompat<>();//
    static SparseArrayCompat<BaseFragment> cachesChartFragment = new SparseArrayCompat<>();//图表

    /**
     * 图表
     *
     * @param position
     * @return
     */
    public static BaseFragment getChartFragment(int position) {
        BaseFragment fragment = null;
        BaseFragment tempFragment = cachesChartFragment.get(position);
        if (tempFragment != null) {
            fragment = tempFragment;
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new FeedbackFragment();
                break;
            case 1:
                fragment = new FinishedFragment();
                break;
            case 2:
                fragment = new CanceledFragment();
                break;
        }
        cachesChartFragment.put(position, fragment);
        return fragment;
    }

    //移除所有
    public static void removeAllFragment() {
        if (cachesFragment != null && cachesFragment.size() > 0) {
            cachesFragment.clear();
        }
    }

    //拿到列表
    public static SparseArrayCompat<BaseFragment> getListFragment() {
        if (cachesFragment != null && cachesFragment.size() > 0) {
            return cachesFragment;
        }
        return null;
    }
}
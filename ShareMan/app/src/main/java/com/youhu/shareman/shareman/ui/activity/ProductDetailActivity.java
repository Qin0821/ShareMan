package com.youhu.shareman.shareman.ui.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.ProductDetailAdapter;
import com.youhu.shareman.shareman.adapter.ViewPagerAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.data.ProductDetailInfo;
import com.youhu.shareman.shareman.ui.fragment.ScrollViewFragment;
import com.youhu.shareman.shareman.ui.widget.ISlideCallback;
import com.youhu.shareman.shareman.ui.widget.SlideDetailsLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Touch on 2017/8/19.
 */

public class ProductDetailActivity extends BaseActivity implements BaseView,ISlideCallback {


    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.slidedetails)
    SlideDetailsLayout mSlideDetailsLayout;

    //tab标题
    private ViewPagerAdapter adapter;
    private String[] mTitleArray = {"商品详情","商品规格"};
    private ProductDetailAdapter productDetailAdapter1;
    private ProductDetailAdapter productDetailAdapter2;
    private List<ProductDetailInfo> datas;
    private List<View> pages;
    //自定义底部弹窗
    private View inflate;
    private Dialog dialog;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_product_detail);
        super.initBind();
    }

    @Override
    protected void initUI() {
        setContext(this);

        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.slidedetails_front, new ScrollViewFragment()).commit();

        //关于事件分发
        initViewPager();
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

    private void initViewPager() {
        //初始化页面
//        NoScrollListView imagelistview = new NoScrollListView(getContext());
//        NoScrollListView formatlistview = new NoScrollListView(getContext());

        ListView imagelistview = new ListView(getContext());
        ListView formatlistview = new ListView(getContext());

        pages=new ArrayList<View>();
        pages.add(imagelistview);
        pages.add(formatlistview);


        //viewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext(),pages,mTitleArray);
        mViewPager.setAdapter(adapter);

        //设置ListView
        datas=new ArrayList<ProductDetailInfo>();
        datas.add(new ProductDetailInfo(R.drawable.product_detail_1));
        datas.add(new ProductDetailInfo(R.drawable.product_detail_2));
        datas.add(new ProductDetailInfo(R.drawable.product_detail_3));
        datas.add(new ProductDetailInfo(R.drawable.product_detail_4));
        datas.add(new ProductDetailInfo(R.drawable.product_detail_5));

        productDetailAdapter1=new ProductDetailAdapter();
        productDetailAdapter1.setContext(this);
        productDetailAdapter1.setDatas(datas);
        imagelistview.setAdapter(productDetailAdapter1);
        productDetailAdapter2=new ProductDetailAdapter();
        productDetailAdapter2.setContext(this);
        productDetailAdapter2.setDatas(datas);
        formatlistview.setAdapter(productDetailAdapter2);
        //viewPager与tab联动
        mTabs.setupWithViewPager(mViewPager);
    }


    //控制详情页和图文页
    @Override
    public void openDetails(boolean smooth) {
        mSlideDetailsLayout.smoothOpen(smooth);
    }
    @Override
    public void closeDetails(boolean smooth) {
        mSlideDetailsLayout.smoothClose(smooth);
    }

}

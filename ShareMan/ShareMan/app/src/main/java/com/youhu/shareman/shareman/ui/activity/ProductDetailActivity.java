package com.youhu.shareman.shareman.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.ProductDetailAdapter;
import com.youhu.shareman.shareman.adapter.ViewPagerAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.ProductDetailModel;
import com.youhu.shareman.shareman.presentercoml.ProductDetailPresenter;
import com.youhu.shareman.shareman.ui.fragment.ProductDetailScrollViewFragment;
import com.youhu.shareman.shareman.ui.widget.ISlideCallback;
import com.youhu.shareman.shareman.ui.widget.SlideDetailsLayout;
import com.youhu.shareman.shareman.view.ProductDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Touch on 2017/8/19.
 */

public class ProductDetailActivity extends BaseActivity implements ISlideCallback {


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
    private List<View> pages;
    //自定义底部弹窗
    private View inflate;
    private Dialog dialog;
    ProductDetailPresenter productDetailPresenter=new ProductDetailPresenter();
    private ListView imagelistview;
    private ListView formatlistview;

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

        //获取传递过来的version
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String version =bundle.getString("bannerVersion","");

        //给Fragment传递version
        Bundle bundle1=new Bundle();
        bundle1.putString("toVersion",version);


        productDetailPresenter.onCreate();
        productDetailPresenter.attachView(productDetailView);
        productDetailPresenter.getProductDetail(version);


        Fragment fragment=new ProductDetailScrollViewFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.slidedetails_front, fragment).commit();

        fragment.setArguments(bundle1);

        //初始化页面
//        NoScrollListView imagelistview = new NoScrollListView(getContext());
//        NoScrollListView formatlistview = new NoScrollListView(getContext());

        imagelistview = new ListView(getContext());
        formatlistview = new ListView(getContext());

        pages=new ArrayList<View>();
        pages.add(imagelistview);
        pages.add(formatlistview);


        //viewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext(),pages,mTitleArray);
        mViewPager.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }


    ProductDetailView productDetailView=new ProductDetailView() {
        @Override
        public void getProductDetail(BaseData<ProductDetailModel> productDetailModel) {
            //关于事件分发
            initViewPager(productDetailModel.getData());
        }

        @Override
        public void showMessage(String message) {

        }
    };

    private void initViewPager(ProductDetailModel imageData) {
        //设置详情ListView
        List<String> detailData=new ArrayList<String>();
        detailData.add(imageData.getGraphics());

        //详情页面
        productDetailAdapter1=new ProductDetailAdapter();
        productDetailAdapter1.setContext(this);
        productDetailAdapter1.setDatas(detailData);
        imagelistview.setAdapter(productDetailAdapter1);

        //设置参数ListView
        List<String> paramData=new ArrayList<String>();
        paramData.add(imageData.getParams());
        productDetailAdapter2=new ProductDetailAdapter();
        productDetailAdapter2.setContext(this);
        productDetailAdapter2.setDatas(paramData);
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

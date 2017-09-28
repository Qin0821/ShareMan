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
import android.widget.ImageView;
import android.widget.ListView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.ProductDetailAdapter;
import com.youhu.shareman.shareman.adapter.ViewPagerAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.model.data.ProductDetailModel;
import com.youhu.shareman.shareman.model.data.ZhimaModel;
import com.youhu.shareman.shareman.presentercoml.ProductDetailPresenter;
import com.youhu.shareman.shareman.ui.fragment.ProductDetailScrollViewFragment;
import com.youhu.shareman.shareman.ui.widget.ISlideCallback;
import com.youhu.shareman.shareman.ui.widget.SlideDetailsLayout;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.util.ToastUtils;
import com.youhu.shareman.shareman.view.ProductDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/19.
 */

public class ProductDetailActivity extends BaseActivity implements ISlideCallback,ProductDetailScrollViewFragment.FragmentInteraction {


    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.slidedetails)
    SlideDetailsLayout mSlideDetailsLayout;
    @BindView(R.id.img_start_booking)
    ImageView mStart;

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
    private ProductDetailModel currentProductDetailData;
    private ListView imagelistview;
    private ListView formatlistview;
    private int chooseId;
    private String phoneNumber;
    private String token;
    private String version;

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

        //账户信息
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        //获取传递过来的version
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        version = bundle.getString("bannerVersion","");

        //给Fragment传递version
        Bundle bundle1=new Bundle();
        bundle1.putString("toVersion", version);


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
            //当前详情信息
            if(productDetailModel!=null){
                currentProductDetailData=productDetailModel.getData();
            }
            //关于事件分发
            initViewPager(productDetailModel.getData());
        }

        @Override
        public void doStartBooking(NormalModel startData) {
            if("0".equals(startData.getCode())){
//                productDetailPresenter.getZhima(phoneNumber,token);
                productDetailPresenter.getZhima("15701236749","4f4f5ccb9f7ad689ba2552c2c0d25703");
                ToastUtils.show(getContext(),"审核中...");
            }else if("1001".equals(startData.getCode())){
                ToastUtils.show(getContext(),"未登录");
            }else if("1002".equals(startData.getCode())){
                ToastUtils.show(getContext(),"请完善个人信息");
            }else if("1010".equals(startData.getCode())){
                ToastUtils.show(getContext(),"订单总数不能超过2个");
            }
        }

        @Override
        public void doGetZhima(BaseData<ZhimaModel> zhimaData) {
            if("Y".equals(zhimaData.getData().getZmxy_score())&&"0".equals(zhimaData.getData().getHygz_level())||"1".equals(zhimaData.getData().getHygz_level())){
                ToastUtils.show(getContext(),"预约成功");
            }else{
                ToastUtils.show(getContext(),"预约失败");
            }

        }

        @Override
        public void showMessage(String message) {

        }
    };

    @OnClick(R.id.img_start_booking)
    void onClick(View view){
        switch (view.getId()){
            case R.id.img_start_booking:
                if(chooseId==-1){
                    ToastUtils.show(getContext(),"请选择颜色、内存和增值服务");
                }else{
                    //TODO
                    //获取用户颜色内存
                    String introduceTitle=currentProductDetailData.getTagBean().get(chooseId).getIntroduce_title();
                    //发送生成订单请求
                    ToastUtils.show(getContext(),""+phoneNumber+token+version+introduceTitle);
//                    productDetailPresenter.startBooking(phoneNumber,token,version,introduceTitle);
                    productDetailPresenter.startBooking("15701236749","4f4f5ccb9f7ad689ba2552c2c0d25703",version,introduceTitle);
                    //发送请求芝麻信用

                }
                break;
        }
    }

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

    @Override
    public void process(int choose) {
        chooseId=choose;
    }
}

package com.youhu.shareman.shareman;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.youhu.shareman.shareman.adapter.MyHotRecomentAdapter;
import com.youhu.shareman.shareman.adapter.MyListAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.data.UserInfo;
import com.youhu.shareman.shareman.model.constant.AppConfig;
import com.youhu.shareman.shareman.model.data.BannerModel;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.HotRecomentModel;
import com.youhu.shareman.shareman.presentercoml.MainActivityPreserter;
import com.youhu.shareman.shareman.ui.activity.BrandActivity;
import com.youhu.shareman.shareman.ui.activity.FeedBackOrderNormalActivity;
import com.youhu.shareman.shareman.ui.activity.FeedbackActivity;
import com.youhu.shareman.shareman.ui.activity.InfomationActivity;
import com.youhu.shareman.shareman.ui.activity.LoginActivity;
import com.youhu.shareman.shareman.ui.activity.MessageActivity;
import com.youhu.shareman.shareman.ui.activity.ProductDetailActivity;
import com.youhu.shareman.shareman.ui.activity.RecoverActivity;
import com.youhu.shareman.shareman.ui.activity.ServiceMobilePhoneTypeActivity;
import com.youhu.shareman.shareman.ui.activity.ServiceOrderActivity;
import com.youhu.shareman.shareman.ui.activity.ShareOrderActivity;
import com.youhu.shareman.shareman.ui.activity.ShareTypeActivity;
import com.youhu.shareman.shareman.ui.activity.UserInfoActivity;
import com.youhu.shareman.shareman.ui.activity.VoucherActivity;
import com.youhu.shareman.shareman.util.CheckUtils;
import com.youhu.shareman.shareman.util.GlideImageLoader;
import com.youhu.shareman.shareman.util.GlideRoundTransform;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.view.MainActivityView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BaseView {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_user)
    ImageView mUser;
    @BindView(R.id.main_message)
    ImageView mMessage;
    @BindView(R.id.drawere_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.ll_recover)
    LinearLayout mRecover;
    @BindView(R.id.ll_share)
    LinearLayout mShare;
    @BindView(R.id.ll_service)
    LinearLayout mService;
    @BindView(R.id.economy)
    LinearLayout mEconomy;
    @BindView(R.id.style)
    LinearLayout mStyle;
    @BindView(R.id.preference)
    LinearLayout mPreference;
    @BindView(R.id.lv_personal)
    ListView mListView;
    @BindView(R.id.hot_recomment)
    GridView mHotRecoment;
//    @BindView(R.id.hot_recomment)
//    ListView mHotRecoment;
    @BindView(R.id.user_image)
    ImageView mUserImage;


    //经济实惠
    private static int STATE_ECONOMY=0;
    //个性潮流
    private static int STATE_STYLE=1;
    //特惠活动
    private static int STATE_PREFERENCE=2;
    //退出时的时间
    private long mExitTime;
    private ActionBarDrawerToggle mActionBarToggle;
    private MyListAdapter mListAdapter;
    private MyHotRecomentAdapter hotRecomentAdapter;
    private List<UserInfo> datas;
    private List<HotRecomentModel> imageUrl;
    //对话框
    private TextView firstChoose;
    private TextView secondChoose;
    private TextView chooseCancel;
    private View inflate;
    //自定义底部弹窗
    private Dialog dialog;
    //打电话权限申请
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private String phoneNumber;
    private String token;
    MainActivityPreserter mainActivityPreserter=new MainActivityPreserter();
    private List<String> bannerImageUrl;
    private String version;
    private List<BannerModel> bannerList;


    @Override
    protected void initBind() {
        setContentView(R.layout.activity_main);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文设置
        setContext(this);

        //登录用户信息
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        //请求轮播图数据
        mainActivityPreserter.onCreate();
        mainActivityPreserter.attachView(mainActivityView);
        mainActivityPreserter.getMainBanner();
        mainActivityPreserter.getHotRecoment();

        //初始化头像
        Glide.with(getContext()).load(R.drawable.sb_gray).asBitmap().transform(new GlideRoundTransform(getContext(), 0)).into(new BitmapImageViewTarget(mUserImage) {
            @Override
            protected void setResource(Bitmap resource) {
                mUserImage.setImageBitmap(resource);
            }
        });

        //初始化抽屉
        initDrawereLayout();
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


    //请求接口回调
    MainActivityView mainActivityView=new MainActivityView() {
        @Override
        public void doBanner(BaseData<List<BannerModel>> bannerData) {
            bannerList=bannerData.getData();
            //轮播图
            bannerImageUrl = new ArrayList<>();
            if(bannerData!=null){
                for(int i=0;i<bannerData.getData().size();i++){
                    bannerImageUrl.add(AppConfig.IMAGE_URL+bannerData.getData().get(i).getCarousel());
                }
                initBanner(bannerImageUrl);
            }

        }

        @Override
        public void doHotRecoment(BaseData<List<HotRecomentModel>> hotRecomentData) {
            //初始化热门推荐
            //数据来源
            imageUrl = new ArrayList<HotRecomentModel>();
            imageUrl=hotRecomentData.getData();
            initHotRecoment(imageUrl);
        }

        @Override
        public void showMessage(String message) {

        }
    };

    //按钮点击跳转
    @OnClick({R.id.ll_recover, R.id.ll_share, R.id.ll_service, R.id.main_user, R.id.main_message, R.id.economy, R.id.style, R.id.preference, R.id.user_image})
    void onClick(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()) {
            //回收
            case R.id.ll_recover:
                JumpUtil.overlay(this, RecoverActivity.class);
                break;
            //共享
            case R.id.ll_share:
                JumpUtil.overlay(this, ShareTypeActivity.class);
                break;
            //维修
            case R.id.ll_service:
                JumpUtil.overlay(this, ServiceMobilePhoneTypeActivity.class);
                break;
            //个人中心
            case R.id.main_user:
                //弹出侧边框
                toggle();
                break;
            //新消息提醒
            case R.id.main_message:
                //优惠信息提醒
                if(CheckUtils.isLogin(getContext())){
                    JumpUtil.overlay(this,MessageActivity.class);
                }else{
                    JumpUtil.overlay(this,LoginActivity.class);
                }
                break;
            //手机分类
            case R.id.economy:
                bundle.putInt("type_state",STATE_ECONOMY);
                JumpUtil.overlay(this,BrandActivity.class,bundle);
                break;
            case R.id.style:
                bundle.putInt("type_state",STATE_STYLE);
                JumpUtil.overlay(this, BrandActivity.class,bundle);
                break;
            case R.id.preference:
                bundle.putInt("type_state",STATE_PREFERENCE);
                JumpUtil.overlay(this, BrandActivity.class,bundle);
                break;
            //个人资料设置
            case R.id.user_image:
                //判断是否已经登录，未登录跳转登录界面，已登录跳转对应用户信息界面
                if(CheckUtils.isLogin(getContext())){
                    //已经登录，跳转该用户的信息界面
                    JumpUtil.overlay(this, UserInfoActivity.class);
                }else{
                    //未登录，跳转到登录界面
                    JumpUtil.overlay(this, LoginActivity.class);
                }

                break;
        }
    }

    //初始化侧拉栏
    private void initDrawereLayout() {
        //不显示标题
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        //按钮联动
        mActionBarToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, 0, 0);

        //显示菜单
        datas = new ArrayList<UserInfo>();
        datas.add(new UserInfo(R.drawable.icon_1, "共享订单"));
        datas.add(new UserInfo(R.drawable.icon_2, "回收订单"));
        datas.add(new UserInfo(R.drawable.icon_3, "维修订单"));
        datas.add(new UserInfo(R.drawable.icon_information, "身份信息"));
        datas.add(new UserInfo(R.drawable.icon_4, "联系客服"));
        datas.add(new UserInfo(R.drawable.icon_6, "代金券"));
        datas.add(new UserInfo(R.drawable.icon_5, "打小报告"));


        mListAdapter = new MyListAdapter();
        mListAdapter.setContext(this);
        mListAdapter.setDatas(datas);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    //共享订单
                    case 0:
                        if(CheckUtils.isLogin(getContext())){
                            JumpUtil.overlay(MainActivity.this, ShareOrderActivity.class);
                        }else{
                            JumpUtil.overlay(getContext(),LoginActivity.class);
                        }
                        break;
                    //回收订单
                    case 1:
                        JumpUtil.overlay(MainActivity.this, FeedBackOrderNormalActivity.class);
//                        JumpUtil.overlay(MainActivity.this, FeedBackOrderActivity.class);
                        break;
                    //维修订单
                    case 2:
                        JumpUtil.overlay(MainActivity.this, ServiceOrderActivity.class);
                        break;
                    //身份信息
                    case 3:
                        if(CheckUtils.isLogin(getContext())){
                            JumpUtil.overlay(MainActivity.this, InfomationActivity.class);
                        }else{
                            JumpUtil.overlay(getContext(),LoginActivity.class);
                        }
                        break;
                    //联系客服
                    case 4:
                        dialogShow();
                        break;
                    //代金券
                    case 5:
                        if(CheckUtils.isLogin(getContext())){
                            JumpUtil.overlay(MainActivity.this, VoucherActivity.class);
                        }else{
                            JumpUtil.overlay(getContext(),LoginActivity.class);
                        }
                        break;
                    //打小报告
                    case 6:
                        if(CheckUtils.isLogin(getContext())){
                            JumpUtil.overlay(MainActivity.this, FeedbackActivity.class);
                        }else{
                            JumpUtil.overlay(getContext(),LoginActivity.class);
                        }
                        break;
                }
            }
        });
    }

    private void initHotRecoment(final List<HotRecomentModel> imageUrl) {

        hotRecomentAdapter = new MyHotRecomentAdapter();
        hotRecomentAdapter.setContext(this);
        hotRecomentAdapter.setDatas(imageUrl);
        mHotRecoment.setAdapter(hotRecomentAdapter);
        mHotRecoment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //获取version
                String version=imageUrl.get(position).getVersion();
                Bundle bundle=new Bundle();
                bundle.putString("bannerVersion",version);
                JumpUtil.overlay(getContext(),ProductDetailActivity.class,bundle);
            }
        });
    }

    //初始化轮播图
    private void initBanner(List<String> imageUrl) {
        //设置间隔
        mBanner.setDelayTime(3000);
        //添加图片
        mBanner.setImages(imageUrl);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //banner加点
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //点居中
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.ZoomOut);
        //bannerde图片的点击事件
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //获取version
                String version=bannerList.get(position).getVersion();
                Bundle bundle=new Bundle();
                bundle.putString("bannerVersion",version);
                JumpUtil.overlay(MainActivity.this, ProductDetailActivity.class,bundle);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    //按钮和DrawerLayout联动
    private void toggle() {
        int drawerLockMode = mDrawerLayout.getDrawerLockMode(GravityCompat.START);
        if (mDrawerLayout.isDrawerVisible(GravityCompat.START)
                && (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }


    //设置客服电话弹出框
    public void dialogShow() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.choose_phone_dialog_layout, null);
        //初始化控件
        firstChoose = inflate.findViewById(R.id.take_photo);
        secondChoose = inflate.findViewById(R.id.choose_photo);
        chooseCancel = inflate.findViewById(R.id.choose_phone_cancel);

        firstChoose.setText("客服电话");
//        secondChoose.setText("400-007-7006");
        secondChoose.setText("0571-87359253");
        secondChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.show(getContext(), "");
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // 没有获得授权，申请授权  
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE)) {
                        // 返回值：  
                        //如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.  
                        //如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.  
                        //如果设备策略禁止应用拥有这条权限, 这个方法也返回false.  
                        // 弹窗需要解释为何需要该权限，再次请求授权  
                        Toast.makeText(MainActivity.this, "请授权！", Toast.LENGTH_LONG).show();
                        // 帮跳转到该应用的设置界面，让用户手动授权  
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                } else {
                    // 已经获得授权，可以打电话
//                    String phoneNumber = "4000077006";
                    String phoneNumber = "0571-87359253";
                    Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));//直接拨打电话
                    startActivity(dialIntent);
                }
                dialog.dismiss();
            }

        });

        chooseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置Dialog距离底部的距离
        lp.y = 20;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        //显示对话框
        dialog.show();
    }


    //双击退出应用
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}

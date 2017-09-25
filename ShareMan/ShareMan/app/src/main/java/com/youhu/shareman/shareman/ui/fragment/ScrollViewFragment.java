package com.youhu.shareman.shareman.ui.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.ui.widget.PreSaleDialog;
import com.youhu.shareman.shareman.ui.widget.ShareDialog;
import com.youhu.shareman.shareman.ui.widget.ShopTagDiglog;
import com.youhu.shareman.shareman.ui.widget.tabGround.TagBean;
import com.youhu.shareman.shareman.ui.widget.tabGround.TagContainerLayout;
import com.youhu.shareman.shareman.util.GlideImageLoader;
import com.youhu.shareman.shareman.util.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * <b>Project:</b> SlideDetailsLayout<br>
 * <b>Create Date:</b> 16/1/26<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ScrollViewFragment extends ScrollViewBaseFragment {

    @BindView(R.id.product_banner)
    Banner mBanner;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.tv_product_name)
    TextView mProductName;
    @BindView(R.id.tv_product_price)
    TextView mProductPrice;
    @BindView(R.id.tv_product_low_price)
    TextView mProductSharePrice;
    @BindView(R.id.ig_product_detail_share)
    ImageView mProductDetailShare;
    @BindView(R.id.ig_product_detail_shouquan)
    ImageView mProductDetailShouQian;
    @BindView(R.id.rl_tiaokuan)
    RelativeLayout mRltiaokuan;
    @BindView(R.id.ll_tiaokuan)
    LinearLayout mLlTiaokuan;
    @BindView(R.id.ll_touch_shouqi)
    LinearLayout mLTouchShouqi;


    //售后保障弹窗状态
    private Boolean status=true;
    //自定义底部弹窗
    private View inflate;
    private Dialog dialog;
    //分享弹窗
    private ShareDialog mShareDialog;
    private static final  String APP_ID="wx27c6bc5e8c8f52f4";
    private IWXAPI api;
    //售前弹窗
    private PreSaleDialog preSaleDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product_scroll_view, null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //轮播图
        List<Integer> imageUrl = new ArrayList<>();
        imageUrl.add(R.drawable.product_detail_1);
        imageUrl.add(R.drawable.product_detail_2);
        imageUrl.add(R.drawable.product_detail_3);
        imageUrl.add(R.drawable.product_detail_4);
        imageUrl.add(R.drawable.product_detail_5);
        initBanner(imageUrl);

        //分享
        regToWx();
    }



    @OnClick({R.id.back,R.id.rl_tiaokuan,R.id.ig_product_detail_share,R.id.ig_product_detail_shouquan,R.id.choose_style,R.id.ll_touch_shouqi})
    void onClick(View view){
        switch (view.getId()){
            case R.id.ig_product_detail_share:
                //分享到微信和朋友圈
                //初始化选择分享弹窗
                initShareDialog();
                break;
            case R.id.ig_product_detail_shouquan:
                preSaleDialog=new PreSaleDialog(getContext());
                preSaleDialog.setCancelOnclickListener(new PreSaleDialog.onCancelOnclickListener() {
                    @Override
                    public void onCancelClick() {
                        preSaleDialog.dismiss();
                    }
                });
                preSaleDialog.show();
                break;
            case R.id.choose_style:
                dialogShow();
                break;
            case R.id.rl_tiaokuan:
                if(status){
                    mLlTiaokuan.setVisibility(View.VISIBLE);
                    status=false;
                }else{
                    mLlTiaokuan.setVisibility(View.GONE);
                    status=true;
                }
                break;
            case R.id.ll_touch_shouqi:
                mLlTiaokuan.setVisibility(View.GONE);
                status=true;
                break;
            case R.id.back:
                //返回按钮
                getActivity().onBackPressed();
                break;
        }
    }

    private void initShareDialog() {
        //初始化弹窗
        mShareDialog=new ShareDialog(getContext());
        //设置点击事件
        mShareDialog.setWechatOnclickListener(new ShareDialog.onWeChatOnclickListener() {
            @Override
            public void onWechatClick() {
                ToastUtils.show(getContext(),"分享到微信好友！");

                /*String text="真好玩";
                WXTextObject textobj=new WXTextObject();
                textobj.text=text;

                WXMediaMessage msg=new WXMediaMessage();
                msg.mediaObject=textobj;
                msg.description=text;

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;
                api.sendReq(req);*/

                WXTextObject textObject = new WXTextObject();
                textObject.text = "helloWorld";
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObject;
                msg.description = "吃鸡吃鸡";
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = "transaction";
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;

                api.sendReq(req);

                mShareDialog.dismiss();
            }
        });
        mShareDialog.setFriendSpaceOnclickListener(new ShareDialog.onFriendSpaceOnclickListener() {
            @Override
            public void onFriendSpaceClick() {
                String text="真好玩";
                WXTextObject textobj=new WXTextObject();
                textobj.text=text;

                WXMediaMessage msg=new WXMediaMessage();
                msg.mediaObject=textobj;
                msg.description=text;

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                api.sendReq(req);
                ToastUtils.show(getContext(),"分享到朋友圈！");
                mShareDialog.dismiss();
            }
        });

        mShareDialog.show();
    }

    //设置选择图片弹出框
    public void dialogShow(){
            List<TagBean> tagBeans=new ArrayList<>();
            tagBeans.add(new TagBean("16G",222,1000));
            tagBeans.add(new TagBean("32G",10,0));
            tagBeans.add(new TagBean("64G",20,555));
            tagBeans.add(new TagBean("128G",30,0));

            List<TagBean> tagBeans2=new ArrayList<>();
            tagBeans2.add(new TagBean("16G",40,0));
            tagBeans2.add(new TagBean("32G",50,0));
            tagBeans2.add(new TagBean("64G",77,0));
            tagBeans2.add(new TagBean("128G",99,34));

            List<TagBean> tagBeans3=new ArrayList<>();
            tagBeans3.add(new TagBean("16G",7,0));
            tagBeans3.add(new TagBean("32G",8,22));
            tagBeans3.add(new TagBean("64G",9,44));
            tagBeans3.add(new TagBean("128G",10,0));

            TagBean tagBean1=new TagBean();
            tagBean1.setTitle("酷黑");
            tagBean1.setTagBean(tagBeans);

            TagBean tagBean2=new TagBean();
            tagBean2.setTitle("红");
            tagBean2.setTagBean(tagBeans2);

            TagBean tagBean3=new TagBean();
            tagBean3.setTitle("土豪金");
            tagBean3.setTagBean(tagBeans3);

            TagBean tagBean4=new TagBean();
            tagBean4.setTitle("玫瑰红");
            tagBean4.setTagBean(tagBeans3);

            TagBean tagBean5=new TagBean();
            tagBean5.setTitle("宝蓝");
            tagBean5.setTagBean(tagBeans2);

            TagBean tagBean6=new TagBean();
            tagBean6.setTitle("银白");
            tagBean6.setTagBean(tagBeans);

            List<TagBean> listTwo=new ArrayList<>();
            listTwo.add(tagBean1);
            listTwo.add(tagBean2);
            listTwo.add(tagBean3);
            listTwo.add(tagBean4);
            listTwo.add(tagBean5);
            listTwo.add(tagBean6);

            ShopTagDiglog.Builder builder= new ShopTagDiglog.Builder(getContext());
            builder.setBanViewColor(new TagContainerLayout.ViewColor())
                    .setDefaultViewColor(new TagContainerLayout.ViewColor(ContextCompat.getColor(getContext(),R.color.backGroundColor),0,ContextCompat.getColor(getContext(),R.color.apptitle)))
                    .setClickViewColor(new TagContainerLayout.ViewColor(Color.parseColor("#333333"),Color.parseColor("#F20942"),ContextCompat.getColor(getContext(),R.color.white)))
                    .setTagBean(listTwo)
                    .create().show();

    }


    //初始化轮播图
    private void initBanner(List<Integer> imageUrl) {
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
//        mBanner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//                ToastUtils.show(getContext(), "我被点击了");
//            }
//        });
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    //初始化微信分享
    private void regToWx() {
        //通过WXAPIFactory工厂，获取IWXAPI实例
        api= WXAPIFactory.createWXAPI(getContext(),APP_ID,true);
        //将应用的appid注册到微信
        api.registerApp(APP_ID);
    }
}

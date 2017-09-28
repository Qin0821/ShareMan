package com.youhu.shareman.shareman.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.model.constant.AppConfig;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.model.data.ProductDetailModel;
import com.youhu.shareman.shareman.model.data.ZhimaModel;
import com.youhu.shareman.shareman.presentercoml.ProductDetailPresenter;
import com.youhu.shareman.shareman.ui.widget.PreSaleDialog;
import com.youhu.shareman.shareman.ui.widget.ShareDialog;
import com.youhu.shareman.shareman.ui.widget.TagDialog;
import com.youhu.shareman.shareman.util.GlideImageLoader;
import com.youhu.shareman.shareman.util.ToastUtils;
import com.youhu.shareman.shareman.view.ProductDetailView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.ByteArrayOutputStream;
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
public class ProductDetailScrollViewFragment extends ScrollViewBaseFragment {

    @BindView(R.id.product_banner)
    Banner mBanner;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.tv_product_name)
    TextView mProductName;
    @BindView(R.id.tv_product_price)
    TextView mProductPrice;
    @BindView(R.id.tv_product_depression)
    TextView mProductDepression;
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
    private int chooseType=-1;
    //设置回显

    //分享弹窗
    private ShareDialog mShareDialog;
    private static final  String APP_ID="wx27c6bc5e8c8f52f4";
    private IWXAPI api;
    //售前弹窗
    private PreSaleDialog preSaleDialog;
    //选择类型弹窗
    private TagDialog tagDialog;
    //展示的型号
    private String version;
    private ProductDetailModel productDetailData;
    ProductDetailPresenter productDetailPresenter=new ProductDetailPresenter();

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

        if (isAdded()) {//判断Fragment已经依附Activity
            version = getArguments().getString("toVersion");
        }
        productDetailPresenter.onCreate();
        productDetailPresenter.attachView(productDetailView);
        productDetailPresenter.getProductDetail(version);

        listterner.process(chooseType);
        //分享
        regToWx();
    }


    //请求回调数据
    ProductDetailView productDetailView=new ProductDetailView() {
        @Override
        public void getProductDetail(BaseData<ProductDetailModel> productDetailModel) {
            productDetailData=productDetailModel.getData();
            List<String> imageUrl = new ArrayList<>();
            //轮播图
            if(productDetailData!=null){
                for(int i=0;i<productDetailData.getProductImages().size();i++){
                    imageUrl.add(AppConfig.IMAGE_URL+productDetailData.getProductImages().get(i));
                }
            }

            initBanner(imageUrl);

            mProductName.setText(version);
            //总价值
            mProductPrice.setText("价值"+productDetailData.getOriginal_price());
            //共享价
            mProductSharePrice.setText("¥"+productDetailData.getReal_price());
            //折旧费
            mProductDepression.setText("折旧费"+productDetailData.getDepreciation_count()+"元/天");


        }

        @Override
        public void doStartBooking(NormalModel startData) {

        }

        @Override
        public void doGetZhima(BaseData<ZhimaModel> zhimaData) {

        }

        @Override
        public void showMessage(String message) {

        }
    };


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
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.share_image);
                WXImageObject imgObj = new WXImageObject(bmp);
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = imgObj;
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 120, 120, true);

                bmp.recycle();
                msg.thumbData = bmpToByteArray(thumbBmp, true);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;
                api.sendReq(req);

//                String text="真好玩";
//                WXTextObject textobj=new WXTextObject();
//                textobj.text=text;
//
//                WXMediaMessage msg=new WXMediaMessage();
//                msg.mediaObject=textobj;
//                msg.description=text;

//                SendMessageToWX.Req req = new SendMessageToWX.Req();
//                req.transaction = String.valueOf(System.currentTimeMillis());
//                req.message = msg;
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                api.sendReq(req);
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
                mShareDialog.dismiss();
            }
        });

        mShareDialog.show();
    }

    //设置选择规格弹出框
    public void dialogShow(){
        tagDialog=new TagDialog(getContext());
        tagDialog.setTagBeanList(productDetailData.getTagBean());
        tagDialog.setItemOnclickListener(new TagDialog.onItemOnclickListener() {
            @Override
            public void onItemClick(int chooseId) {
                if(chooseId==-1){
                    ToastUtils.show(getContext(),"请选择型号");
                }else{
                    tagDialog.dismiss();
                    //总价值
                    mProductPrice.setText("价值"+productDetailData.getTagBean().get(chooseId).getOriginal_price());
                    //共享价
                    mProductSharePrice.setText("¥"+productDetailData.getTagBean().get(chooseId).getReal_price());
                    //折旧费
                    mProductDepression.setText("折旧费"+productDetailData.getTagBean().get(chooseId).getDepreciation_count()+"元/天");
                    chooseType=chooseId;
                }
                listterner.process(chooseType);
            }
        });
        tagDialog.show();
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
        mBanner.start();
    }

    //初始化微信分享
    private void regToWx() {
        //通过WXAPIFactory工厂，获取IWXAPI实例
        api= WXAPIFactory.createWXAPI(getContext(),APP_ID,true);
        //将应用的appid注册到微信
        api.registerApp(APP_ID);
    }


    //定义用来与外部activity交互，获取到宿主activity
    private FragmentInteraction listterner;

    //定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        void process(int choose);
    }

    // 当FRagmen被加载到activity的时候会被回调
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof FragmentInteraction) {
            //获取到宿主activity并赋值
            listterner = (FragmentInteraction)activity;
        } else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    //把传递进来的activity对象释放掉
    @Override
    public void onDetach() {
        super.onDetach();
        listterner = null;
    }



    //微信方法提取
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

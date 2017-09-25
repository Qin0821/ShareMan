package com.youhu.shareman.shareman.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presentercoml.SignUpAndPaymentPresenter;
import com.youhu.shareman.shareman.view.SignUpAndPaymentView;
import com.youhu.shareman.shareman.ui.widget.LinePathView;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.util.ToastUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/24.
 */

public class SignUpAndPaymentActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.choose_sign_contract)
    ImageView mChooseSignContract;
    @BindView(R.id.btn_sign_payment)
    Button mSignAndPayment;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;


    //记录是否同意协议按钮状态,默认选择
    private boolean chooseState=true;
    //自定义底部弹窗
    private View inflate;
    private Dialog dialog;
    private TextView mClear;
    private ImageView mCancel;
    private TextView mSave;
    private LinePathView mLine;
    //签名文件本地保存路径，上传时选择服务器作为路径
    public static String path= Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "qm.png";
    private String phoneNumber;
    private String token;
    private Bundle bundle;
    private int orderId;
    private String postData;

    SignUpAndPaymentPresenter signUpAndPaymentPresenter=new SignUpAndPaymentPresenter();

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_sign_and_payment);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文
        setContext(this);
        mTitle.setText("签约支付");
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);
        Intent intent=getIntent();
        bundle=intent.getExtras();
        orderId=bundle.getInt("orderId");
//        postData="phoneNumber="+phoneNumber+"&token="+token+"&orderId="+orderId;
        postData="phoneNumber=15701236749&token=fcfcf1962e40afc99ea1e84a01e6c001&orderId="+orderId;

        //默认接受协议
        mChooseSignContract.setImageResource(R.drawable.btn_choose_blue);
        mSignAndPayment.setBackgroundResource(R.drawable.button_react);

        //获取协议界面
        signUpAndPaymentPresenter.onCreate();
        signUpAndPaymentPresenter.attachView(signUpAndPaymentView);
        signUpAndPaymentPresenter.getOrderAgreement("15701236749","fcfcf1962e40afc99ea1e84a01e6c001",orderId);

        //初始化WebView
        initWebView();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    SignUpAndPaymentView signUpAndPaymentView=new SignUpAndPaymentView() {
        @Override
        public void doOrderAgreement(NormalModel orderAgreementData) {

        }


        @Override
        public void doUploadSign(NormalModel uploadSignData) {
            if("0".equals(uploadSignData.getCode())){
                ToastUtils.show(getContext(),"上传成功");
            }else if("1010".equals(uploadSignData.getCode())){
                ToastUtils.show(getContext(),"签名图片为空");
            }else{
                ToastUtils.show(getContext(),"未登录");
            }
        }

        @Override
        public void showMessage(String message) {

        }
    };

    @OnClick({R.id.back,R.id.btn_sign_payment,R.id.choose_sign_contract})
    void onClick(View view){
        switch (view.getId()){
            case R.id.choose_sign_contract:
                if(chooseState){
                    //设置为选中图片
                    mChooseSignContract.setImageResource(R.drawable.btn_choose_blue);
                    //判断是否同意协议，如果没有同意协议就不可以点击选择支付
                    mSignAndPayment.setBackgroundResource(R.drawable.button_react);
                    mSignAndPayment.setClickable(true);
                    chooseState=false;
                }else{
                    mChooseSignContract.setImageResource(R.drawable.btn_choose_gray);
                    mSignAndPayment.setBackgroundResource(R.drawable.button_gray_react);
                    mSignAndPayment.setClickable(false);
                    chooseState=true;
                }
                break;
            case R.id.btn_sign_payment:
                //弹出签名对话框
                dialogShow();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    //初始化WebView
    private void initWebView() {
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
            new Object() {
                public void setLoadWithOverviewMode(boolean overview) {
                    settings.setLoadWithOverviewMode(overview);
                }
            }.setLoadWithOverviewMode(true);
        }

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                mWebView.postUrl("http://123.207.70.168/shareman/order/getAgreement", postData.getBytes());

            }
        });
    }


    //设置选择图片弹出框
    public void dialogShow(){
        dialog = new Dialog(this,R.style.ShopTabDialog);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.write_sign_dialog_layout, null);
        //初始化控件
        mClear = inflate.findViewById(R.id.tv_clear);
        mCancel=inflate.findViewById(R.id.img_cancel);
        mSave = inflate.findViewById(R.id.tv_save);
        mLine= inflate.findViewById(R.id.lineview);
        //初始化lineView
        mLine.setPenColor(Color.BLACK);
        mLine.setBackColor(Color.WHITE);
        mLine.setPaintWidth(10);

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清除签名
                mLine.clear();
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //上传签名，并且改变按钮状态，关闭签名界面
                if (mLine.getTouched()) {
                    try {
                        mLine.save("/sdcard/qm.png", true, 10);
                        dialog.dismiss();
                        File file=new File(path);
//                        signUpAndPaymentPresenter.uploanSign(phoneNumber,token,String.valueOf(orderId),file);
                        signUpAndPaymentPresenter.uploadSign("15701236749","fcfcf1962e40afc99ea1e84a01e6c001",String.valueOf(orderId),file);
                        JumpUtil.overlay(getContext(),PaymentWayActivity.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtils.show(getContext(),"您还没有签名哦...");
                }
            }
        });
        //关闭签名界面
        mCancel.setOnClickListener(new View.OnClickListener() {
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
}

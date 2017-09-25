package com.youhu.shareman.shareman.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;

/**
 * Created by Touch on 2017/9/2.
 */

public class AgreementDialog extends Dialog {

    private TextView mTitle;
    private WebView mWebView;
    private Button mCancel;
    private String postData;
    private String phoneNumber;
    private String token;
    private int orderId;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    private onCancelOnclickListener cancelOnclickListener;//微信按钮被点击了的监听器


    public AgreementDialog(@NonNull Context context) {
        super(context,R.style.ActionSheetDialogStyle);
    }

    public void setCancelOnclickListener(onCancelOnclickListener onCancelOnclickListener) {

        this.cancelOnclickListener = onCancelOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_sale_dialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        mTitle=findViewById(R.id.tv_pre_title);
        mWebView=findViewById(R.id.wb_pre_sale);
        mCancel=findViewById(R.id.btn_cancel);

        //设置标题
        mTitle.setText("电子协议");

        //webView发送的请求
//        postData="phoneNumber="+phoneNumber+"&token="+token+"&orderId="+orderId;
        postData="phoneNumber=15701236749&token=4f4f5ccb9f7ad689ba2552c2c0d25703&orderId="+orderId;

        //初始化webView
        initWebView();
    }

    private void initData() {
    }

    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });

    }


    /**
     * 设置关闭被点击的接口
     */
    public interface onCancelOnclickListener {
        public void onCancelClick();
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
}

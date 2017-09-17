package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youhu.shareman.shareman.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/24.
 */

public class PaymentWayActivity extends BaseActivity implements BaseView {

    @BindView(R.id.choose_voucher)
    LinearLayout mChooseVoucher;
    @BindView(R.id.rl_alipay)
    RelativeLayout mRlalipay;
    @BindView(R.id.rl_wechatpayment)
    RelativeLayout mRlWechatpayment;
    @BindView(R.id.image_choose_alipay)
    ImageView mChooseAlipay;
    @BindView(R.id.image_choose_wechatpayment)
    ImageView mChooseWechatpayment;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;

    //付款方式（0为支付宝，1为微信）,默认支付宝支付
    private int payWay=0;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_payment_way);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文
        setContext(this);
        mTitle.setText("签约支付");

        //默认选中支付宝
        mChooseAlipay.setImageResource(R.drawable.btn_choose_blue);

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

    @OnClick({R.id.back,R.id.choose_voucher,R.id.rl_alipay,R.id.rl_wechatpayment,R.id.btn_pay})
    void onClick(View view){
        switch (view.getId()){
            case R.id.choose_voucher:
                JumpUtil.overlay(this,VoucherActivity.class);
                break;
            case R.id.rl_alipay:
                //选中支付宝支付
                mChooseAlipay.setImageResource(R.drawable.btn_choose_blue);
                mChooseWechatpayment.setImageResource(R.drawable.btn_choose_gray);
                payWay=0;
                break;
            case R.id.rl_wechatpayment:
                mChooseWechatpayment.setImageResource(R.drawable.btn_choose_blue);
                mChooseAlipay.setImageResource(R.drawable.btn_choose_gray);
                payWay=1;
                break;
            case R.id.btn_pay:
                //根据选择不同的支付方式跳转不同支付界面
                if(payWay==0){
                    //选择支付宝支付
                    ToastUtils.show(this,"跳转至支付宝界面");
                }else{
                    //选择微信支付
                    ToastUtils.show(this,"跳转至微信界面");
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}

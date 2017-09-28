package com.youhu.shareman.shareman.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.PayWayModel;
import com.youhu.shareman.shareman.presentercoml.PaymentWayPresenter;
import com.youhu.shareman.shareman.util.PayResult;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.util.ToastUtils;
import com.youhu.shareman.shareman.view.PaymentWayView;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/24.
 */

public class PaymentWayActivity extends BaseActivity {

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
    @BindView(R.id.tv_pay_price)
    TextView mPayPrice;
    @BindView(R.id.tv_voucher)
    TextView mVoucher;

    //付款方式（0为支付宝，1为微信）,默认支付宝支付
    private int payWay=0;
    private int orderId;
    private int price;
    private int amount;
    PaymentWayPresenter paymentWayPresenter=new PaymentWayPresenter();
    private static final int RESTCODE=1111;
    private String phoneNumber;
    private String token;
    private String info;
    private static final int SDK_PAY_FLAG = 1;

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

        //用户账号
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        //获取支付订单ID
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        orderId = bundle.getInt("payOrderId");

        paymentWayPresenter.onStart();
        paymentWayPresenter.attachView(paymentWayView);
        paymentWayPresenter.getPayInfo(phoneNumber,token,orderId);

        //不使用微信支付
        mRlWechatpayment.setVisibility(View.GONE);

        //默认选中支付宝
        mChooseAlipay.setImageResource(R.drawable.btn_choose_blue);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }


    //显示返回的数据
    PaymentWayView paymentWayView=new PaymentWayView() {
        @Override
        public void doGetPayPrice(BaseData<PayWayModel> payData) {
            //更新UI
            mPayPrice.setText("¥ "+payData.getData().getTotalPrice());
            mVoucher.setText("可使用"+payData.getData().getCount()+"张优惠券");
        }

        @Override
        public void doGetOrderInfo(BaseData<String> orderInfoData) {
            /**
             * 支付宝
             */
            final String orderInfo=orderInfoData.getData();
            Runnable payRunnable = new Runnable() {
                @Override
                public void run() {
                    PayTask alipay = new PayTask(PaymentWayActivity.this);
                    Map<String, String> result = alipay.payV2(orderInfo,true);

                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();

        }

        @Override
        public void showMessage(String message) {

        }
    };

    @OnClick({R.id.back,R.id.choose_voucher,R.id.rl_alipay,R.id.rl_wechatpayment,R.id.btn_pay})
    void onClick(View view){
        switch (view.getId()){
            case R.id.choose_voucher:
                //跳转至选择优惠券界面,传递订单ID，获取返回数据
                Intent intent=new Intent();
                intent.putExtra("payId",orderId);
                intent.setClass(PaymentWayActivity.this,VoucherActivity.class);
                startActivityForResult(intent,RESTCODE);
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
                    paymentWayPresenter.getPayOrderInfo(phoneNumber,token,1,orderId);
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


    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PaymentWayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PaymentWayActivity.this, payResult.getMemo(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                default:
                    break;
            }
        };


    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            price=data.getExtras().getInt("price");
            amount=data.getExtras().getInt("voucherAmount");
            mPayPrice.setText("¥ "+price);
            mVoucher.setText("- "+amount);
        }
    }
}

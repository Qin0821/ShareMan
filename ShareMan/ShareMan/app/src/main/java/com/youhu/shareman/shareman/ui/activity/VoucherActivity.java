package com.youhu.shareman.shareman.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.VoucherAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.ChooseVoucherModel;
import com.youhu.shareman.shareman.model.data.VoucherModel;
import com.youhu.shareman.shareman.presentercoml.VoucherPresenter;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.view.VoucherView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/7.
 */

public class VoucherActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.list_voucher)
    ListView mListVoucher;
    @BindView(R.id.img_voucher_bg)
    ImageView mVoucherBg;

    VoucherPresenter voucherPresenter=new VoucherPresenter();
    private VoucherAdapter mVoucherAdapter;
    private List<VoucherModel> datas;
    private String phoneNumber;
    private String token;
    private int orderId;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_voucher);
        super.initBind();
    }

    @Override
    protected void initUI() {
        setContext(this);
        mTitle.setText("代金券");

        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        //获取从上一页面跳转传递的订单号
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            orderId = bundle.getInt("payId");
        }

        voucherPresenter.onCreate();
        voucherPresenter.attachView(voucherView);
        voucherPresenter.getVoucher(phoneNumber,token);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    VoucherView voucherView=new VoucherView() {
        @Override
        public void doGetVoucher(final BaseData<List<VoucherModel>> voucherData) {
            //初始化数据
            datas=new ArrayList<>();
            datas=voucherData.getData();
            if(datas==null){
                mVoucherBg.setVisibility(View.VISIBLE);
            }else{
                mVoucherAdapter=new VoucherAdapter();
                mVoucherAdapter.setContext(getContext());
                mVoucherAdapter.setDatas(datas);
                mListVoucher.setAdapter(mVoucherAdapter);
                mListVoucher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //请求使用优惠券接口
                        voucherPresenter.useVoucher(phoneNumber,token,String.valueOf(voucherData.getData().get(i).getId()),String.valueOf(orderId));
                    }
                });
            }
        }

        @Override
        public void doUseVoucher(BaseData<ChooseVoucherModel> useVoucherData) {
            Intent intent=new Intent();
            Bundle bundle=intent.getExtras();
            bundle.putInt("price",useVoucherData.getData().getTotalPrice());
            bundle.putInt("voucherAmount",useVoucherData.getData().getAmount());
            //发送返回码
            setResult(1111,intent);
            //关闭当前界面
            finish();
        }

        @Override
        public void showMessage(String message) {

        }
    };


    @OnClick({R.id.back})
    void onClici(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

}

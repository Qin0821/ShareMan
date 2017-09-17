package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.util.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/9/4.
 */

public class RecycleOrderActivity extends BaseActivity implements BaseView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;


    @Override
    protected void initBind() {
        setContentView(R.layout.activity_recycle_order);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文
        setContext(this);
        mTitle.setText("回收下单");


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

    @OnClick({R.id.back,R.id.btn_submit_order})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_submit_order:
                JumpUtil.overlay(this,SubmitSuccessfulActivity.class);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}

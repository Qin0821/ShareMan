package com.youhu.shareman.shareman.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/8.
 */

public class ServiceOrderActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_service_type);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文，标题
        setContext(this);
        mTitle.setText("维修订单");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    @OnClick(R.id.back)
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}

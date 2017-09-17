package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.MainActivity;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.util.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/9/4.
 */

public class SubmitSuccessfulActivity extends BaseActivity implements BaseView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.btn_submit_successful)
    Button mSubmitSuccessful;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_submit_successful);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文
        setContext(this);
        mTitle.setText("");

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

    @OnClick({R.id.back,R.id.btn_submit_successful})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_submit_successful:
                JumpUtil.overlay(this, MainActivity.class);
                break;
            case  R.id.back:
                finish();
                break;
        }
    }
}

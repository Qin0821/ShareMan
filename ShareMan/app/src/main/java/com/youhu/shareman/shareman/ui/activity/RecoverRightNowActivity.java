package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.Button;
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

public class RecoverRightNowActivity extends BaseActivity implements BaseView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.tv_reback_assess)
    TextView mRebackAssess;
    @BindView(R.id.btn_recover_right_now)
    Button mRecoverRightNow;


    @Override
    protected void initBind() {
        setContentView(R.layout.activity_recover_right_now);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文
        setContext(this);
        mTitle.setText("回收");

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

    @OnClick({R.id.back,R.id.tv_reback_assess,R.id.btn_recover_right_now})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_reback_assess:
                //重新估价,清空之前的选项卡
                JumpUtil.overlay(this,RecoverActivity.class);
                break;
            case R.id.btn_recover_right_now:
                JumpUtil.overlay(this,RecycleOrderActivity.class);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}

package com.youhu.shareman.shareman.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.ui.widget.SecurityCodeView;
import com.youhu.shareman.shareman.util.CountDownTimerUtils;
import com.youhu.shareman.shareman.util.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/15.
 */

public class ChangeNumberActivity extends BaseActivity implements BaseView,SecurityCodeView.InputCompleteListener {

    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.tv_phone_number)
    TextView mTvPhoneNumber;
    @BindView(R.id.scv_edittext)
    SecurityCodeView mScvEditText;
    @BindView(R.id.bt_get_securityCode)
    TextView mGetSecurityCode;
    @BindView(R.id.bt_next)
    Button mBtNext;
    @BindView(R.id.tv_error_message)
    TextView mErroeMessage;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_change_number);
        super.initBind();
    }

    @Override
    protected void initUI() {
        mTitle.setText("修改手机");

        mGetSecurityCode.setEnabled(true);

        //设置输入验证码监听
        setSecurityCodeListener();
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

    @OnClick({R.id.back,R.id.bt_next,R.id.bt_get_securityCode})
    void onClick(View view){
        switch (view.getId()){
            case R.id.bt_next:
                JumpUtil.overlay(this,NewPhoneNumberActivity.class);
                break;
            case R.id.bt_get_securityCode:
                //获取验证码

                //倒计时60秒
                CountDownTimerUtils count = new CountDownTimerUtils(mGetSecurityCode, 60000, 1000);
                count.start();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void setSecurityCodeListener() {
        mScvEditText.setInputCompleteListener(this);
    }

    @Override
    public void inputComplete() {
        if (!mScvEditText.getEditContent().equals("1234")) {
//            mBtNext.setText("验证码输入错误");
//            mBtNext.setTextColor(Color.RED);
            mErroeMessage.setVisibility(View.VISIBLE);
            //如果验证码输入错误就清空
//            mScvEditText.clearEditText();
        }else{
            mErroeMessage.setVisibility(View.INVISIBLE);
            mBtNext.setEnabled(true);
            mBtNext.setText("下一步");
            mBtNext.setTextColor(Color.WHITE);
            mBtNext.setBackgroundResource(R.drawable.button_react);
        }
    }

    @Override
    public void deleteContent(boolean isDelete) {
        if (isDelete){
            mBtNext.setText("下一步");
            mBtNext.setTextColor(Color.WHITE);
            mBtNext.setBackgroundResource(R.drawable.button_gray_react);
            mBtNext.setEnabled(false);
        }
    }
}

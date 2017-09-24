package com.youhu.shareman.shareman.ui.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NewNumberLoginCodeModel;
import com.youhu.shareman.shareman.presentercoml.NewPhoneNumberPresenter;
import com.youhu.shareman.shareman.view.NewPhoneNumberView;
import com.youhu.shareman.shareman.ui.widget.SecurityCodeView;
import com.youhu.shareman.shareman.util.CheckUtils;
import com.youhu.shareman.shareman.util.CountDownTimerUtils;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/15.
 */

public class NewPhoneNumberActivity extends BaseActivity implements SecurityCodeView.InputCompleteListener {

    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.scv_edittext)
    SecurityCodeView mScvEditText;
    @BindView(R.id.bt_get_securityCode)
    TextView mGetSecurityCode;
    @BindView(R.id.bt_next)
    Button mBtNext;
    @BindView(R.id.tv_error_message)
    TextView mErroeMessage;

    private String phoneNumber;
    private String token;
    private String newPhoneNumber;
    //修改手机号码接口
    NewPhoneNumberPresenter newPhoneNumberPresenter=new NewPhoneNumberPresenter();

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_new_phone_number);
        super.initBind();
    }

    @Override
    protected void initUI() {
        setContext(this);
        mTitle.setText("新手机号码");

        //设置输入验证码监听
        setSecurityCodeListener();
        //监听输入文字变化
        setEditTextChangeLisenter();

        newPhoneNumberPresenter.onCreate();
        newPhoneNumberPresenter.attachView(newPhoneNumberView);

        //获取已登录的手机号
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token=SharedPreferencesUtils.getToken(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    //修改手机号码返回结果
    NewPhoneNumberView newPhoneNumberView=new NewPhoneNumberView() {
        @Override
        public void doChangePhoneNumber(BaseData<String> phoneNumberData) {
            //提示验证码信息
            ToastUtils.show(getContext(),phoneNumberData.getMessage());
        }

        @Override
        public void doNewPhoneNumber(BaseData<NewNumberLoginCodeModel> newPhoneNumberData) {
            //保存新手机的账号和token
            CheckUtils.saveLogin(getContext(),newPhoneNumberData.getData().getNewPhoneNumber(),newPhoneNumberData.getData().getToken());

            if(newPhoneNumberData.getCode()==0){
                mBtNext.setEnabled(true);
                mBtNext.setText("下一步");
                mBtNext.setTextColor(Color.WHITE);
                mBtNext.setBackgroundResource(R.drawable.button_react);
                ToastUtils.show(getContext(),newPhoneNumberData.getMessage());
            }else{

                mErroeMessage.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void showMessage(String message) {

        }
    };

    @OnClick({R.id.back,R.id.bt_next,R.id.bt_get_securityCode})
    void onClick(View view){
        switch (view.getId()){
            case R.id.bt_get_securityCode:
                //获取新手机号
                newPhoneNumber=mEtPhoneNumber.getText().toString();
                if(CheckUtils.isMobileNO(newPhoneNumber)){
                    newPhoneNumberPresenter.doChangePhoneNumber(phoneNumber,token,newPhoneNumber);
                    //倒计时60秒
                    CountDownTimerUtils count = new CountDownTimerUtils(mGetSecurityCode, 60000, 1000);
                    count.start();
                }else {
                    ToastUtils.show(getContext(),"输入的手机号码有误！");
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.bt_next:
                JumpUtil.overlay(this,UserInfoActivity.class);
                break;
        }
    }

    private void setSecurityCodeListener() {
        mScvEditText.setInputCompleteListener(this);
    }

    //请求验证码接口调用
    @Override
    public void inputComplete() {
        newPhoneNumberPresenter.doNewPhoneNumber(phoneNumber,token,newPhoneNumber,mScvEditText.getEditContent());
//        if (!mScvEditText.getEditContent().equals("1234")) {
////            mBtNext.setText("验证码输入错误");
////            mBtNext.setTextColor(Color.RED);
////            mScvEditText.clearEditText();
//            mErroeMessage.setVisibility(View.VISIBLE);
//        }else{
//            mBtNext.setEnabled(true);
//            mBtNext.setText("下一步");
//            mBtNext.setTextColor(Color.WHITE);
//            mBtNext.setBackgroundResource(R.drawable.button_react);
//        }
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

    //监听手机号码是否正确
    private void setEditTextChangeLisenter() {
        mEtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                newPhoneNumber=mEtPhoneNumber.getText().toString();
                if(CheckUtils.isMobileNO(newPhoneNumber)){

                }else{
                    if(newPhoneNumber.length()==11){
                        ToastUtils.show(getContext(),"您输入的手机格式有误！");
                    }
                }
            }
        });
    }
}

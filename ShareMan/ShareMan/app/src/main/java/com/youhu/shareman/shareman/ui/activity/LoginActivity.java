package com.youhu.shareman.shareman.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.presentercoml.LoginPresenter;
import com.youhu.shareman.shareman.ui.view.LoginView;
import com.youhu.shareman.shareman.util.CheckUtils;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youhu.shareman.shareman.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/28.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.img_login_close)
    ImageView mLoginClose;
    @BindView(R.id.et_login_number)
    EditText mLoginNumber;
    @BindView(R.id.btn_login_next)
    Button mLoginNext;
    //开发者使用按钮
    @BindView(R.id.jump)
    ImageView mJump;

    //获取手机号码
    private String phoneNumber;
    LoginPresenter presenter=new LoginPresenter(this);

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_login);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文，标题
        setContext(this);

        //监听输入文字变化
        setEditTextChangeLisenter();

        presenter.onCreate();
        presenter.attachView(loginView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }


    @OnClick({R.id.btn_login_next,R.id.img_login_close,R.id.jump})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login_next:
                //跳转至验证码界面
                //获取输入的手机号码
                phoneNumber=mLoginNumber.getText().toString();
                //验证手机号码是否符合规定
                if(CheckUtils.isMobileNO(phoneNumber)){
                    Intent intent = new Intent(this,LoginCodeActivity.class);
                    intent.putExtra("phoneNumber",phoneNumber);
                    //获取验证码
                    presenter.getCode(phoneNumber);
                    startActivityForResult(intent,1);
                }else{
                    ToastUtils.show(this,"您的手机号码有误！");
                }
                break;
            //关闭登录界面
            case R.id.img_login_close:
                finish();
                break;
            case R.id.jump:
                JumpUtil.overlay(this,UserInfoActivity.class);
                break;
        }
    }

    private LoginView loginView=new LoginView() {
        @Override
        public void onSuccess(String message) {
            ToastUtils.show(getContext(),message);
        }

        @Override
        public void showMessage(String message) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }


    private void setEditTextChangeLisenter() {
        mLoginNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phoneNumber=mLoginNumber.getText().toString();
                if(CheckUtils.isMobileNO(phoneNumber)){
                    mLoginNext.setBackgroundResource(R.drawable.button_react);
                }else{
                    mLoginNext.setBackgroundResource(R.drawable.button_gray_react);
                    if(phoneNumber.length()==11){
                        ToastUtils.show(getContext(),"您输入的手机格式有误！");
                    }
                }
            }
        });
    }

}

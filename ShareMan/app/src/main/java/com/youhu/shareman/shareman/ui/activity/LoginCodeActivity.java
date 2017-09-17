package com.youhu.shareman.shareman.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;
import com.youhu.shareman.shareman.presentercoml.LoginCodePresenter;
import com.youhu.shareman.shareman.ui.view.LoginCodeView;
import com.youhu.shareman.shareman.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/28.
 */

public class LoginCodeActivity extends BaseActivity {

    @BindView(R.id.img_login_close)
    ImageView mLoginClose;
    @BindView(R.id.et_login_code)
    EditText mLoginCode;
    @BindView(R.id.btn_login)
    Button mLogin;
    @BindView(R.id.tv_error_message)
    TextView mErrorMessage;

    //获取登录状态
    private static String Tag="LoginPresenter";
    LoginCodePresenter presenter=new LoginCodePresenter(this);
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_login_code);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文，标题
        setContext(this);
        //存储登录状态
        sp= getApplication().getSharedPreferences("LoginState",0);

        presenter.onCreate();
        presenter.attachView(loginCodeView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    @OnClick({R.id.btn_login,R.id.img_login_close})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                //记录登录状态
                String code=mLoginCode.getText().toString();
                Intent intent=getIntent();
                String phoneNumber=intent.getStringExtra("phoneNumber");
                ToastUtils.show(this,""+phoneNumber+""+code);

                if(TextUtils.isEmpty(code)){
                    ToastUtils.show(this,"验证码不能为空！");
                }else{
                    presenter.dologin(phoneNumber,code);
                }

                break;
            //关闭登录界面
            case R.id.img_login_close:
                //返回输入手机号码界面
                finish();
                break;
        }
    }

    private LoginCodeView loginCodeView=new LoginCodeView() {
        @Override
        public void onLoginResult(BaseData<LoginCodeModel> data) {
            //更新UI
            if(data.getCode()==0){
                //登录成功
                editor=sp.edit().putBoolean("isLogin",true);
                editor.commit();
                setResult(RESULT_OK);
                finish();
                ToastUtils.show(getContext(),"登录成功");
            }else{
                //登录失败
                ToastUtils.show(getContext(),"登录失败");
                mErrorMessage.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void showMessage(String message) {

        }
    };
}

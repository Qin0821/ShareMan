package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presentercoml.FeedBackPresenter;
import com.youhu.shareman.shareman.view.FeedBackView;
import com.youhu.shareman.shareman.ui.widget.CustomEditText;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/19.
 */

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.et_feedback)
    CustomEditText mCustomEditText;
    @BindView(R.id.et_contact_way)
    EditText mContactWay;


    FeedBackPresenter feedBackPresenter=new FeedBackPresenter();
    private String phoneNumber;
    private String token;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_feedback);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文，标题
        setContext(this);
        mTitle.setText("打小报告");

        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        feedBackPresenter.onCreate();
        feedBackPresenter.attachView(feedBackView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }


    //提交反馈意见接口返回
    FeedBackView feedBackView=new FeedBackView() {
        @Override
        public void doSendAdvice(NormalModel normalModel) {
            ToastUtils.show(getContext(),normalModel.getMessage());
        }

        @Override
        public void showMessage(String message) {

        }
    };

    @OnClick({R.id.back,R.id.et_feedback,R.id.et_contact_way,R.id.submit_feedback})
    void onClick(View view){
        switch (view.getId()){
            case R.id.submit_feedback:
                //将反馈内容上传到服务器
                String advice=mCustomEditText.getText().toString();
                String contactWay=mContactWay.getText().toString();
                feedBackPresenter.doSendAdvice(phoneNumber,token,advice,contactWay);
//                feedBackPresenter.doSendAdvice("15701236749","fcfcf1962e40afc99ea1e84a01e6c001",advice,contactWay);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}

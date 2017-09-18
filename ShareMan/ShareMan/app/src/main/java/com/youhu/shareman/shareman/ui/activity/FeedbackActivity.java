package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/19.
 */

public class FeedbackActivity extends BaseActivity implements BaseView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;

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

    @OnClick({R.id.back,R.id.et_feedback,R.id.et_contact_way,R.id.submit_feedback})
    void onClick(View view){
        switch (view.getId()){
            case R.id.submit_feedback:
                //将反馈内容上传到服务器
                ToastUtils.show(this,"提交");
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}

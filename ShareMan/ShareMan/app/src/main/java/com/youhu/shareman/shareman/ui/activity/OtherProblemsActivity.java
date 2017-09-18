package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.RecoverChooseListAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.util.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/9/5.
 */

public class OtherProblemsActivity extends BaseActivity implements BaseView {

    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.lv_recover)
    ListView mListview;
    @BindView(R.id.btn_assess_right_now)
    Button mAssessRightNow;

    private RecoverChooseListAdapter adapter;
    private String[] data={"充电不正常","机身弯曲","触摸功能不正常","无法正常开机","通话不正常","拍照摄像不正常","机身进水或受潮","iCloud无法注销","指南针功能不正常"};

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_other_problems);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文
        setContext(this);
        mTitle.setText("其它问题（多选）");

        adapter=new RecoverChooseListAdapter();
        adapter.setContext(this);
        adapter.setDatas(data);
        mListview.setAdapter(adapter);
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

    @OnClick({R.id.back,R.id.btn_assess_right_now})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_assess_right_now:
                JumpUtil.overlay(this,RecoverRightNowActivity.class);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}

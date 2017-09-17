package com.youhu.shareman.shareman.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


/**
 * Created by Touch on 2017/8/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    //定义一个Presenter用于解绑当前持有的View
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBind();
        initUI();
        initData();
        fillData();
    }

    /**
     * 界面绑定方法，在不需要进行界面绑定的Activity,进行空实现即可
     */
    protected void initBind() {
        ButterKnife.bind(this);
    }
    /**
     * 界面初始化
     */
    protected abstract void initUI();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    /**
     * 数据填充
     */
    protected abstract void fillData();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.AdapterView;
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

public class ShellBordeActivity extends BaseActivity implements BaseView {

    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.lv_recover)
    ListView mListview;

    private RecoverChooseListAdapter adapter;
    private String[] data={"外壳完好","外壳有划痕","外壳有磕碰/掉漆"};

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_phone_list);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文
        setContext(this);
        mTitle.setText("外壳边框");

        adapter=new RecoverChooseListAdapter();
        adapter.setContext(this);
        adapter.setDatas(data);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JumpUtil.overlay(getContext(),ScreenLayoutActivity.class);
            }
        });
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

    @OnClick({R.id.back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}

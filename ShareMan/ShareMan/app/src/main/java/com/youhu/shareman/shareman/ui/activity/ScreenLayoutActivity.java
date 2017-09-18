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

public class ScreenLayoutActivity extends BaseActivity implements BaseView {

    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.lv_recover)
    ListView mListview;

    private RecoverChooseListAdapter adapter;
    private String[] data={"屏幕无法显示","屏幕显示正常","屏幕有划痕","屏幕有缺角/碎裂/内屏损伤","亮坏点/色差/轻微发黄","色斑/漏液/错乱/严重老化"};

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_phone_list);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文
        setContext(this);
        mTitle.setText("屏幕外观");

        adapter=new RecoverChooseListAdapter();
        adapter.setContext(this);
        adapter.setDatas(data);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JumpUtil.overlay(getContext(),DisassembleRepairActivity.class);
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

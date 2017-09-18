package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.MessageAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.data.MessageInfo;
import com.youhu.shareman.shareman.util.JumpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/9/2.
 */

public class MessageActivity extends BaseActivity implements BaseView {

    @BindView(R.id.lv_message)
    ListView mLvMessage;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;

    private List<MessageInfo> data;
    private MessageAdapter adapter;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_message);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文，标题
        setContext(this);
        mTitle.setText("消息");

        adapter=new MessageAdapter();
        data=new ArrayList<MessageInfo>();
        data.add(new MessageInfo("新来的朋友，A君送你一张新机优惠券，赶紧行动起来吧！","优惠券传送门"));
        data.add(new MessageInfo("小伙子，我看你骨骼惊奇，送你一本获得新手机的武林秘籍，赶紧打开看看吧！","优惠券传送门"));
        data.add(new MessageInfo("老朋友，B君送你一张新机优惠券，赶紧行动起来吧！","优惠券传送门"));

        adapter.setDatas(data);
        adapter.setContext(this);
        mLvMessage.setAdapter(adapter);
        mLvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                JumpUtil.overlay(getContext(),VoucherActivity.class);
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

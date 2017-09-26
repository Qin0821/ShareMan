package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.MessageAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.MessageModel;
import com.youhu.shareman.shareman.presentercoml.MessagePresenter;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youhu.shareman.shareman.view.MessageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/9/2.
 */

public class MessageActivity extends BaseActivity {

    @BindView(R.id.lv_message)
    ListView mLvMessage;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;

    private List<MessageModel> data;
    private MessageAdapter adapter;
    MessagePresenter messagePresenter=new MessagePresenter();


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

        messagePresenter.onCreate();
        messagePresenter.attachView(messageView);
        messagePresenter.getMainMessage();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    MessageView messageView=new MessageView() {
        @Override
        public void doMessage(BaseData<List<MessageModel>> messageData) {
            adapter=new MessageAdapter();
            data=new ArrayList<MessageModel>();
            data=messageData.getData();

            adapter.setDatas(data);
            adapter.setContext(getContext());
            mLvMessage.setAdapter(adapter);
            mLvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    JumpUtil.overlay(getContext(),VoucherActivity.class);
                }
            });
        }

        @Override
        public void showMessage(String message) {

        }
    };

    @OnClick({R.id.back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}

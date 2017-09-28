package com.youhu.shareman.shareman.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.youhu.shareman.shareman.R;

/**
 * Created by Touch on 2017/9/2.
 */

public class ShareDialog extends Dialog {

    private ImageView mShareWeChat;
    private ImageView mShareFriendSpace;
    private onWeChatOnclickListener wechatOnclickListener;//微信按钮被点击了的监听器
    private onFriendSpaceOnclickListener friendSpaceOnclickListener;//朋友圈按钮被点击了的监听器

    /**
     * 设置微信被点击的接口
     */
    public interface onWeChatOnclickListener {
        public void onWechatClick();
    }

    /**
     * 设置朋友圈被点击的接口
     */
    public interface onFriendSpaceOnclickListener {
        public void onFriendSpaceClick();
    }

    public ShareDialog(@NonNull Context context) {
        super(context);
    }

    public void setWechatOnclickListener(onWeChatOnclickListener onWeChatOnclickListener) {

        this.wechatOnclickListener = onWeChatOnclickListener;
    }

    public void setFriendSpaceOnclickListener(onFriendSpaceOnclickListener onFriendSpaceOnclickListener) {

        this.friendSpaceOnclickListener = onFriendSpaceOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_dialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        mShareWeChat=findViewById(R.id.share_weixin);
        mShareFriendSpace=findViewById(R.id.share_pengyouquan);
    }

    private void initData() {
    }

    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        mShareWeChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wechatOnclickListener != null) {
                    wechatOnclickListener.onWechatClick();
                }
            }
        });

        //设置清除按钮被点击后，向外界提供监听
        mShareFriendSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (friendSpaceOnclickListener != null) {
                    friendSpaceOnclickListener.onFriendSpaceClick();
                }
            }
        });
    }



}

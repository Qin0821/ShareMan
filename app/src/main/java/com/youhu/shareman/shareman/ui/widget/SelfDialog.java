package com.youhu.shareman.shareman.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.youhu.shareman.shareman.R;

/**
 * Created by Touch on 2017/8/14.
 */

public class SelfDialog extends Dialog{

    //确定按钮
    private ImageView mClearNickname;
    private Button mChangeNicknameOk;
    private EditText mChangeNickName;

    //确定文本和取消文本的显示内容
    private String yesStr, clearStr;

    private onClearOnclickListener clearOnclickListener;//清除按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public SelfDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    public void setClearOnclickListener(String str, onClearOnclickListener onClearOnclickListener) {
        if (str != null) {
            clearStr = str;
        }
        this.clearOnclickListener = onClearOnclickListener;
    }

    public EditText getmChangeNickName() {
        return mChangeNickName;
    }

    public void setmChangeNickName(EditText mChangeNickName) {
        this.mChangeNickName = mChangeNickName;
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mydialog);
            //按空白处不能取消动画
            setCanceledOnTouchOutside(true);

            //初始化界面控件
            initView();
            //初始化界面数据
            initData();
            //初始化界面控件的事件
            initEvent();

        }

        /**
         * 初始化界面的确定和取消监听器
         */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        mChangeNicknameOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });

        //设置清除按钮被点击后，向外界提供监听
        mClearNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    clearOnclickListener.onClearClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果设置按钮的文字
        if (yesStr != null) {
            mChangeNicknameOk.setText(yesStr);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        mClearNickname=findViewById(R.id.clear_nickname);
        mChangeNickName=findViewById(R.id.et_change_nickname);
        mChangeNicknameOk = (Button) findViewById(R.id.change_nickname_ok);
    }


    /**
     * 设置确定按钮和清除被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onClearOnclickListener {
        public void onClearClick();
    }

}

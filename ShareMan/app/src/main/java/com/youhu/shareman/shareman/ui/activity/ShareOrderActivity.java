package com.youhu.shareman.shareman.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.ui.fragment.ApplyingFragment;
import com.youhu.shareman.shareman.ui.fragment.CandelFragment;
import com.youhu.shareman.shareman.ui.fragment.ShareingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/9/9.
 */

public class ShareOrderActivity extends FragmentActivity {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.feedback_frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.radiogroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.rb_flying)
    RadioButton mRbflying;
    @BindView(R.id.rb_shareing)
    RadioButton mRbshareing;
    @BindView(R.id.rb_canceled)
    RadioButton mRbcancel;


    private Fragment applyingfragment, shareingfragment, cancelfragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_order);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        /**
         * 拿到事务管理器并开启事务
         */
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        //上下文
        mTitle.setText("共享详情");

        //默认选择回收中
        mRadioGroup.check(R.id.rb_flying);
        if (applyingfragment == null) {
            applyingfragment = new ApplyingFragment();
            transaction.add(R.id.feedback_frameLayout, applyingfragment);
        } else {
            transaction.show(applyingfragment);
        }
        transaction.commit();
        //设置radiogroup监听
        mRadioGroup.setOnCheckedChangeListener(new MyRadioGroupListener());
    }

    @OnClick({R.id.back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }


    //radiogroup监听
    private class MyRadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            switch (checkedId) {
                case R.id.rb_flying:
                    //申请中fragment
                    /**
                     * 为了防止重叠，需要点击之前先移除其他Fragment
                     */
                    hideFragment(transaction);
                    applyingfragment = new ApplyingFragment();
                    transaction.replace(R.id.feedback_frameLayout, applyingfragment);
                    transaction.commit();
                    break;
                case R.id.rb_shareing:
                    //共享中fragment
                    hideFragment(transaction);
                    shareingfragment = new ShareingFragment();
                    transaction.replace(R.id.feedback_frameLayout, shareingfragment);
                    transaction.commit();
                    break;
                case R.id.rb_canceled:
                    //已取消fragment
                    hideFragment(transaction);
                    cancelfragment = new CandelFragment();
                    transaction.replace(R.id.feedback_frameLayout, cancelfragment);
                    transaction.commit();
                    break;
            }
        }
    }

    /*
     * 去除（隐藏）所有的Fragment
     * */
    private void hideFragment(FragmentTransaction transaction) {
        if (applyingfragment != null) {
            //transaction.hide(f1);隐藏方法也可以实现同样的效果，不过我一般使用去除
            transaction.remove(applyingfragment);
//            transaction.hide(applyingfragment);
        }
        if (shareingfragment != null) {
            //transaction.hide(f2);
            transaction.remove(shareingfragment);
//            transaction.hide(shareingfragment);

        }
        if (cancelfragment != null) {
            //transaction.hide(f3);
            transaction.remove(cancelfragment);
//            transaction.hide(cancelfragment);
        }
    }
}

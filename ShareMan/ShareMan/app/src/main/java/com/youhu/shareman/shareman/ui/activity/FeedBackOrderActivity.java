package com.youhu.shareman.shareman.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.ui.fragment.BaseFragment;
import com.youhu.shareman.shareman.ui.fragment.FragmentFactory;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/29.
 */

public class FeedBackOrderActivity extends BaseActivity implements BaseView {

    @BindView(R.id.feedback_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.rb_feedbacking)
    RadioButton mFeedbacking;
    @BindView(R.id.rb_finshed)
    RadioButton mFinished;
    @BindView(R.id.rb_canceled)
    RadioButton mCanceled;
    @BindView(R.id.radiogroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;


    private MyAdapter mAdapter;


    @Override
    protected void initBind() {
        setContentView(R.layout.activity_feedback_order);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文，标题
        setContext(this);
        mTitle.setText("回收订单");

        mViewPager.setPageMargin((int)getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));
        //默认选择回收中
        mRadioGroup.check(R.id.rb_feedbacking);
        //底部按钮监听
        mRadioGroup.setOnCheckedChangeListener(new MyRadioGroupListener());
        initControoler();

    }

    private class MyRadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_feedbacking:
                    mViewPager.setCurrentItem(0);
                    //更改标题
                    mTitle.setText("回收订单");
                    break;
                case R.id.rb_finshed:
                    mViewPager.setCurrentItem(1);
                    mTitle.setText("完成订单");
                    break;
                case R.id.rb_canceled:
                    mViewPager.setCurrentItem(2);
                    mTitle.setText("取消订单");
                    break;
            }
        }
    }

    private void initControoler() {
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mViewPager.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mViewPager.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return true;
            }
        });
        //viewpager页面和按钮联动
//        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0:
//                        mRadioGroup.check(R.id.rb_feedbacking);
//                        break;
//                    case 1:
//                        mRadioGroup.check(R.id.rb_finshed);
//                        break;
//                    case 2:
//                        mRadioGroup.check(R.id.rb_canceled);
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.getChartFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
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

    @OnClick({R.id.back,R.id.textView})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}

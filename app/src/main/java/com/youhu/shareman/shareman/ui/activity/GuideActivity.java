package com.youhu.shareman.shareman.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.youhu.shareman.shareman.MainActivity;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.MyViewPagerAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youth.banner.transformer.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/9/22.
 */

public class GuideActivity extends BaseActivity {

    @BindView(R.id.in_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.in_ll)
    LinearLayout mIn_ll;
    @BindView(R.id.iv_light_dots)
    ImageView mLight_dots;
    @BindView(R.id.bt_next)
    Button mBtn_next;


    private int mDistance;
    private ImageView mOne_dot;
    private ImageView mTwo_dot;
    private ImageView mThree_dot;
    private ImageView mFour_dot;
    private List<View> mViewList;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_guide);
        super.initBind();
    }

    @Override
    protected void initUI() {
        setContext(this);

        mViewList = new ArrayList<View>();
        LayoutInflater lf = getLayoutInflater().from(this);
        View view1 = lf.inflate(R.layout.we_indicator1, null);
        View view2 = lf.inflate(R.layout.we_indicator2, null);
        View view3 = lf.inflate(R.layout.we_indicator3, null);
        View view4 = lf.inflate(R.layout.we_indicator4, null);
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        mViewList.add(view4);

        mViewPager.setAdapter(new MyViewPagerAdapter(mViewList));
        addDots();
        moveDots();
        mViewPager.setPageTransformer(true,new DepthPageTransformer());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    @OnClick(R.id.bt_next)
    void click(View view){
        switch (view.getId()){
            case R.id.bt_next:
                JumpUtil.overlay(getContext(), MainActivity.class);
                finish();
                break;
        }
    }


    //增加小点
    private void addDots() {
        mOne_dot = new ImageView(this);
        mOne_dot.setImageResource(R.drawable.gray_dot);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 20, 0);
        mIn_ll.addView(mOne_dot, layoutParams);
        mTwo_dot = new ImageView(this);
        mTwo_dot.setImageResource(R.drawable.gray_dot);
        mIn_ll.addView(mTwo_dot, layoutParams);
        mThree_dot = new ImageView(this);
        mThree_dot.setImageResource(R.drawable.gray_dot);
        mIn_ll.addView(mThree_dot, layoutParams);
        mFour_dot = new ImageView(this);
        mFour_dot.setImageResource(R.drawable.gray_dot);
        mIn_ll.addView(mFour_dot, layoutParams);
        setClickListener();
    }

    //索引的点的移动效果
    private void moveDots() {
        mLight_dots.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获得两个圆点之间的距离
                mDistance = mIn_ll.getChildAt(1).getLeft() - mIn_ll.getChildAt(0).getLeft();
                mLight_dots.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //页面滚动时小白点移动的距离，并通过setLayoutParams(params)不断更新其位置
                float leftMargin = mDistance * (position + positionOffset)+20;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLight_dots.getLayoutParams();
                params.leftMargin = (int) leftMargin;
                mLight_dots.setLayoutParams(params);
                if(position==3){
                    mBtn_next.setVisibility(View.VISIBLE);
                }
                if(position!=3&&mBtn_next.getVisibility()==View.VISIBLE){
                    mBtn_next.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                //页面跳转时，设置小圆点的margin
                float leftMargin = mDistance * position;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLight_dots.getLayoutParams();
                params.leftMargin = (int) leftMargin;
                mLight_dots.setLayoutParams(params);
                if(position==3){
                    mBtn_next.setVisibility(View.VISIBLE);
                }
                if(position!=3&&mBtn_next.getVisibility()==View.VISIBLE){
                    mBtn_next.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setClickListener() {
        mOne_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });
        mTwo_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
            }
        });
        mThree_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(2);
            }
        });
        mFour_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(3);
            }
        });
    }
}

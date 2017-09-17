package com.youhu.shareman.shareman.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.RecyclingPagerAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.BrandData;
import com.youhu.shareman.shareman.presentercoml.BrandPresenter;
import com.youhu.shareman.shareman.ui.view.BrandView;
import com.youhu.shareman.shareman.ui.widget.ClipViewPager;
import com.youhu.shareman.shareman.ui.widget.ScalePageTransformer;
import com.youhu.shareman.shareman.util.JumpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/31.
 */

public class BrandActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.ll_type_choose)
    LinearLayout mTypeChoose;
    @BindView(R.id.clip_viewpager)
    ClipViewPager mViewPager;
    @BindView(R.id.image_detail)
    ImageView mImageDetail;

    private Bundle bundle;
    BrandPresenter presenter=new BrandPresenter();
    private TypeChooseAdapter mPagerAdapter=new TypeChooseAdapter(this);
//    private List<Integer> list = new ArrayList<>();
    private List<BrandData> list = new ArrayList<>();
//    private List<Integer> list_detail = new ArrayList<>();

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_brand);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文，标题
        setContext(this);
        presenter.onStart();
        presenter.attachView(brandView);
        Intent intent=getIntent();
        bundle =intent.getExtras();
        if(bundle!=null){
            switch (bundle.getInt("type_state")){
                case 0:
                    mTitle.setText("经济实惠");

                    break;
                case 1:
                    mTitle.setText("个性潮流");
                    break;
                case 2:
                    mTitle.setText("特惠活动");
                    break;
                case 11:
                    mTitle.setText("iPhone专区");
                    presenter.doBrandData("OPPO");
                    break;
                case 12:
                    mTitle.setText("VIVO专区");
                    presenter.doBrandData("OPPO");
                    break;
                case 13:
                    mTitle.setText("华为专区");
                    presenter.doBrandData("OPPO");
                    break;
                case 14:
                    mTitle.setText("OPPO专区");
                    presenter.doBrandData("OPPO");
                    break;
            }
        }else{
            mTitle.setText("专区");
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }


    private BrandView brandView=new BrandView() {
        @Override
        public void getBrandData(BaseData<List<BrandData>> baseData) {
            //设置数据
            if(baseData.getCode()==0){
                list=baseData.getData();
                //设置OffscreenPageLimit
                mViewPager.setOffscreenPageLimit(Math.min(list.size(), 5));
                mPagerAdapter.addAll(list);

                //初始化ClipViewPager
                initClipViewPager();

//                mImageDetail.setImageURI(Uri.parse(list.get(0).getBot_show()));
                Glide.with(getContext()).load(list.get(0).getTop_show()).error(R.drawable.pic_01).crossFade(300).into(mImageDetail);
            }else{
                //如果返回的数据不成功

            }
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

    private void initClipViewPager() {
        mViewPager.setPageTransformer(true, new ScalePageTransformer());

        mTypeChoose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //监听图片的滑动改变下面详情的图片
//                mImageDetail.setImageURI(Uri.parse(list.get(position).getBot_show()));
                Glide.with(getContext()).load(list.get(position).getBot_show()).error(R.drawable.pic_01).crossFade(300).into(mImageDetail);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setAdapter(mPagerAdapter);
    }

    public class TypeChooseAdapter extends RecyclingPagerAdapter {

        private final List<BrandData> mList;
        private final Context mContext;

        public TypeChooseAdapter(Context context) {
            mList = new ArrayList<>();
            mContext = context;
        }

        public void addAll(List<BrandData> list) {
            mList.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ImageView imageView = null;
            if (convertView == null) {
                imageView = new ImageView(mContext);
            } else {
                imageView = (ImageView) convertView;
            }

            //圆角图片
//            imageView.setImageURI(Uri.parse(mList.get(position).getTop_show()));
            Glide.with(getContext()).load(mList.get(position).getTop_show()).error(R.drawable.pic_01).crossFade(300).into(imageView);
//            Glide.with(getContext()).load(mList.get(position)).transform(new GlideRoundTransform(getContext(), 5)).into(imageView);
            imageView.setTag(position);
            //条目点击事件
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    JumpUtil.overlay(getContext(),ProductDetailActivity.class);
                }
            });
            return imageView;
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}

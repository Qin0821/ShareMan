package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.ui.activity.SignUpAndPaymentActivity;
import com.youhu.shareman.shareman.util.JumpUtil;

import java.util.List;

/**
 * Created by Touch on 2017/9/8.
 */

public class MyApplyingPagerAdapter extends PagerAdapter {

    private Context context;
    private List<View> viewList;//数据源

    public MyApplyingPagerAdapter(Context context, List<View> viewList) {
        this.context = context;
        this.viewList = viewList;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    //数据源的数目
    public int getCount() {
        return viewList.size();
    }


    //view是否由对象产生，官方写arg0==arg1即可
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;

    }


    //销毁一个页卡(即ViewPager的一个item)
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(viewList.get(position));
    }


    //对应页卡添加上数据
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));//千万别忘记添加到container
        View view=viewList.get(position);
        TextView loadingPay=view.findViewById(R.id.tv_loading_pay);
        loadingPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.overlay(context, SignUpAndPaymentActivity.class);
            }
        });
        return viewList.get(position);
    }
}

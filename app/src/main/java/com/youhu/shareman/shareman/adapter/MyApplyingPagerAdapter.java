package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;
import com.youhu.shareman.shareman.ui.activity.SignUpAndPaymentActivity;
import com.youhu.shareman.shareman.util.JumpUtil;

import java.util.List;

/**
 * Created by Touch on 2017/9/8.
 */

public class MyApplyingPagerAdapter extends PagerAdapter {

    private Context context;
    private List<View> viewList;//界面的个数
    private List<ShareOrderModel> data;

    public MyApplyingPagerAdapter(Context context, List<View> viewList) {
        this.context = context;
        this.viewList = viewList;
    }

    public List<ShareOrderModel> getData() {
        return data;
    }

    public void setData(List<ShareOrderModel> data) {
        this.data = data;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(viewList.get(position));//千万别忘记添加到container
        View view=viewList.get(position);
        //获取对应的控件设置数据
        TextView loadingPay=view.findViewById(R.id.tv_loading_pay);
        TextView orderCancel=view.findViewById(R.id.tv_order_cancel);
        TextView applyingPhoneName=view.findViewById(R.id.tv_applying_phone_name);
        TextView applyingPhonePrice=view.findViewById(R.id.tv_aplying_phone_price);
        TextView applyingName=view.findViewById(R.id.tv_applying_name);
        TextView IDNumber=view.findViewById(R.id.tv_id_number);
        TextView IDAddress=view.findViewById(R.id.tv_id_address);
        TextView applyingDate=view.findViewById(R.id.tv_applying_date);

        applyingPhoneName.setText(data.get(position).getVersion());
        applyingPhonePrice.setText("¥ "+String.valueOf(data.get(position).getPrice()));
        applyingName.setText(data.get(position).getName());
        IDNumber.setText(String.valueOf(data.get(position).getId_card_no()));
        IDAddress.setText(data.get(position).getAddress());
        applyingDate.setText(data.get(position).getApply_date());

        loadingPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putInt("orderId",data.get(position).getOrder_id());
                JumpUtil.overlay(context, SignUpAndPaymentActivity.class,bundle);
            }
        });

        orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置监听
                mOnItemDeleteListener.onDeleteClick(position);
            }
        });
        return viewList.get(position);
    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }
}

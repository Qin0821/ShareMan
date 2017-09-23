package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;

import java.util.List;

/**
 * Created by Touch on 2017/9/8.
 */

public class MyCancelPagerAdapter extends PagerAdapter {

    private Context context;
    private List<View> viewList;//界面的个数
    private List<ShareOrderModel> data;

    public MyCancelPagerAdapter(Context context, List<View> viewList) {
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
        TextView orderCancel=view.findViewById(R.id.tv_cancel_order);
        TextView cancelPhoneName=view.findViewById(R.id.tv_cancel_phone_name);
        TextView cancelName=view.findViewById(R.id.tv_cancel_name);
        TextView IDNumber=view.findViewById(R.id.tv_id_number);
        TextView IDAddress=view.findViewById(R.id.tv_id_address);
        TextView cancelDate=view.findViewById(R.id.tv_cancel_date);

        cancelPhoneName.setText(data.get(position).getVersion());
        cancelName.setText(data.get(position).getName());
        IDNumber.setText(String.valueOf(data.get(position).getId_card_no()));
        IDAddress.setText(data.get(position).getAddress());
        cancelDate.setText(data.get(position).getCancel_time());


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

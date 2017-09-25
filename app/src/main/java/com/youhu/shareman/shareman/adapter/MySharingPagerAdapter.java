package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;
import com.youhu.shareman.shareman.util.ToastUtils;

import java.util.List;

/**
 * Created by Touch on 2017/9/8.
 */

public class MySharingPagerAdapter extends PagerAdapter {

    private Context context;
    private List<View> viewList;//界面的个数
    private List<ShareOrderModel> data;

    public MySharingPagerAdapter(Context context, List<View> viewList) {
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
        TextView dianzixieyi=view.findViewById(R.id.tv_dianzixieyi);
        TextView shareingPhoneName=view.findViewById(R.id.tv_shareing_phone_name);
        TextView shareingPhoneColor=view.findViewById(R.id.tv_shareing_phone_color);
        TextView shareingPhoneMenmer=view.findViewById(R.id.tv_shareing_phone_menmer);
        TextView shareingPhonePrice=view.findViewById(R.id.tv_shareing_phone_price);
        TextView shareingName=view.findViewById(R.id.tv_shareing_name);
        TextView IDNumber=view.findViewById(R.id.tv_id_number);
        TextView shareingAddress=view.findViewById(R.id.tv_shareing_address);
        TextView shareingDate=view.findViewById(R.id.tv_shareing_date);

        shareingPhoneColor.setText("黑色");
        shareingPhoneMenmer.setText("32G");
        shareingPhoneName.setText(data.get(position).getVersion());
        shareingPhonePrice.setText("¥ "+String.valueOf(data.get(position).getPrice()));
        shareingName.setText(data.get(position).getName());
        IDNumber.setText(String.valueOf(data.get(position).getId_card_no()));
        shareingAddress.setText(data.get(position).getAddress());
        shareingDate.setText(data.get(position).getApply_date());

        dianzixieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(context,"电子协议");
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

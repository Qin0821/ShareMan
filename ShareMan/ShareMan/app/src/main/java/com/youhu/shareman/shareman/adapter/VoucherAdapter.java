package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.model.data.VoucherModel;

import java.util.List;

/**
 * Created by Touch on 2017/8/18.
 */

public class VoucherAdapter extends BaseAdapter {

    private Context context;
    private List<VoucherModel> datas;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<VoucherModel> getDatas() {
        return datas;
    }

    public void setDatas(List<VoucherModel> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_voucher, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //设置数据
        myViewHolder.mImageId.setImageResource(R.drawable.image_daijinquan);
        myViewHolder.mVoucher.setText(datas.get(i).getStartDate()+"至"+datas.get(i).getEndDate());

        return view;
    }

    class MyViewHolder {
        private ImageView mImageId;
        private TextView mVoucher;

        MyViewHolder(View itemView) {
            mImageId=itemView.findViewById(R.id.ig_voucher);
            mVoucher=itemView.findViewById(R.id.tv_voucher);
        }
    }
}

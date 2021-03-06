package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.model.data.AddressMangerModel;
import com.youhu.shareman.shareman.ui.activity.EditAddressActivity;
import com.youhu.shareman.shareman.util.JumpUtil;

import java.util.List;

/**
 * Created by Touch on 2017/8/10.
 */

public class MyAddressListAdapter extends BaseAdapter{

    private Context context;
    private List<AddressMangerModel> datas;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<AddressMangerModel> getDatas() {
        return datas;
    }

    public void setDatas(List<AddressMangerModel> datas) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_address, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //设置数据
        myViewHolder.mReceiver.setText(datas.get(i).getConsignee_name());
        myViewHolder.mReceiverNumber.setText(datas.get(i).getConsignee_tel());
        myViewHolder.mReceiverAddress.setText(datas.get(i).getConsignee_address()+datas.get(i).getDetail_address());
        //点击编辑按钮
        myViewHolder.mAddressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至编辑收货地址界面
                //把上面数据传给下一个界面
                Bundle bundle=new Bundle();
                bundle.putSerializable("updateAddress",datas.get(i));
                JumpUtil.overlay(getContext(), EditAddressActivity.class,bundle);
            }
        });
        //点击删除按钮
        myViewHolder.mAddressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //把点击条目的id传给
                mOnItemDeleteListener.onDeleteClick(i);
            }
        });

        return view;
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

    class MyViewHolder {
        private TextView mReceiver;
        private TextView mReceiverNumber;
        private TextView mReceiverAddress;
        private ImageView mAddressEdit;
        private ImageView mAddressDelete;

        MyViewHolder(View itemView) {
            mReceiver=itemView.findViewById(R.id.receiver);
            mReceiverNumber = itemView.findViewById(R.id.receiver_number);
            mReceiverAddress=itemView.findViewById(R.id.receiver_address);
            mAddressEdit=itemView.findViewById(R.id.address_edit);
            mAddressDelete=itemView.findViewById(R.id.address_delete);
        }
    }
}

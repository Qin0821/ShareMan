package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;

import java.util.List;

/**
 * Created by Touch on 2017/8/10.
 */

public class RecoverListAdapter extends BaseAdapter{

    private Context context;
    private List<String> datas;
    private int selectedPosition = 0;// 选中的位置

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
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
            view = LayoutInflater.from(context).inflate(R.layout.list_item_choose_phone_type_first, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //设置数据
        myViewHolder.mRecoverName.setText(datas.get(i));
        if (selectedPosition == i) {
            myViewHolder.mRecoverName.setTextColor(Color.BLACK);
            myViewHolder.mRecoverName.setBackgroundResource(R.drawable.bg_white);
        } else {
            myViewHolder.mRecoverName.setBackgroundColor(Color.TRANSPARENT);
        }

//        myViewHolder.mRecoverName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemChangeListener.onChangeClick(i);
//                finalMyViewHolder.mRecoverName.setBackgroundResource(R.drawable.bg_white);
//            }
//        });

        return view;
    }

    class MyViewHolder {
        private TextView mRecoverName;

        MyViewHolder(View itemView) {
            mRecoverName = itemView.findViewById(R.id.tv_first_name);
        }
    }

    /**
     * 按钮的监听接口
     */
//    public interface onItemChangeListener {
//        void onChangeClick(int i);
//    }
//
//    private onItemChangeListener mOnItemChangeListener;
//
//    public void setmOnItemChangeClickListener(onItemChangeListener mOnItemChangeListener) {
//        this.mOnItemChangeListener = mOnItemChangeListener;
//    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
}

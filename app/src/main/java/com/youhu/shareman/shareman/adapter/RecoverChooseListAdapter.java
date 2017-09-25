package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;

/**
 * Created by Touch on 2017/8/10.
 */

public class RecoverChooseListAdapter extends BaseAdapter{

    private Context context;
    private String[] datas;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String[] getDatas() {
        return datas;
    }

    public void setDatas(String[] datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas != null ? datas.length : 0;
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
            view = LayoutInflater.from(context).inflate(R.layout.list_item_recover, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //设置数据
        myViewHolder.mRecoverText.setText(datas[i]);

        return view;
    }

    class MyViewHolder {
        private TextView mRecoverText;

        MyViewHolder(View itemView) {
            mRecoverText = itemView.findViewById(R.id.tv_recover_text);
        }
    }

}

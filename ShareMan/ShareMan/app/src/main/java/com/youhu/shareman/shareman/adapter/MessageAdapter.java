package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.data.MessageInfo;

import java.util.List;

/**
 * Created by Touch on 2017/8/10.
 */

public class MessageAdapter extends BaseAdapter{

    private Context context;
    private List<MessageInfo> datas;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<MessageInfo> getDatas() {
        return datas;
    }

    public void setDatas(List<MessageInfo> datas) {
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
            view = LayoutInflater.from(context).inflate(R.layout.list_item_message, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //设置数据
        myViewHolder.mDetailText.setText(datas.get(i).getDetailText());
        myViewHolder.mLinkText.setText(datas.get(i).getLinkText());

        return view;
    }

    class MyViewHolder {
        private TextView mDetailText;
        private TextView mLinkText;

        MyViewHolder(View itemView) {
            mDetailText=itemView.findViewById(R.id.tv_detail_text);
            mLinkText = itemView.findViewById(R.id.tv_link_text);
        }
    }
}

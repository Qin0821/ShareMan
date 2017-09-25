package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.data.UserInfo;

import java.util.List;

/**
 * Created by Touch on 2017/8/10.
 */

public class MyListAdapter extends BaseAdapter{

    private Context context;
    private List<UserInfo> datas;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<UserInfo> getDatas() {
        return datas;
    }

    public void setDatas(List<UserInfo> datas) {
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
            view = LayoutInflater.from(context).inflate(R.layout.list_item_userinfo, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //设置数据
        myViewHolder.mImage.setImageResource(datas.get(i).getImageId());
        myViewHolder.mUserInfoText.setText(datas.get(i).getUserInfoText());

        return view;
    }

    class MyViewHolder {
        private ImageView mImage;
        private TextView mUserInfoText;

        MyViewHolder(View itemView) {
            mImage=itemView.findViewById(R.id.list_image);
            mUserInfoText = itemView.findViewById(R.id.list_text);
        }
    }
}

package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.model.constant.AppConfig;
import com.youhu.shareman.shareman.model.data.HotRecomentModel;
import com.youhu.shareman.shareman.util.GlideRoundTransform;

import java.util.List;

/**
 * Created by Touch on 2017/8/16.
 */

public class MyHotRecomentAdapter extends BaseAdapter {

    private Context context;
    private List<HotRecomentModel> datas;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<HotRecomentModel> getDatas() {
        return datas;
    }

    public void setDatas(List<HotRecomentModel> datas) {
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
            view = LayoutInflater.from(context).inflate(R.layout.list_item_hot_recoment, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //设置数据
        Glide.with(getContext())
                .load(AppConfig.BASE_URL+datas.get(i).getCategory_url())
                .transform(new GlideRoundTransform(getContext(), 5))
                .into(myViewHolder.mImage);
//        myViewHolder.mImage.setImageResource(datas.get(i).getImageUrl());

        return view;
    }

    class MyViewHolder {
        private ImageView mImage;

        MyViewHolder(View itemView) {
            mImage=itemView.findViewById(R.id.hot_recoment_image);
        }
    }
}

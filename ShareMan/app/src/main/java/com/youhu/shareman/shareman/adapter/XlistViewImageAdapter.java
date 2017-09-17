package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.data.ProductDetailInfo;
import com.youhu.shareman.shareman.util.GlideRoundTransform;

import java.util.List;

/**
 * Created by Touch on 2017/8/18.
 */

public class XlistViewImageAdapter extends BaseAdapter {

    private Context context;
    private List<ProductDetailInfo> datas;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ProductDetailInfo> getDatas() {
        return datas;
    }

    public void setDatas(List<ProductDetailInfo> datas) {
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
            view = LayoutInflater.from(context).inflate(R.layout.image_list_item, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //设置数据
        //设置数据
        Glide.with(getContext())
                .load(datas.get(i).getImageId())
                .transform(new GlideRoundTransform(getContext(), 5))
                .into(myViewHolder.mImageId);
//        myViewHolder.mImageId.setImageResource(datas.get(i).getImageId());

        return view;
    }

    class MyViewHolder {
        private ImageView mImageId;

        MyViewHolder(View itemView) {
            mImageId=itemView.findViewById(R.id.ig_share_type);
        }
    }
}

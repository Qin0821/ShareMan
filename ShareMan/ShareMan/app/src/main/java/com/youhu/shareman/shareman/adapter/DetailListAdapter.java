package com.youhu.shareman.shareman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.model.data.ProductDetailModel;

import java.util.List;

/**
 * Created by Touch on 2017/8/10.
 */

public class DetailListAdapter extends BaseAdapter{

    private Context context;
    private List<ProductDetailModel.TagBeanBean> datas;
    private int selectItem=-1;
    int isCheck=-1;
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ProductDetailModel.TagBeanBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ProductDetailModel.TagBeanBean> datas) {
        this.datas = datas;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
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
            view = LayoutInflater.from(context).inflate(R.layout.list_product_detail_item, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //设置数据
        myViewHolder.mColor.setText(datas.get(i).getIntroduce_title());
        //默认选择第一个
        //状态选择器
        if(selectItem==i){
            myViewHolder.mColor.setSelected(true);
            myViewHolder.mColor.setPressed(true);
            myViewHolder.mColor.setTextColor(getContext().getResources().getColor(R.color.apptitle));
            myViewHolder.mRlList.setBackgroundResource(R.drawable.product_detail_black_react);

        }else {
            myViewHolder.mColor.setSelected(false);
            myViewHolder.mColor.setPressed(false);
            myViewHolder.mColor.setTextColor(getContext().getResources().getColor(R.color.appgray));
            myViewHolder.mRlList.setBackgroundResource(R.drawable.product_detail_gray_react);
        }

        return view;
    }

    class MyViewHolder {
        private TextView mColor;
        private RelativeLayout mRlList;

        MyViewHolder(View itemView) {
            mColor=itemView.findViewById(R.id.tv_color_menmer);
            mRlList=itemView.findViewById(R.id.rl_product_detail_list);
        }
    }
}

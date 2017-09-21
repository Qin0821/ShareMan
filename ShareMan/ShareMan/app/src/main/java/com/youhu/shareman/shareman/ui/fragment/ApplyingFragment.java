package com.youhu.shareman.shareman.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.MyApplyingPagerAdapter;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;
import com.youhu.shareman.shareman.presentercoml.ShareOrderPresenter;
import com.youhu.shareman.shareman.ui.view.ShareOrderView;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Touch on 2017/9/8.
 */

public class ApplyingFragment extends ViewPagerBaseFragment {


    private ViewPager pager;
    private List<View> viewList = new ArrayList<View>();
    private List<ShareOrderModel> data;
    private MyApplyingPagerAdapter viewAdapter;
    private LayoutInflater inflater;
    private View view;
    private TextView textView;

    ShareOrderPresenter shareOrderPresenter=new ShareOrderPresenter();

    @Override
    protected View initView(final LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_applying, container, false);
        final ImageView normal=view.findViewById(R.id.img_normal);
        //获取ViewPager
        pager = (ViewPager) view.findViewById(R.id.applying_viewpager);
        pager.setPageMargin((int)getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));

        shareOrderPresenter.onStart();
        //获取订单信息
        shareOrderPresenter.getShareOrder("15701236749","fcfcf1962e40afc99ea1e84a01e6c001",1);
        shareOrderPresenter.attachView(new ShareOrderView() {
            @Override
            public void doShareOrder(BaseData<List<ShareOrderModel>> shareOrderData) {
                data=new ArrayList<>();
                data=shareOrderData.getData();
                if(data.size()==0){
                    pager.setVisibility(View.GONE);
                    normal.setVisibility(View.VISIBLE);
                }else{
                    //获取view
                    //查找布局文件用LayoutInflater.inflate
                    for(int i=0;i<data.size();i++){
                        View view1 = inflater.inflate(R.layout.viewpager_applying_item, null);
                        viewList.add(i,view1);
                    }

                    //实例化适配器
                    viewAdapter = new MyApplyingPagerAdapter(getContext(),viewList);
                    viewAdapter.setData(data);
                    //设置适配器
                    pager.setAdapter(viewAdapter);
                }
            }

            @Override
            public void showMessage(String message) {

            }
        });

        //设置点击取消订单的监听


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}

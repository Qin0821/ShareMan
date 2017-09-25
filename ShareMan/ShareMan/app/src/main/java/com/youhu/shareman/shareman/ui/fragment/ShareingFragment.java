package com.youhu.shareman.shareman.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.MySharingPagerAdapter;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;
import com.youhu.shareman.shareman.presentercoml.ShareOrderPresenter;
import com.youhu.shareman.shareman.ui.widget.AgreementDialog;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.view.ShareOrderView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Touch on 2017/9/8.
 */

public class ShareingFragment extends ViewPagerBaseFragment {
    private ViewPager pager;
    private List<View> viewList = new ArrayList<View>();
    private List<ShareOrderModel> data;
    private MySharingPagerAdapter viewAdapter;
    private LayoutInflater inflater;
    private View view;
    private TextView textView;
    private AgreementDialog agreementDialog;

    ShareOrderPresenter shareOrderPresenter=new ShareOrderPresenter();
    private String phoneNumber;
    private String token;
    private int orderId;

    @Override
    protected View initView(final LayoutInflater inflater, ViewGroup container) {
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(getContext());
        token = SharedPreferencesUtils.getToken(getContext());
        view = inflater.inflate(R.layout.fragment_shareing, container, false);
        final ImageView normal=view.findViewById(R.id.img_normal);
        //获取ViewPager
        pager = (ViewPager) view.findViewById(R.id.shareing_viewpager);
        pager.setPageMargin((int)getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));

        //实例化适配器
        viewAdapter = new MySharingPagerAdapter(getContext(),viewList);

        shareOrderPresenter.onStart();
        //获取订单信息
//        shareOrderPresenter.getShareOrder(phoneNumber,token,2);
        shareOrderPresenter.getShareOrder("15701236749","4f4f5ccb9f7ad689ba2552c2c0d25703",1);
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
                        View view1 = inflater.inflate(R.layout.viewpager_shareing_item, null);
                        viewList.add(i,view1);
                    }

                    viewAdapter.setData(data);
                    //设置适配器
                    pager.setAdapter(viewAdapter);
                }
            }

            @Override
            public void doDeleteOrder(NormalModel deleteOrderData) {

            }

            @Override
            public void doCancelOrder(NormalModel cancelOrderData) {

            }

            @Override
            public void showMessage(String message) {

            }
        });

        viewAdapter.setOnItemAgreementClickListener(new MySharingPagerAdapter.onItemAgreementListener() {
            @Override
            public void onDeleteClick(int position) {
                agreementDialog=new AgreementDialog(getContext());
                agreementDialog.setPhoneNumber(phoneNumber);
                agreementDialog.setToken(token);
                orderId=data.get(position).getOrder_id();
                agreementDialog.setOrderId(orderId);
                agreementDialog.setCancelOnclickListener(new AgreementDialog.onCancelOnclickListener() {
                    @Override
                    public void onCancelClick() {
                        agreementDialog.dismiss();
                    }
                });
                agreementDialog.show();
            }
        });
        return view;
    }

}

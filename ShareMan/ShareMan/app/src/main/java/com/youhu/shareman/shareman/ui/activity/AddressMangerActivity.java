package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.MyAddressListAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.AddressMangerModel;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presentercoml.AddressMangerPresenter;
import com.youhu.shareman.shareman.view.AddressMangerView;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/16.
 */

public class AddressMangerActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.btn_add_address)
    Button mAddAddress;
    @BindView(R.id.address_list)
    ListView mAddressList;

    AddressMangerPresenter addressMangerPresenter=new AddressMangerPresenter();
    private MyAddressListAdapter myAddressListAdapter;
    private List<AddressMangerModel> datas;
    private String phoneNumber;
    private String token;


    @Override
    protected void initBind() {
        setContentView(R.layout.activity_address_manger);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文
        setContext(this);
        mTitle.setText("管理收货地址");

        //初始化适配器
        myAddressListAdapter=new MyAddressListAdapter();
        myAddressListAdapter.setContext(getContext());

        //初始化用户信息
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        //初始化RetrofitManger
        addressMangerPresenter.onStart();
        addressMangerPresenter.attachView(addressMangerView);

        //调用获取地址接口
//        addressMangerPresenter.doPostDetail(phoneNumber,token);
//        addressMangerPresenter.doPostDetail("15701236749","fcfcf1962e40afc99ea1e84a01e6c001");

        //调用删除地址接口
        myAddressListAdapter.setOnItemDeleteClickListener(new MyAddressListAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int i) {
                int postDetailId=datas.get(i).getId();
                addressMangerPresenter.doDeletePostDetail(phoneNumber,token,postDetailId);
//                addressMangerPresenter.doDeletePostDetail("15701236749","fcfcf1962e40afc99ea1e84a01e6c001",postDetailId);
                //删除后重新请求数据
                addressMangerPresenter.doPostDetail(phoneNumber,token);
                myAddressListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        //调用获取地址接口
        addressMangerPresenter.doPostDetail(phoneNumber,token);
//        addressMangerPresenter.doPostDetail("15701236749","fcfcf1962e40afc99ea1e84a01e6c001");
    }

    //获取地址数据返回
    AddressMangerView addressMangerView=new AddressMangerView() {
        @Override
        public void doPostDetail(BaseData<List<AddressMangerModel>> addressMangerModel) {
            //数据来源
            datas=addressMangerModel.getData();
            myAddressListAdapter.setDatas(datas);
            mAddressList.setAdapter(myAddressListAdapter);
        }

        @Override
        public void doDeletePostDetail(NormalModel deleteAddressModel) {
            ToastUtils.show(getContext(),deleteAddressModel.getMessage());
        }

        @Override
        public void showMessage(String message) {

        }
    };

    @OnClick({R.id.back,R.id.btn_add_address})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_add_address:
                JumpUtil.overlay(this,AddAddressActivity.class);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

}

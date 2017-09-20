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
import com.youhu.shareman.shareman.ui.view.AddressMangerView;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;

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
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        addressMangerPresenter.onStart();
        addressMangerPresenter.attachView(addressMangerView);

        //调用获取地址接口
//        addressMangerPresenter.doPostDetail(phoneNumber,token);
        addressMangerPresenter.doPostDetail("15701236749","fcfcf1962e40afc99ea1e84a01e6c001");
    }

    @Override
    protected void initData() {
        //获取收货地址信息
//        initAddress();
    }

    @Override
    protected void fillData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //获取地址数据返回
    AddressMangerView addressMangerView=new AddressMangerView() {
        @Override
        public void doPostDetail(BaseData<List<AddressMangerModel>> addressMangerModel) {
            //数据来源
            datas=addressMangerModel.getData();
            myAddressListAdapter=new MyAddressListAdapter();
            myAddressListAdapter.setContext(getContext());
            myAddressListAdapter.setDatas(datas);
            mAddressList.setAdapter(myAddressListAdapter);
        }

        @Override
        public void doDeletePostDetail(NormalModel deleteAddressModel) {

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

//    private void initAddress() {
//        //数据来源
//        datas=new ArrayList<AddressInfo>();
//        datas.add(new AddressInfo("微微","15355479993","浙江省杭州市西湖区文三路文锦大厦B座905"));
//        datas.add(new AddressInfo("波波","15355479993","浙江省杭州市西湖区文三路文锦大厦B座905"));
//        datas.add(new AddressInfo("超哥","15355479993","浙江省杭州市西湖区文三路文锦大厦B座905"));
//        datas.add(new AddressInfo("小美","15355479993","浙江省杭州市西湖区文三路文锦大厦B座905"));
//        myAddressListAdapter=new MyAddressListAdapter();
//        myAddressListAdapter.setContext(this);
//        myAddressListAdapter.setDatas(datas);
//        mAddressList.setAdapter(myAddressListAdapter);
//
//    }
}

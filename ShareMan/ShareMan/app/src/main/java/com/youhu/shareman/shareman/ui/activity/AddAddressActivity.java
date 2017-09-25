package com.youhu.shareman.shareman.ui.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presentercoml.AddAddressPresenter;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.util.ToastUtils;
import com.youhu.shareman.shareman.view.AddAddressView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import db.AreaBean;
import db.CityBean;
import db.DBManager;
import db.ProvinceBean;

/**
 * Created by Touch on 2017/8/12.
 */

public class AddAddressActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitleText;
    @BindView(R.id.et_receiver_name)
    EditText mReceiverName;
    @BindView(R.id.et_mobile_phone_number)
    EditText mMobilePhoneNumber;
    @BindView(R.id.tv_choose_address)
    TextView mChooseAddress;
    @BindView(R.id.et_detail_address)
    EditText mDetailAddress;
    @BindView(R.id.save_address)
    Button mSaveAddress;


    AddAddressPresenter addAddressPresenter=new AddAddressPresenter();

    private OptionsPickerView pvOptions;//地址选择器
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<CityBean>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<AreaBean>>> options3Items = new ArrayList<>();//区
    private ArrayList<String> Provincestr = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> Citystr = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> Areastr = new ArrayList<>();//区
    private String phoneNumber;
    private String token;
    private String consigneeAddress="";

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_add_address);
        super.initBind();
    }


    @Override
    protected void initUI() {
        setContext(this);
        //设置标题
        mTitleText.setText("新增地址");
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        addAddressPresenter.onCreate();
        addAddressPresenter.attachView(addAddressView);

    }

    @Override
    protected void initData() {
        //初始化地址选择器
//        ininPickerView();

    }

    @Override
    protected void fillData() {

    }


    //添加地址接口返回
    AddAddressView addAddressView=new AddAddressView() {
        @Override
        public void doAddAddress(NormalModel addAddressData) {
            ToastUtils.show(getContext(),"添加成功");
        }

        @Override
        public void showMessage(String message) {

        }
    };

    @OnClick({R.id.back, R.id.et_receiver_name, R.id.et_mobile_phone_number, R.id.tv_choose_address, R.id.et_detail_address,R.id.save_address})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_receiver_name:

                break;
            case R.id.et_mobile_phone_number:

                break;
            case R.id.tv_choose_address:
//                pvOptions.show();
                break;
            case R.id.et_detail_address:

                break;
            case R.id.save_address:
                //提交新的地址
                String consigneeName=mReceiverName.getText().toString();
                String consigneeTel=mMobilePhoneNumber.getText().toString();
                consigneeAddress="浙江省杭州市西湖区";
                String detailAddress=mDetailAddress.getText().toString();
                if(consigneeName==null||"".equals(consigneeName)){
                    ToastUtils.show(this,"收货人姓名不能为空");
                }else if(consigneeTel==null||"".equals(consigneeTel)){
                    ToastUtils.show(this,"收货人手机号不能为空");
                }else if("".equals(consigneeAddress)){
                    ToastUtils.show(this,"收货人地址不能为空");
                }else{
                    addAddressPresenter.doPostDetail(phoneNumber,token,consigneeName,consigneeTel,consigneeAddress,detailAddress);
                    finish();
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void ininPickerView() {
        //选项选择器
        pvOptions = new OptionsPickerView(this);
        // 获取数据库
        SQLiteDatabase db = DBManager.getdb(getApplication());
        //省
        Cursor cursor = db.query("province", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int pro_id = cursor.getInt(0);
            String pro_code = cursor.getString(1);
            String pro_name = cursor.getString(2);
            String pro_name2 = cursor.getString(3);
            ProvinceBean provinceBean = new ProvinceBean(pro_id, pro_code, pro_name, pro_name2);
            options1Items.add(provinceBean);//添加一级目录
            Provincestr.add(cursor.getString(2));
            //查询二级目录，市区
            Cursor cursor1 = db.query("city", null, "province_id=?", new String[]{pro_id + ""}, null, null,
                    null);
            ArrayList<CityBean> cityBeanList = new ArrayList<>();
            ArrayList<String> cityStr = new ArrayList<>();
            //地区集合的集合（注意这里要的是当前省份下面，当前所有城市的地区集合我去）
            ArrayList<ArrayList<AreaBean>> options3Items_03 = new ArrayList<>();
            ArrayList<ArrayList<String>> options3Items_str = new ArrayList<>();
            while (cursor1.moveToNext()) {
                int cityid = cursor1.getInt(0);
                int province_id = cursor1.getInt(1);
                String code = cursor1.getString(2);
                String name = cursor1.getString(3);
                String provincecode = cursor1.getString(4);
                CityBean cityBean = new CityBean(cityid, province_id, code, name, provincecode);
                //添加二级目录
                cityBeanList.add(cityBean);
                cityStr.add(cursor1.getString(3));
                //查询三级目录
                Cursor cursor2 = db.query("area", null, "city_id=?", new String[]{cityid + ""}, null, null,
                        null);
                ArrayList<AreaBean> areaBeanList = new ArrayList<>();
                ArrayList<String> areaBeanstr = new ArrayList<>();
                while (cursor2.moveToNext()) {
                    int areaid = cursor2.getInt(0);
                    int city_id = cursor2.getInt(1);
//                    String code0=cursor2.getString(2);
                    String areaname = cursor2.getString(3);
                    String citycode = cursor2.getString(4);
                    AreaBean areaBean = new AreaBean(areaid, city_id, areaname, citycode);
                    areaBeanList.add(areaBean);
                    areaBeanstr.add(cursor2.getString(3));
                }
                options3Items_str.add(areaBeanstr);//本次查询的存储内容
                options3Items_03.add(areaBeanList);
            }
            options2Items.add(cityBeanList);//增加二级目录数据
            Citystr.add(cityStr);
            options3Items.add(options3Items_03);//添加三级目录
            Areastr.add(options3Items_str);
        }
        //设置三级联动效果
        pvOptions.setPicker(Provincestr, Citystr, Areastr, true);
        //设置选择的三级单位
//        pvOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        //设置是否循环滚动
        pvOptions.setCyclic(false, false, false);

        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(0, 0, 0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPro_name()
                        + options2Items.get(options1).get(option2).getName()
                        + options3Items.get(options1).get(option2).get(options3).getName();
                mChooseAddress.setText(tx);
                consigneeAddress=tx;
            }
        });
    }
}

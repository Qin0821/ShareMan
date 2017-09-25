package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.VoucherAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.data.VoucherInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/7.
 */

public class VoucherActivity extends BaseActivity implements BaseView{

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.list_voucher)
    ListView mListVoucher;

    private VoucherAdapter mVoucherAdapter;
    private List<VoucherInfo> datas;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_voucher);
        super.initBind();
    }

    @Override
    protected void initUI() {
        mTitle.setText("代金券");

        //初始化代金券
        initVoucher();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }


    @Override
    public void showMessage(String message) {

    }

    @OnClick({R.id.back})
    void onClici(View view){
        switch (view.getId()){

            case R.id.back:
                finish();
                break;
        }
    }

    private void initVoucher() {
        //初始化数据
        datas=new ArrayList<VoucherInfo>();
        datas.add(new VoucherInfo(R.drawable.image_daijinquan,"有效期： 2017.7.30-2017.12.29"));
        datas.add(new VoucherInfo(R.drawable.image_daijinquan,"有效期： 2017.7.30-2017.12.30"));
        datas.add(new VoucherInfo(R.drawable.image_daijinquan,"有效期： 2017.7.30-2017.12.31"));
        datas.add(new VoucherInfo(R.drawable.image_daijinquan,"有效期： 2017.7.30-2017.12.18"));
        mVoucherAdapter=new VoucherAdapter();
        mVoucherAdapter.setContext(this);
        mVoucherAdapter.setDatas(datas);
        mListVoucher.setAdapter(mVoucherAdapter);
    }
}

package com.youhu.shareman.shareman.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.XlistViewImageAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.data.ProductDetailInfo;
import com.youhu.shareman.shareman.util.JumpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/8.
 */

public class ShareTypeActivity extends BaseActivity implements BaseView {

    @BindView(R.id.Xlistview)
    ListView mXlistView;
    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;

    //iPhone系列
    private static int STATE_IPHONE=11;
    //vivo系列
    private static int STATE_VIVO=12;
    //Huawei系列
    private static int STATE_HUAWEI=13;
    //oppo系列
    private static int STATE_OPPO=14;
    private XlistViewImageAdapter adapter;
    private List<ProductDetailInfo> datas;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_share_type);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文，标题
        setContext(this);
        mTitle.setText("共享分类");

        //初始化共享分类列表
        initShareType();

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

    @OnClick(R.id.back)
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

    private void initShareType() {
        //初始化
        adapter=new XlistViewImageAdapter();
        datas=new ArrayList<ProductDetailInfo>();
        datas.add(new ProductDetailInfo(R.drawable.class_iphone));
        datas.add(new ProductDetailInfo(R.drawable.class_oppo));
        datas.add(new ProductDetailInfo(R.drawable.class_vivo));
        datas.add(new ProductDetailInfo(R.drawable.class_huawei));
        adapter.setContext(this);
        adapter.setDatas(datas);
        mXlistView.setAdapter(adapter);
        mXlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Bundle bundle=new Bundle();
                switch (position){
                    case 0:
                        bundle.putInt("type_state",STATE_IPHONE);
                        JumpUtil.overlay(getContext(),BrandActivity.class,bundle);
                        break;
                    case 1:
                        bundle.putInt("type_state",STATE_OPPO);
                        JumpUtil.overlay(getContext(),BrandActivity.class,bundle);
                        break;
                    case 2:
                        bundle.putInt("type_state",STATE_VIVO);
                        JumpUtil.overlay(getContext(),BrandActivity.class,bundle);
                        break;
                    case 3:
                        bundle.putInt("type_state",STATE_HUAWEI);
                        JumpUtil.overlay(getContext(),BrandActivity.class,bundle);
                        break;
                }
            }
        });
    }
}

package com.youhu.shareman.shareman.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.RecoverListAdapter;
import com.youhu.shareman.shareman.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/8.
 */

public class RecoverActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
//    @BindView(R.id.lv_first)
//    ListView mFirstListview;
//    @BindView(R.id.lv_second)
//    ListView mSecondListview;

    private RecoverListAdapter adapter;
    private List<String> data;
    private String[] data1={"iPhone","vivo","华为"};
    private String[][] data2={{"iPhone 7","iPhone 7 plus","iPhone 8 "},{"vivo 7","vivo 8 ","vivo x9"},{"华为P7","华为P8","华为P9"}};


    @Override
    protected void initBind() {
        setContentView(R.layout.activity_service_type);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //上下文，标题
        setContext(this);
        mTitle.setText("回收");


        data=new ArrayList<>();
        data.add("iPhone");
        data.add("vivo");
        data.add("oppo");
        data.add("华为");
        adapter=new RecoverListAdapter();
        adapter.setDatas(data);
        adapter.setContext(this);
//        mFirstListview.setAdapter(adapter);
//        mFirstListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                adapter.setSelectedPosition(position);
//                mFirstListview.setSelection(position);
//
//            }
//        });
//        adapter.notifyDataSetInvalidated();
//        adapter.setmOnItemChangeClickListener(new RecoverListAdapter.onItemChangeListener() {
//            @Override
//            public void onChangeClick(int i) {
//            }
//        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }

    @OnClick(R.id.back)
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

}

package com.youhu.shareman.shareman.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mrj.library.RoundImageView;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.adapter.DetailListAdapter;
import com.youhu.shareman.shareman.model.constant.AppConfig;
import com.youhu.shareman.shareman.model.data.ProductDetailModel;
import com.youhu.shareman.shareman.util.GlideRoundTransform;
import com.youhu.shareman.shareman.util.ToastUtils;

import java.util.List;

/**
 * Created by Touch on 2017/9/23.
 */

public class TagDialog extends Dialog {

    private AccidentProtectionDialog accidentProtectionDialog;

    private ImageView sureImageView;
    private ImageView yiwaiImageView;
    private int chooseId=-1;
    private List<ProductDetailModel.TagBeanBean> tagBeanList;
    private ListView listView;
    private DetailListAdapter detailListAdapter;
    private RoundImageView roundImageView;
    private TextView choosePrice;
    private TextView yiwaiPrice;
    private ImageView roundImageView1;

    public TagDialog(@NonNull Context context) {
        super(context,R.style.ShopTabDialog);
    }

    public List<ProductDetailModel.TagBeanBean> getTagBeanList() {
        return tagBeanList;
    }

    public void setTagBeanList(List<ProductDetailModel.TagBeanBean> tagBeanList) {
        this.tagBeanList = tagBeanList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        initDialog();
        if(tagBeanList!=null){
            if(chooseId!=-1){
                detailListAdapter.setSelectItem(chooseId);
                choosePrice.setText("¥"+tagBeanList.get(chooseId).getReal_price());
                yiwaiPrice.setText("意外保障 ¥"+tagBeanList.get(chooseId).getInsurance()+"必选");
                Glide.with(getContext()).load(AppConfig.IMAGE_URL+tagBeanList.get(chooseId).getCover_fir()).transform(new GlideRoundTransform(getContext(), 5)).error(R.drawable.error).into(roundImageView1);
            }else{
                choosePrice.setText("¥"+tagBeanList.get(0).getReal_price());
                yiwaiPrice.setText("意外保障 ¥"+tagBeanList.get(0).getInsurance()+"必选");
                Glide.with(getContext()).load(AppConfig.IMAGE_URL+tagBeanList.get(0).getCover_fir()).transform(new GlideRoundTransform(getContext(), 5)).error(R.drawable.error).into(roundImageView1);
            }
        }
    }

    private void initData() {
        //展示规格数据
        detailListAdapter=new DetailListAdapter();
        detailListAdapter.setContext(getContext());
        detailListAdapter.setDatas(tagBeanList);
        listView.setAdapter(detailListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(chooseId!=i){
                    detailListAdapter.setSelectItem(i);
                    choosePrice.setText("¥"+tagBeanList.get(i).getReal_price());
                    yiwaiPrice.setText("意外保障 ¥"+tagBeanList.get(i).getInsurance()+"必选");
                    Glide.with(getContext()).load(AppConfig.IMAGE_URL+tagBeanList.get(i).getCover_fir()).transform(new GlideRoundTransform(getContext(), 5)).error(R.drawable.error).into(roundImageView1);
                    chooseId=i;
                }else{
                    detailListAdapter.setSelectItem(-1);
                    choosePrice.setText("¥"+tagBeanList.get(0).getReal_price());
                    yiwaiPrice.setText("意外保障 ¥"+tagBeanList.get(0).getInsurance()+"必选");
                    Glide.with(getContext()).load(AppConfig.IMAGE_URL+tagBeanList.get(0).getCover_fir()).transform(new GlideRoundTransform(getContext(), 5)).error(R.drawable.error).into(roundImageView1);
                    chooseId=-1;
                }

                detailListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initEvent() {

    }

    private void initDialog() {
        setContentView(R.layout.tag_dialog);
        //设置返回键可撤销
        setCancelable(true);
        //设置点击非Dialog区可撤销
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        yiwaiImageView= findViewById(R.id.img_yiwaibaozhang);
        sureImageView=findViewById(R.id.sure_button);
        listView = findViewById(R.id.lv_product_detail);
//        roundImageView = findViewById(R.id.riv_image);
        roundImageView1 = findViewById(R.id.riv_image);
        choosePrice = findViewById(R.id.price);
        yiwaiPrice = findViewById(R.id.tv_yiwai_price);


        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        yiwaiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accidentProtectionDialog=new AccidentProtectionDialog(getContext());
                accidentProtectionDialog.setCancelOnclickListener(new AccidentProtectionDialog.onCancelOnclickListener() {
                    @Override
                    public void onCancelClick() {
                        accidentProtectionDialog.dismiss();
                    }
                });
                accidentProtectionDialog.show();
                ToastUtils.show(getContext(),"意外保障说明");
            }
        });

        sureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnclickListener.onItemClick(chooseId);
            }
        });
    }


    //获取选中的ID方法
    public interface onItemOnclickListener {
        public void onItemClick(int chooseId);
    }

    private onItemOnclickListener itemOnclickListener;

    public void setItemOnclickListener(onItemOnclickListener onItemOnclickListener) {

        this.itemOnclickListener = onItemOnclickListener;
    }
}

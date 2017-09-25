package com.youhu.shareman.shareman.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.model.data.ProductDetailModel;
import com.youhu.shareman.shareman.util.ToastUtils;

import java.util.List;

/**
 * Created by Touch on 2017/9/23.
 */

public class TagDialog extends Dialog {

    private AccidentProtectionDialog accidentProtectionDialog;

    private ImageView sureImageView;
    private ImageView yiwaiImageView;
    private List<ProductDetailModel.TagBeanBean> tagBeanList;
    private ListView listView;

    public TagDialog(@NonNull Context context) {
        super(context,R.style.ShopTabDialog);
    }

    public List<ProductDetailModel.TagBeanBean> setTagBeanList() {
        return tagBeanList;
    }

    public void setTagBeanList(List<ProductDetailModel.TagBeanBean> tagBean) {
        this.tagBeanList = tagBean;
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

    }

    private void initData() {

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
        //TODO
        //设置适配器
        listView = findViewById(R.id.lv_product_detail);

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
                ToastUtils.show(getContext(),"开始预约");
            }
        });
    }
}

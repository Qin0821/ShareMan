package com.youhu.shareman.shareman.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yalantis.ucrop.UCrop;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.util.ToastUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/18.
 */

public class InfomationActivity extends BaseActivity implements BaseView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.textView)
    TextView mTitle;
    @BindView(R.id.et_really_name)
    EditText mReallyName;
    @BindView(R.id.et_id_number)
    EditText mIDNumber;
    @BindView(R.id.et_server_pwd)
    EditText mServerPwd;
    @BindView(R.id.et_address_now)
    EditText mAddressNow;
    @BindView(R.id.et_unit_name)
    EditText mUnitName;
    @BindView(R.id.relativeLayout1)
    RelativeLayout mRelativeLayout1;
    @BindView(R.id.relativeLayout2)
    RelativeLayout mRelativeLayout2;
    @BindView(R.id.relativeLayout3)
    RelativeLayout mRelativeLayout3;
    @BindView(R.id.relativeLayout4)
    RelativeLayout mRelativeLayout4;
    @BindView(R.id.ig_infomationA)
    ImageView mImageInfomationA;
    @BindView(R.id.ig_infomationB)
    ImageView mImageInfomationB;
    @BindView(R.id.ig_infomation_banshen)
    ImageView mImageInfomationBanshen;
    @BindView(R.id.ig_infomation_student)
    ImageView mImageInfomationStudent;
    @BindView(R.id.submit_infomation)
    Button mSubmitInfomatoin;


    //对话框
    private TextView firstChoose;
    private TextView secondChoose;
    private TextView chooseCancel;
    private View inflate;
    //自定义底部弹窗
    private Dialog dialog;
    private static final int CAMERA_REQUEST_CODE = 11;
    private static final int GALLERY_REQUEST_CODE = 22;
    private String mTempPhotoPath = Environment.getExternalStorageDirectory() + File.separator + "photo.jpeg";
    private Uri mGetPhotoPath;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_infomation);
        super.initBind();
    }

    @Override
    protected void initUI() {
        setContext(this);

        //标题
        mTitle.setText("身份信息");
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

    @OnClick({R.id.back,R.id.relativeLayout1,R.id.relativeLayout2,R.id.relativeLayout3,R.id.relativeLayout4,R.id.submit_infomation})
    void onClici(View view){
        switch (view.getId()){
            case R.id.relativeLayout1:
                dialogShow(R.id.relativeLayout1);
                break;
            case R.id.relativeLayout2:
                dialogShow(R.id.relativeLayout2);
                break;
            case R.id.relativeLayout3:
                dialogShow(R.id.relativeLayout3);
                break;
            case R.id.relativeLayout4:
                dialogShow(R.id.relativeLayout4);
                break;
            case R.id.submit_infomation:
                //提交身份信息的时候验证所有信息是否为空
                if(TextUtils.isEmpty(mReallyName.getText().toString())){
                    ToastUtils.show(this,"真实姓名不能为空");
                }else if(TextUtils.isEmpty(mIDNumber.getText().toString())){
                    ToastUtils.show(this,"身份证号不能为空");
                }else if(TextUtils.isEmpty(mServerPwd.getText().toString())){
                    ToastUtils.show(this,"服务密码不能为空");
                }else if(TextUtils.isEmpty(mUnitName.getText().toString())){
                    ToastUtils.show(this,"工作单位或所在院校不能为空");
                }else if(TextUtils.isEmpty(mAddressNow.getText().toString())){
                    ToastUtils.show(this,"现住地址不能为空");
                }else{
                    ToastUtils.show(this,"提交身份信息成功");
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    //切割图片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == this.RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:   // 调用相机拍照
                    File temp = new File(mTempPhotoPath);
//                    startCropActivity(Uri.fromFile(temp));
                    break;
                case GALLERY_REQUEST_CODE:  // 直接从相册获取
                    mGetPhotoPath=data.getData();
//                    startCropActivity(data.getData());
                    break;
                case UCrop.REQUEST_CROP:
                    // TODO: 裁剪图片结果
                    break;
                case UCrop.RESULT_ERROR:
                    // TODO: 裁剪图片错误
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //设置选择图片弹出框
    public void dialogShow(final int viewId){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.choose_phone_dialog_layout, null);
        //初始化控件
        firstChoose = inflate.findViewById(R.id.take_photo);
        secondChoose = inflate.findViewById(R.id.choose_photo);
        chooseCancel = inflate.findViewById(R.id.choose_phone_cancel);
        firstChoose.setText("拍照");
        secondChoose.setText("相册选取");
        firstChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(getContext(),"拍照");
                dialog.dismiss();
                //调用相机拍照
                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //下面这句指定调用相机拍照后的照片存储的路径
                takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTempPhotoPath)));
                startActivityForResult(takeIntent, CAMERA_REQUEST_CODE);
                switch (viewId){
                    case R.id.relativeLayout1:
                        mImageInfomationA.setVisibility(View.VISIBLE);
                        mImageInfomationA.setImageURI(Uri.fromFile(new File(mTempPhotoPath)));
                        break;
                }
            }
        });
        secondChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(getContext(),"相册选取");
                dialog.dismiss();
                // "从相册选择"按钮被点击了
                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(pickIntent, GALLERY_REQUEST_CODE);
                switch (viewId){
                    case R.id.relativeLayout1:
                        mImageInfomationA.setVisibility(View.VISIBLE);
                        mImageInfomationA.setImageURI(mGetPhotoPath);
                        break;
                }
            }
        });

        chooseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置Dialog距离底部的距离
        lp.y = 20;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        //显示对话框
        dialog.show();
    }
}

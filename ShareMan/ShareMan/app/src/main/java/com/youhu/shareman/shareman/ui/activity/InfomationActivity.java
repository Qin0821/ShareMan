package com.youhu.shareman.shareman.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kevin.crop.UCrop;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.constant.AppConfig;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.InformationModel;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presentercoml.InformationPresenter;
import com.youhu.shareman.shareman.util.GlideRoundTransform;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.util.ToastUtils;
import com.youhu.shareman.shareman.view.InformationView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Touch on 2017/8/18.
 */

public class InfomationActivity extends BaseActivity {

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
    private static final int CAMERA_REQUEST_CODE_1 = 11;
    private static final int CAMERA_REQUEST_CODE_2 = 12;
    private static final int CAMERA_REQUEST_CODE_3 = 13;
    private static final int CAMERA_REQUEST_CODE_4 = 14;
    private static final int GALLERY_REQUEST_CODE_1 = 21;
    private static final int GALLERY_REQUEST_CODE_2 = 22;
    private static final int GALLERY_REQUEST_CODE_3 = 23;
    private static final int GALLERY_REQUEST_CODE_4 = 24;
    private Uri mDestinationUri;
    private String mTempPhotoPath = Environment.getExternalStorageDirectory() + File.separator + "photo.jpeg";
    /**
     * 图片选择的监听回调
     */
    private OnPictureSelectedListener mOnPictureSelectedListener;
    /** 依附的Activity */
    protected InfomationActivity mActivity= null;
    private Uri mGetPhotoPath;
    InformationPresenter informationPresenter=new InformationPresenter();
    private String phoneNumber;
    private String token;
    private File myCaptureFileA;
    private File myCaptureFileB;
    private File myCaptureFileC;
    private File myCaptureFileD;

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
        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        informationPresenter.onCreate();
        informationPresenter.attachView(informationView);
//        informationPresenter.getInformation(phoneNumber,token);
        informationPresenter.getInformation("15701236749","4f4f5ccb9f7ad689ba2552c2c0d25703");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }


    //上传身份信息接口返回
    InformationView informationView=new InformationView() {
        @Override
        public void doGetInformation(BaseData<InformationModel> informationModel) {
            //TODO
            //设置个人信息数据
            mReallyName.setText(informationModel.getData().getName());
            mIDNumber.setText(informationModel.getData().getId_card_no());
            mServerPwd.setText(informationModel.getData().getService_password());
            mUnitName.setText(informationModel.getData().getCompany());
            mAddressNow.setText(informationModel.getData().getAddress());
            //设置身份证图片
            if(informationModel.getData().getStudent_id_card()==null){
                mImageInfomationA.setVisibility(View.INVISIBLE);
            }else{
                mImageInfomationA.setVisibility(View.VISIBLE);
                Glide.with(getContext()).load(AppConfig.BASE_URL+informationModel.getData().getId_card_a()).transform(new GlideRoundTransform(getContext(), 5)).error(R.drawable.error).into(mImageInfomationA);
            }
            if(informationModel.getData().getStudent_id_card()==null){
                mImageInfomationB.setVisibility(View.INVISIBLE);
            }else{
                mImageInfomationB.setVisibility(View.VISIBLE);
                Glide.with(getContext()).load(AppConfig.BASE_URL+informationModel.getData().getId_card_b()).transform(new GlideRoundTransform(getContext(), 5)).error(R.drawable.error).into(mImageInfomationB);
            }
            if(informationModel.getData().getStudent_id_card()==null){
                mImageInfomationBanshen.setVisibility(View.INVISIBLE);
            }else{
                mImageInfomationBanshen.setVisibility(View.VISIBLE);
                Glide.with(getContext()).load(AppConfig.BASE_URL+informationModel.getData().getId_card_on_hand()).transform(new GlideRoundTransform(getContext(), 5)).error(R.drawable.error).into(mImageInfomationBanshen);
            }
            if(informationModel.getData().getStudent_id_card()==null){
                mImageInfomationStudent.setVisibility(View.INVISIBLE);
            }else{
                mImageInfomationStudent.setVisibility(View.VISIBLE);
                Glide.with(getContext()).load(AppConfig.BASE_URL+informationModel.getData().getId_card_no()).transform(new GlideRoundTransform(getContext(), 5)).error(R.drawable.error).into(mImageInfomationStudent);
            }

        }

        @Override
        public void doUploadInformation(NormalModel uploadInformation) {
            ToastUtils.show(getContext(),"上传成功");
        }

        @Override
        public void doUploadIdCardA(NormalModel uploadIdCardAData) {
            ToastUtils.show(getContext(),"上传中...");
        }

        @Override
        public void doUploadIdCardB(NormalModel uploadIdCardBData) {
            ToastUtils.show(getContext(),"上传中...");
        }

        @Override
        public void doUploadBanshen(NormalModel uploadBanshenData) {
            ToastUtils.show(getContext(),"上传成功");
        }

        @Override
        public void doUploadStudent(NormalModel uploadStudentData) {
            ToastUtils.show(getContext(),"上传成功");
        }

        @Override
        public void showMessage(String message) {

        }
    };

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
                    //获取用户填写信息
                    String name=mReallyName.getText().toString();
                    String idCardNo=mIDNumber.getText().toString();
                    String servicePassword=mServerPwd.getText().toString();
                    String company=mUnitName.getText().toString();
                    String address=mAddressNow.getText().toString();
                    //图片文件
                    informationPresenter.uploadIdCardA(phoneNumber,token,myCaptureFileA);
                    informationPresenter.uploadIdCardA(phoneNumber,token,myCaptureFileB);
                    informationPresenter.uploadIdCardA(phoneNumber,token,myCaptureFileC);
                    informationPresenter.uploadIdCardA(phoneNumber,token,myCaptureFileD);
                    informationPresenter.uploadInformation(phoneNumber,token,name,idCardNo,servicePassword,company,address);
//                    informationPresenter.uploadIdCardA("15701236749","4f4f5ccb9f7ad689ba2552c2c0d25703",myCaptureFileA);
//                    informationPresenter.uploadIdCardB("15701236749","4f4f5ccb9f7ad689ba2552c2c0d25703",myCaptureFileB);
//                    informationPresenter.uploadIdCardBanshen("15701236749","4f4f5ccb9f7ad689ba2552c2c0d25703",myCaptureFileC);
//                    informationPresenter.uploadStudent("15701236749","4f4f5ccb9f7ad689ba2552c2c0d25703",myCaptureFileD);
//                    informationPresenter.uploadInformation("15701236749","4f4f5ccb9f7ad689ba2552c2c0d25703",name,idCardNo,servicePassword,company,address);
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    //选择图片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 调用相机拍照
                case CAMERA_REQUEST_CODE_1:
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");

                    try {
                        saveFileA(bitmap,"informationA");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mImageInfomationA.setImageBitmap(bitmap);
//                    Glide.with(getContext()).load(data.getExtras()).asBitmap().transform(new GlideRoundTransform(getContext(), 20)).into(mImageInfomationA);
//                    File temp = new File(mTempPhotoPath);
//                    mImageInfomationA.setImageURI(Uri.fromFile(temp));
//                    mDestinationUri = Uri.fromFile(new File(this.getCacheDir(), "cropImage.jpeg"));
//                    startCropActivity(Uri.fromFile(temp));
                    break;
                case CAMERA_REQUEST_CODE_2:
                    Bundle bundle2 = data.getExtras();
                    Bitmap bitmap2 = (Bitmap) bundle2.get("data");
                    try {
                        saveFileB(bitmap2,"informationB.jpeg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mImageInfomationB.setImageBitmap(bitmap2);
                    break;
                case CAMERA_REQUEST_CODE_3:
                    Bundle bundle3 = data.getExtras();
                    Bitmap bitmap3 = (Bitmap) bundle3.get("data");
                    try {
                        saveFileC(bitmap3,"informationBanshen.jpeg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mImageInfomationBanshen.setImageBitmap(bitmap3);
                    break;
                case CAMERA_REQUEST_CODE_4:
                    Bundle bundle4 = data.getExtras();
                    Bitmap bitmap4 = (Bitmap) bundle4.get("data");
                    try {
                        saveFileD(bitmap4,"informationStudent.jpeg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mImageInfomationStudent.setImageBitmap(bitmap4);
                    break;
                // 直接从相册获取
                case GALLERY_REQUEST_CODE_1:
                    mGetPhotoPath=data.getData();
                    myCaptureFileA = new File(String.valueOf(mGetPhotoPath));
                    mImageInfomationA.setImageURI(mGetPhotoPath);
//                    startCropActivity(data.getData());
                    break;
                case GALLERY_REQUEST_CODE_2:
                    mGetPhotoPath=data.getData();
                    myCaptureFileB = new File(String.valueOf(mGetPhotoPath));
                    mImageInfomationB.setImageURI(mGetPhotoPath);
                    break;
                case GALLERY_REQUEST_CODE_3:
                    mGetPhotoPath=data.getData();
                    myCaptureFileC = new File(String.valueOf(mGetPhotoPath));
                    mImageInfomationBanshen.setImageURI(mGetPhotoPath);
                    break;
                case GALLERY_REQUEST_CODE_4:
                    mGetPhotoPath=data.getData();
                    myCaptureFileD = new File(String.valueOf(mGetPhotoPath));
                    mImageInfomationStudent.setImageURI(mGetPhotoPath);
                    break;
                case UCrop.REQUEST_CROP:
                    // TODO: 裁剪图片结果
//                    deleteTempPhotoFile();
                    break;
                case UCrop.RESULT_ERROR:
                    // TODO: 裁剪图片错误
//                    deleteTempPhotoFile();
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
                //下面这句指定调用相机拍照后的照片存储的路径
//                takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTempPhotoPath)));
                switch (viewId){
                    case R.id.relativeLayout1:
                        //调用相机拍照
                        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takeIntent, CAMERA_REQUEST_CODE_1);
                        mImageInfomationA.setVisibility(View.VISIBLE);
                        break;
                    case R.id.relativeLayout2:
                        Intent takeIntent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takeIntent2, CAMERA_REQUEST_CODE_2);
                        mImageInfomationB.setVisibility(View.VISIBLE);
                        break;
                    case R.id.relativeLayout3:
                        Intent takeIntent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takeIntent3, CAMERA_REQUEST_CODE_3);
                        mImageInfomationBanshen.setVisibility(View.VISIBLE);
                        break;
                    case R.id.relativeLayout4:
                        Intent takeIntent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takeIntent4, CAMERA_REQUEST_CODE_4);
                        mImageInfomationStudent.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        secondChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(getContext(),"相册选取");
                dialog.dismiss();
                switch (viewId){
                    case R.id.relativeLayout1:
                        // "从相册选择"按钮被点击了
                        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(pickIntent, GALLERY_REQUEST_CODE_1);
                        mImageInfomationA.setVisibility(View.VISIBLE);
                        break;
                    case R.id.relativeLayout2:
                        Intent pickIntent2 = new Intent(Intent.ACTION_PICK, null);
                        pickIntent2.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(pickIntent2, GALLERY_REQUEST_CODE_2);
                        mImageInfomationB.setVisibility(View.VISIBLE);
                        break;
                    case R.id.relativeLayout3:
                        Intent pickIntent3 = new Intent(Intent.ACTION_PICK, null);
                        pickIntent3.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(pickIntent3, GALLERY_REQUEST_CODE_3);
                        mImageInfomationBanshen.setVisibility(View.VISIBLE);
                        break;
                    case R.id.relativeLayout4:
                        Intent pickIntent4 = new Intent(Intent.ACTION_PICK, null);
                        pickIntent4.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(pickIntent4, GALLERY_REQUEST_CODE_4);
                        mImageInfomationStudent.setVisibility(View.VISIBLE);
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


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startCropActivity(Uri uri) {
        UCrop.of(uri, mDestinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(512, 512)
                .withTargetActivity(CropActivity.class)
                .start(this);
    }


    /**
     * 处理剪切成功的返回值
     *
     * @param result
     */
    private void handleCropResult(Intent result) {
        deleteTempPhotoFile();
        final Uri resultUri = UCrop.getOutput(result);
        if (null != resultUri) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mActivity.getContentResolver(), resultUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mOnPictureSelectedListener.onPictureSelected(resultUri, bitmap);
        } else {
            Toast.makeText(this, "无法剪切选择图片", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 处理剪切失败的返回值
     *
     * @param result
     */
    private void handleCropError(Intent result) {
        deleteTempPhotoFile();
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            Log.e("InfomationActivity", "handleCropError: ", cropError);
            Toast.makeText(this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "无法剪切选择图片", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 删除拍照临时文件
     */
    private void deleteTempPhotoFile() {
        File tempFile = new File(mTempPhotoPath);
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
    }

    /**
     * 设置图片选择的回调监听
     *
     * @param l
     */
    public void setOnPictureSelectedListener(OnPictureSelectedListener l) {
        this.mOnPictureSelectedListener = l;
    }

    /**
     * 图片选择的回调接口
     */
    public interface OnPictureSelectedListener {
        /**
         * 图片选择的监听回调
         *
         * @param fileUri
         * @param bitmap
         */
        void onPictureSelected(Uri fileUri, Bitmap bitmap);
    }


    /**
     * 保存文件
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public void saveFileA(Bitmap bm, String fileName) throws IOException {
        String path = Environment.getExternalStorageDirectory() +"/revoeye/";
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        myCaptureFileA = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFileA));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

    public void saveFileB(Bitmap bm, String fileName) throws IOException {
        String path = Environment.getExternalStorageDirectory() +"/revoeye/";
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        myCaptureFileB = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFileB));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

    public void saveFileC(Bitmap bm, String fileName) throws IOException {
        String path = Environment.getExternalStorageDirectory() +"/revoeye/";
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        myCaptureFileC = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFileC));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

    public void saveFileD(Bitmap bm, String fileName) throws IOException {
        String path = Environment.getExternalStorageDirectory() +"/revoeye/";
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        myCaptureFileD = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFileD));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

}

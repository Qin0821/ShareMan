package com.youhu.shareman.shareman.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.crop.UCrop;
import com.youhu.shareman.shareman.MainActivity;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presentercoml.UserInfoPresenter;
import com.youhu.shareman.shareman.view.UserInfoView;
import com.youhu.shareman.shareman.ui.widget.SelfDialog;
import com.youhu.shareman.shareman.util.CheckUtils;
import com.youhu.shareman.shareman.util.JumpUtil;
import com.youhu.shareman.shareman.util.SharedPreferencesUtils;
import com.youhu.shareman.shareman.util.ToastUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.attr.maxHeight;
import static android.R.attr.maxWidth;

/**
 * Created by Touch on 2017/8/10.
 */

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.user_image_manger)
    RelativeLayout mUserInageManger;
    @BindView(R.id.user_nickname_manger)
    RelativeLayout mNicknameManger;
    @BindView(R.id.user_sex_manger)
    RelativeLayout mUserSexManger;
    @BindView(R.id.phone_number_manger)
    RelativeLayout mPhoneNumberManger;
    @BindView(R.id.address_manger)
    RelativeLayout mAddressManger;
    @BindView(R.id.change_account)
    Button mChangeAccount;
    @BindView(R.id.user_sex)
    TextView mUserSex;
    @BindView(R.id.user_nickname)
    TextView mUserNickname;

    //对话框
    private TextView firstChoose;
    private TextView secondChoose;
    private TextView chooseCancel;
    private View inflate;

    //自定义底部弹窗
    private Dialog dialog;
    //自定义中间弹窗
    private SelfDialog selfDialog;
    private static final int CAMERA_REQUEST_CODE = 11;
    private static final int GALLERY_REQUEST_CODE = 22;
    private String mTempPhotoPath = Environment.getExternalStorageDirectory() + File.separator + "photo.jpeg";

    UserInfoPresenter userInfoPresenter=new UserInfoPresenter();
    private String phoneNumber;
    private String token;

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_userinfo);
        super.initBind();
    }

    @Override
    protected void initUI() {
        setContext(this);

        phoneNumber = SharedPreferencesUtils.getPhoneNumber(this);
        token = SharedPreferencesUtils.getToken(this);

        userInfoPresenter.onCreate();
        userInfoPresenter.attachView(userInfoView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }


    @OnClick({R.id.back,R.id.user_image_manger,R.id.user_nickname_manger,R.id.user_sex_manger,
            R.id.phone_number_manger,R.id.address_manger,R.id.change_account})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.user_image_manger:
                dialogShow(R.id.user_image_manger);
                break;
            case R.id.user_nickname_manger:
                //初始化修改昵称弹窗
                initChangeNicknameDidalog();
                break;
            case R.id.user_sex_manger:
                dialogShow(R.id.user_sex_manger);
                break;
            case R.id.phone_number_manger:
                JumpUtil.overlay(this,ChangeNumberActivity.class);
                break;
            case R.id.address_manger:
                JumpUtil.overlay(this,AddressMangerActivity.class);
                break;
            case R.id.change_account:
                dialogShow(R.id.change_account);
                break;
        }
    }

    UserInfoView userInfoView=new UserInfoView() {
        @Override
        public void doChangeNickname(NormalModel nickNameData) {
            ToastUtils.show(getContext(),nickNameData.getMessage());
        }

        @Override
        public void doChangeSex(NormalModel sexData) {
            ToastUtils.show(getContext(),sexData.getMessage());
        }

        @Override
        public void showMessage(String message) {

        }
    };

    private void initChangeNicknameDidalog() {
        //初始化弹窗
        selfDialog=new SelfDialog(this);
        //设置点击事件
        selfDialog.setClearOnclickListener("", new SelfDialog.onClearOnclickListener() {
            @Override
            public void onClearClick() {
                //清空昵称
                selfDialog.getmChangeNickName().setText("");
            }
        });
        selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                //修改昵称并且上传到服务器
                String nickname=selfDialog.getmChangeNickName().getText().toString();
                //上传到服务器
                //非空判断
                if("".equals(nickname)){
                    ToastUtils.show(getContext(),"输入的昵称为空");
                }else{
//                    userInfoPresenter.doChangeNick("phoneNumber","fcfcf1962e40afc99ea1e84a01e6c001",nickname);
                    userInfoPresenter.doChangeNick(phoneNumber,token,nickname);
                    selfDialog.dismiss();
                }
            }
        });
        selfDialog.show();
    }

    //切割图片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == this.RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:   // 调用相机拍照
                    File temp = new File(mTempPhotoPath);
                    startCropActivity(Uri.fromFile(temp));
                    break;
                case GALLERY_REQUEST_CODE:  // 直接从相册获取
                    startCropActivity(data.getData());
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
    public void dialogShow(int viewId){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.choose_phone_dialog_layout, null);
        //初始化控件
        firstChoose = inflate.findViewById(R.id.take_photo);
        secondChoose = inflate.findViewById(R.id.choose_photo);
        chooseCancel = inflate.findViewById(R.id.choose_phone_cancel);
        switch (viewId){
            case R.id.user_image_manger:
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
                    }
                });
                secondChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.show(getContext(),"相册选取");
                        dialog.dismiss();
                        // 从相册选择"按钮被点击了
                        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(pickIntent, GALLERY_REQUEST_CODE);
                    }
                });
                break;
            case R.id.user_sex_manger:
                firstChoose.setText("男生");
                secondChoose.setText("女生");
                firstChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mUserSex.setText("男");
//                        userInfoPresenter.doChangeSex("15701236749","fcfcf1962e40afc99ea1e84a01e6c001",1);
                        userInfoPresenter.doChangeSex(phoneNumber,token,1);
                        dialog.dismiss();
                    }
                });
                secondChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mUserSex.setText("女");
                        userInfoPresenter.doChangeSex(phoneNumber,token,2);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.change_account:
                firstChoose.setText("退出账号");
                secondChoose.setText("切换账号");
                firstChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckUtils.clearLogin(getContext());
                        JumpUtil.overlay(getContext(), MainActivity.class);
                        ToastUtils.show(getContext(),"退出账号成功");
                        dialog.dismiss();
                    }
                });
                secondChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckUtils.clearLogin(getContext());
                        JumpUtil.overlay(getContext(),LoginActivity.class);
                        finish();
                        dialog.dismiss();
                    }
                });
                break;
        }
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
     */
    public void startCropActivity(Uri sourceUri){
        Uri mDestinationUri = Uri.fromFile(new File(getCacheDir(), "SampleCropImage.jpeg"));
        //第一个参数图片选择的地址，第二个参数裁剪完图片保存的地址
        UCrop.of(sourceUri, mDestinationUri)
                .withAspectRatio(2, 1)
                .withMaxResultSize(maxWidth, maxHeight)
                .start(this);

    }
}

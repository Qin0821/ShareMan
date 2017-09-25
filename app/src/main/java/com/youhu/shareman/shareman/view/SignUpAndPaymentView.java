package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.NormalModel;

/**
 * Created by Touch on 2017/9/21.
 */

public interface SignUpAndPaymentView extends BaseView {
    //获取协议返回
    void doOrderAgreement(NormalModel orderAgreementData);

    //上传签名返回
    void doUploadSign(NormalModel uploadSignData);
}

package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.NormalModel;

/**
 * Created by Touch on 2017/9/22.
 */

public interface InformationView extends BaseView {
    //上传身份信息
    void doUploadInformation(NormalModel uploadInformation);

    //上传身份证A面
    void doUploadIdCardA(NormalModel uploadIdCardAData);

    //上传身份证A面
    void doUploadIdCardB(NormalModel uploadIdCardBData);

    //上传身份证A面
    void doUploadBanshen(NormalModel uploadBanshenData);

    //上传身份证A面
    void doUploadStudent(NormalModel uploadStudentData);
}

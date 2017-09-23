package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.NormalModel;

/**
 * Created by Touch on 2017/9/20.
 */

public interface FeedBackView extends BaseView {
    //小报告接口返回
    void doSendAdvice(NormalModel normalModel);
}

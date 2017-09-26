package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.MessageModel;

import java.util.List;

/**
 * Created by Touch on 2017/9/26.
 */

public interface MessageView extends BaseView {
    void doMessage(BaseData<List<MessageModel>> messageData);
}

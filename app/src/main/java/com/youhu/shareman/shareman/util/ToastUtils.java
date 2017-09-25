package com.youhu.shareman.shareman.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wangx on 0004.
 */
public class ToastUtils {

    private static Toast sToast;

    /**
     * 静态toast
     * @param context
     * @param text
     */
    public static void show(Context context, String text){
        if (sToast == null) {
        sToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);

        }

        sToast.setText(text);
        sToast.show();
    }
}

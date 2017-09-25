package com.youhu.shareman.shareman.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by Touch on 2017/8/28.
 */

public class CheckUtils {
    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 验证是否登录
     */
    public static boolean isLogin(Context context) {
        SharedPreferences sp= context.getSharedPreferences("User",0);
        String mPhoneNumber=sp.getString("phoneNumber","");
        String mToken=sp.getString("token","");
        if("".equals(mPhoneNumber)||"".equals(mToken)){
            return false;
        }else{
            return true;
        }

    }

    /**
     * 保存登录信息
     */
    public static void  saveLogin(Context context,String phoneNumber,String token) {
        SharedPreferences sp= context.getSharedPreferences("User",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("phoneNumber",phoneNumber);
        editor.putString("token",token);
        editor.commit();
    }

    /**
     * 清楚登录信息
     */
    public static void  clearLogin(Context context) {
        SharedPreferences sp= context.getSharedPreferences("User",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("phoneNumber","");
        editor.putString("token","");
        editor.commit();
    }
}

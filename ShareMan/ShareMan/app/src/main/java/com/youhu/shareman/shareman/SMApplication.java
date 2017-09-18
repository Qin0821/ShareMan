package com.youhu.shareman.shareman;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.List;
import java.util.Random;


/**
 * Created by yangmu on 2017/6/29.
 */

public class SMApplication extends Application{

    private static SMApplication instance;

    /**
     * 用于接口调用
     */
//    public String imei;

    public SMApplication() {
        super();
        instance = this;
    }


    public static SMApplication getInstance() {
        if (instance == null)
            throw new IllegalStateException();
        return instance;
    }

    public static Context getAppContext() {
        if (instance == null)
            throw new IllegalStateException();
        return instance.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
//        imei = getIMEI();
    }



    /**
     * android 6.0以上的权限问题需要动态申请权限
     * 获取手机IMEI号
     */
    public String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        if(TextUtils.isEmpty(imei)){
            return "01234"+String.valueOf(new Random().nextInt(99999));
        }
        return imei;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}

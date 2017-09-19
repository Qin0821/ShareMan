package com.youhu.shareman.shareman;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import java.util.List;


/**
 * Created by yangmu on 2017/6/29.
 */

public class SMApplication extends Application{

//    private static SMApplication instance;


//    public SMApplication() {
//        super();
//        instance = this;
//    }


//    public static SMApplication getInstance() {
//        if (instance == null)
//            throw new IllegalStateException();
//        return instance;
//    }

//    public static Context getAppContext() {
//        if (instance == null)
//            throw new IllegalStateException();
//        return instance.getApplicationContext();
//    }

    private static Context context;

    public void onCreate(){
        super.onCreate();
        SMApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return SMApplication.context;
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

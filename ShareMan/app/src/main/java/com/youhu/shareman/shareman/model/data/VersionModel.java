package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/8/7.
 */

public class VersionModel {
    //从服务器获取apk的版本
    private String newApkVersion;

    //获取版本的方法
    public String getNewApkVersion() {
        return newApkVersion;
    }

    public void setNewApkVersion(String newApkVersion) {
        this.newApkVersion = newApkVersion;
    }
}

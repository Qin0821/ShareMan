package com.youhu.shareman.shareman.model.constant;

/**
 * Created by Touch on 2017/9/6.
 */

public class AppConfig {
    /**
     * BASE URL
     */
    public static final String BASE_URL = "http://123.207.70.168/";

    /**
     * 获取验证码
     */
    public static final String USER_LOGIN = "shareman/login/sendCode";

    /**
     * 验证登录
     */
    public static final String USER_LOGIN_CODE = "shareman/login/checkCode";

    /**
     * 共享分类
     */
    public static final String SHARE_BRAND = "shareman/product/findShowByBrand";

    /**
     * 修改昵称
     */
    public static final String CHANGE_NICKNAME = "shareman/user/updateNickname";

    /**
     * 修改性别
     */
    public static final String CHANGE_SEX = "shareman/user/updateGender";

}

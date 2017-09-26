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
     * IMAGE URL
     */
    public static final String IMAGE_URL = "http://123.207.70.168/image";

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
    public static final String SHARE_BRAND = "shareman/product/findShowByBrandId";

    /**
     * 修改昵称
     */
    public static final String CHANGE_NICKNAME = "shareman/user/updateNickname";

    /**
     * 修改性别
     */
    public static final String CHANGE_SEX = "shareman/user/updateGender";

    /**
     * 修改头像
     */
    public static final String CHANGE_USER_IMAGE = "shareman/user/updatePortrait";

    /**
     * 修改手机号码
     */
    public static final String CHANGE_PHONENUMBER = "shareman/user/updatePhoneNumberSendCode";

    /**
     * 修改手机号验证
     */
    public static final String NEW_PHONENUMBER = "shareman/user/updatePhoneNumberCheckCode";

    /**
     * 打小报告
     */
    public static final String SEND_ADVICE = "shareman/advice/sendAdvice";

    /**
     * 获取用户信息
     */
    public static final String GET_USER_INFO = "shareman/user/getPersonalProfile";

    /**
     * 获取收货地址
     */
    public static final String GET_POST_DETAIL = "shareman/postDetail/getPostDetail";

    /**
     * 添加收货地址
     */
    public static final String ADD_POST_DETAIL = "shareman/postDetail/addPostDetail";

    /**
     * 修改收货地址
     */
    public static final String UPDATE_POST_DETAIL = "shareman/postDetail/addPostDetail";

    /**
     * 删除收货地址
     */
    public static final String DELETE_POST_DETAIL = "shareman/postDetail/logicDeletePostDetail";

    /**
     * 共享订单
     */
    public static final String SHARE_ORDER = "shareman/order/getOrder";

    /**
     * 删除订单
     */
    public static final String SHARE_ORDER_DELETE = "shareman/order/deleteOrders";

    /**
     * 取消订单
     */
    public static final String SHARE_ORDER_CANCEL = "shareman/order/cancelOrders";

    /**
     * 查看协议
     */
    public static final String CHECK_AGREEMENT = "shareman/order/getAgreement";

    /**
     * 上传签名
     */
    public static final String UPLOAD_SIGN = "shareman/order/uploadSignature";

    /**
     * 查看个人信息
     */
    public static final String GET_INFORMATION = "shareman/user/getPersonalInfo";

    /**
     * 上传个人信息
     */
    public static final String UPLOAD_INFORMATION = "shareman/user/uploadPersonalInfo";

    /**
     * 上传身份证A面
     */
    public static final String UPLOAD_ID_CARD_A = "shareman/user/uploadIdCardA";

    /**
     * 上传身份证B面
     */
    public static final String UPLOAD_ID_CARD_B = "shareman/user/uploadIdCardB";

    /**
     * 上传身份证半身照
     */
    public static final String UPLOAD_BANSEHNG = "shareman/user/uploadIdCardOnHand";

    /**
     * 上传身份证学生照
     */
    public static final String UPLOAD_STUDENT = "shareman/user/uploadStudentIdCard";

    /**
     * 主页轮播图
     */
    public static final String MAIN_BANNER = "shareman/carousel/findCarousel";

    /**
     * 主页热门推荐
     */
    public static final String MAIN_HOT_RECOMENT = "shareman/category/findHot";

    /**
     * 主页分类
     */
    public static final String MAIN_PHONE_TYPE = "shareman/category/findCategory";

    /**
     * 主页系统消息
     */
    public static final String MAIN_MESSAGE = "shareman/sysMsg/findMessage";

    /**
     * 商品详情图预览
     */
    public static final String PRODUCT_DETAIL = "shareman/product/findProductByVersion";

    /**
     * 优惠券
     */
    public static final String VOUCHER = "shareman/voucher/findVoucher";

    /**
     * 使用优惠券
     */
    public static final String USE_VOUCHER = "shareman/voucher/selectVoucher";

    /**
     * 获取芝麻信用
     */
    public static final String ZHI_MA = "shareman/pay/getZhimaScoreResult";

}

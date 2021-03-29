package com.haitian.servicestaffapp.app;

/**
 * @Author JenSenLeung.
 * @Time 2018/8/1 下午 5:33.
 * @Description This is Constants.
 */
public interface Constants {


    //字体大小
    String ZITISIZE = "1";

    String USERID = "USERID";
    String USER_NAME = "USER_NAME";
    String USER_NICKNAME="USER_NICKNAME";
    String USER_CARD_NAME = "USER_CARD_NAME";
    String USER_PWD = "USER_PWD";
    String USER_AFFILIATE = "USER_AFFILIATE";
    String USER_ROLE_ID = "USER_ROLE_ID";
    String USER_DEPT = "USER_DEPT";
    String USER_MESSAGEID = "USER_MESSAGEID";
    String USER_MESSAGE = "USER_MESSAGE";
    String USER_TYPE = "USER_TYPE";
    String USER_DEPARTMENT = "USER_DEPARTMENT";
    String USER_ZHENGJIAN = "USER_ZHENGJIAN";
    String USER_NEWUSER_PWD = "USER_NEWUSER_PWD";
    String SHOUCHIZHAOP = "SHOWCHIZHAOP";
    String HUOJIANGZHENGSHU = "HUOJIANGZHENGSHU";
    String SHOUCHI = "SHOUCHI";
    String HUOJIANGZHENGS = "HUOJIANGZHENGS";


    String BASE_URL = " http://111.17.215.37:817/";

    String Host = BASE_URL + "waiter/";


    //账号密码登录
    String LOGIN = Host + "login/idlogin";
    //忘记密码
    String FORGETPASSWORD = Host + "login/forgetpwd";
    //注册-全部部门
    String REGISTER_BUMEN = Host + "login/bumenall";
    //注册-服务类型
    String REGISTER_FUWU = Host + "login/fuwuall";
    //获取验证码
    String GETCODE = Host + "login/sendmessage";
    //验证码登录
    String CODELOGIN = Host + "login/phonelogin";
    //注册新用户
    String REGISTER = Host + "login/newuser";
    //通知公告
    String TONGZHILIST = Host + "login/tongzhigonggao";
    //通知公告详情
    String TONGZHIINFO = Host + "login/tongzhidetails";
}

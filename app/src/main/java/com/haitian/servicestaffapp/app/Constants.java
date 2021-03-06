package com.haitian.servicestaffapp.app;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;

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
    //个人信息
    String USERDATA = Host + "login/profiles";
    //修改个人信息
    String UPDATEUSERDATA = Host + "/login/upprofiles";
    //新工单查看
    String NEWXINGONGDAN = Host + "gongdan/qiangdan";
    //接单
    String GONGDANJIEDAN = Host + "gongdan/jiedan";
    //转单
    String GONGDANZHUANDAN = Host + "gongdan/zhuanchu";
    //GPS定位上传
    String GPSUP = Host + "gongdan/gps";
    //进行中的工单 拒绝按钮
    String GONGDANINGJUJUE = Host + "gongdan/jujue";
    //执行工单接口
    String ZHIXINGGONGDAN = Host + "gongdan/zhixing";
    //服务统计
    String TONGJIALL = Host + "gongdan/fuwutongji";
    //服务统计增长率
    String TONGJIZENGZHANG = Host + "gongdan/zengzhanglv";
    //走势图 折线图
    String TONGJIZHEXIANTU = Host + "gongdan/zoushitu";
    //已接单工单查看list
    String YIJIEDANGONGDANLIST = Host + "gongdan/jiedanchakan";
    //客户管理
    String KEHUGUANLILIST = Host + "gongdan//kehuguanli";

    //抢单查询页面
    String QIANGDDAN_LIST = Host + "gongdan/qiangdan";
    //抢单接单按钮
    String QIANGDDAN_BT = Host + "gongdan/qiangdanjiedan";
    //已完成工单
    String YIWANCHANGGONGDAN = Host + "gongdan/yiwancheng?";
    //评价
    String PINGJIA = Host + "gongdan/pingjiazanshi";
    //回复
    String PINGJIAHUIFU = Host + "gongdan/pingjiahuifu";
    //工单进行中
    String GONGDANING = Host + "gongdan/gongdansee";
    //工单已接单
    String GONGDANYIJIEDAN = Host + "gongdan/jiedanchakan";

    //修改密码
    String UPDATEPASSWORD = Host + "login/resetpwd";
    //版本更新
    String UPDATEVERSION = Host + "upversion/update";

}

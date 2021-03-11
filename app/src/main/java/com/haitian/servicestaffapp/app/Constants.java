package com.haitian.servicestaffapp.app;

/**
 * @Author JenSenLeung.
 * @Time 2018/8/1 下午 5:33.
 * @Description This is Constants.
 */
public interface Constants {


    //字体大小
    String ZITISIZE = "1";
    String SHEQUADDRESS = "SHEQUADDRESS";
    String SHEQUMOBILE = "SHEQUMOBILE";
    String USERID = "USERID";       //老人登录为编号   亲属登录为手机号
    String USERTYPE = "USERTYPE";   //1老人 2 亲属
    String OLDID = "OLDID";  //老人id
    String OLDNAME = "OLDNAME";    //老人名字
    String SEX = "SEX";         //性别
    String AGE = "AGE";         //年龄
    String MOBILE = "MOBILE";   //手机号
    String PHOTOURL = "PHOTOURL";        //头像
    String OLDSHEQUID = "OLDSHEQUID";   //老人社区id
    String OLDYIJIANHUJIAOMOBILE = "OLDYIJIANHUJIAOMOBILE";     //老人一键呼叫手机号
    String EVENTBUS_TYEPE = "type";
    String ADMIN = "ADMIN";  //登录手机号
    String PASSWORD = "PASSWORD";  //密码

    String LANGUAGE = "CHINA";


    String BASE_URL = "http://111.17.215.37:813/";  //测试

    String Host = BASE_URL + "yanglaojujia/api/app_yanglaojujia?";

    //省id
    String SHENGID = "SHENGID";
    //市id
    String SHIID = "SHIID";
    //区id
    String QUID = "QUID";

    //百度地图key
    String BAIDUDITUKEY = "GEayeVFZ2CXIOWiHLsrvtFLbD0LDLIok";

    //登录
    String LOGIN = Host + "act=loginAll&data=";
    //忘记密码发送验证码
    String FORGETPASSWORD_CODE = Host + "act=sendUserSMSCode&data=";
    //忘记密码
    String FORGETPASSWORD = Host + "act=updateUserPasswordBySmsCode&data=";
    //首页标题和轮播图
    String HOMETITLEORBANNER = Host + "act=shequadvertising&data=";
    //老人详情
    String OLDINFO = Host + "act=oldkey&data=";
    //修改老人信息
    String UPDATAOLDINFO = Host + "act=oldupdate&data=";
    //联系我们
    String ABOUTUS = Host + "act=getComInfo&data=";
    //意见反馈
    String YIJIANFANKUI = Host + "act=sendFeedback&data=";
    //修改密码
    String UPDATEPASSWORD = Host + "act=updateUserPassword&data=";
    //通知公告列表
    String TONGZHILIST = Host + "act=tongzhilist&data=";
    //通知消息详情
    String TONGZHIINFO = Host + "act=tongzhikey&data=";
    //通知报名
    String BAOMING = Host + "act=tongzhibaoming&data=";
    //心跳包
    String WEIDUMESSAGE = Host + "act=shouyexintiaobao&data=";
    //活动视图
    String HUODONGSHITU = Host + "act=shequhuodong&data=";
    //上传
    String USER_ICON = "http://111.17.215.37:813/yanglaojujia/Api/UploadServlet?";
    //生活点滴
    String SHENGHUODIANDI = Host + "act=getShenhuodiandiList&data=";
    //上传生活点滴
    String ADDSHENGHUODIANDI = Host + "act=sendShenhuodiandi&data=";
    //删除生活点滴
    String DELETESHENGHUODIANDI = Host + "act=deleteShenghuodiandi&data=";
    //人员绑定列表
    String RENYUANLIST = Host + "act=renyuanbangdinglist&data=";
    //添加人员
    String ADDRENYUAN = Host + "act=bangdinglaorenqinshu&data=";
    //人员绑定验证码
    String ADDBANGDINGYANZHENGCODE = Host + "act=sendSMSCode&data=";
    //修改推送
    String RENYUANGUANBITUISONG = Host + "act=updaterenyuanjieshouxinxi&data=";
    //删除
    String DELETERENYUAN = Host + "act=oldqinshudel&data=";
    //修改一键呼叫手机号
    String UPDATEHUJIAOMOBILE = Host + "act=updateoldyijianhujiaoshoujihao&data=";
    //新版本
    String BANBENGENGXIN = Host + "act=checkUpdate&data=";
    //健康档案信息
    String GETJIANKANGDANGANINFO = Host + "act=findoldjiankangdangan&data=";
    //健康档案新增或修改
    String ADDORUPDATEDANGAN = Host + "act=oldjiakangdanganadd&data=";
    //健康档案上传文件
    String DANGANSHANGCHUANWENJIAN = Host + "act=oldjiakangdanganupdate&data=";
    //删除健康文档附件
    String DELETEWENDANGFUJIAN = Host + "act=oldjiakangdangandel&data=";
    //报警详情
    String BAOJINGINFO = Host + "act=baojingxinxi&data=";
    //报警信息一键呼叫列表
    String YIJIANHUJIAOLIST = Host + "act=sosyijianhujiaolist&data=";
    //助食筷列表
    String ZHUSHIKUAILIST = Host + "act=zhushikuai&data=";
    //手功能列表
    String SHOUGONGNENGLIST = Host + "act=shougongneng&data=";
    //手指伸展列表
    String SHOUZHISHENZHANLIST = Host + "act=shouzhishenzhan&data=";
    //脑机
    String NAOJILIST = Host + "act=naojijiekoulist&data=";
    //评估数据训练前
    String PINGGUSHUJUQIANLIST = Host + "act=pinggushujulist&data=";
    //评估数据训练后
    String PINGGUSHUJUHOULIST = Host + "act=pinggushujuxunlianhoulist&data=";
    //脑电数据
    String NAODIANLIST = Host + "act=naodianshujulist&data=";
    //关卡
    String GUANKA = Host +"act=changjingshujuguanqialeilist&data=";
    //有关卡列表
    String YOUGUANKALIST = Host + "act=youxilist&data=";
    //无关卡列表
    String WUGUANKALIST = Host + "act=youxifeiguanqialeilist&data=";
    //检测设备列表
    String JIANCESHEBEILIST = Host + "act=soslistsb&data=";
}

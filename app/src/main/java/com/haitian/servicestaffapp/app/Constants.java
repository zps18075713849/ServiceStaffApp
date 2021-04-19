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
    /**
     * 文件存储相关常量
     */
    public static class SDCardConstants {

        private static final String TAG = "SDCardConstants";
        /**
         * 转码文件后缀
         */
        public final static String TRANSCODE_SUFFIX = ".mp4_transcode";

        /**
         * 裁剪文件后缀
         */
        public final static String CROP_SUFFIX = "-crop.mp4";

        /**
         * 合成文件后缀
         */
        public final static String COMPOSE_SUFFIX = "-compose.mp4";

        /**
         * 裁剪 & 录制 & 转码输出文件的目录
         * android Q 版本默认路径
         * /storage/emulated/0/Android/data/包名/files/Media/
         * android Q 以下版本默认"/sdcard/DCIM/Camera/"
         */
//        public static String getDir(Context context) {
//            String dir;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                dir = context.getExternalFilesDir("") + File.separator + "Media" + File.separator;
//            } else {
//                dir = Environment.getExternalStorageDirectory() + File.separator + "DCIM"
//                        + File.separator + "Camera" + File.separator;
//            }
//            File file = new File(dir);
//            if (!file.exists()) {
//                //noinspection ResultOfMethodCallIgnored
//                file.mkdirs();
//            }
//            return dir;
//        }

        /**
         * 获取外部缓存目录 版本默认"/storage/emulated/0/Android/data/包名/file/Cache/svideo"
         *
         * @param context Context
         * @return string path
         */
        public static String getCacheDir(Context context) {
            File cacheDir = new File(context.getExternalCacheDir(), "svideo");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            return cacheDir.exists() ? cacheDir.getPath() : "";
        }

        /**
         * 清空外部缓存目录文件 "/storage/emulated/0/Android/data/包名/file/Cache/svideo"
         *
         * @param context Context
         */
        public static void clearCacheDir(Context context) {
            final File cacheDir = new File(context.getExternalCacheDir(), "svideo");
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    boolean b = deleteFile(cacheDir);
                    Log.i(TAG, "delete cache file " + b);
                }
            });
        }

        /**
         * 递归删除文件/目录
         * @param file File
         */
        private static boolean deleteFile(File file) {
            if (file == null || !file.exists()) {
                return true;
            }

            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files == null) {
                    return true;
                }
                for (File f : files) {
                    deleteFile(f);
                }
            }
            return file.delete();
        }

    }

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
    String USERDATA = Host + "/login/profiles";
    //修改个人信息
    String UPDATEUSERDATA = Host + "/login/upprofiles";
    //新工单查看
    String NEWXINGONGDAN = Host + "gongdan/gongdansee";
    //接单
    String GONGDANJIEDAN = Host + "gongdan/jiedan";
    //转单
    String GONGDANZHUANDAN = Host + "gongdan/zhuanchu";
}

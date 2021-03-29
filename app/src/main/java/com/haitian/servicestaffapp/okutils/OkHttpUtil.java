package com.haitian.servicestaffapp.okutils;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.haitian.servicestaffapp.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {
    private static OkHttpUtil mokHttpUtil;
    private Handler mhandler;
    private OkHttpClient mokHttpClient;
    private byte[] mEncodedPassword;
    public static String AppVersion = "1.0";

    private OkHttpUtil() {

        //验证
        mEncodedPassword = ("wlcx:sqVLVvO5YgzpF3Qr").getBytes();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mhandler = new Handler();
        mokHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public static OkHttpUtil getInteace() {
        if (mokHttpUtil == null) {
            synchronized (OkHttpUtil.class) {
                mokHttpUtil = new OkHttpUtil();
            }
        }
        return mokHttpUtil;
    }


    public void doPost(String url, Map<String, Object> map, Activity activity, final OkCallBack okCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            map.put(key, map.get(key));
        }
        String jsonstr= JSON.toJSONString(map);
        RequestBody requestbody = RequestBody.create(MediaType.parse("application/json"), jsonstr);

//        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .url(url)
                .post(requestbody)
                .build();

        LogUtil.e("Url："+request.url());
        Call call = mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mokHttpUtil != null) {
                    okCallBack.onFauile(new Exception(e));
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (mokHttpUtil != null) {
                    String json = response.body().string();
                    LogUtil.e(json);
                    okCallBack.onResponse(json);
                    return;
                }
            }
        });
    }


    public void NewsInfo(String url, Map<String, Object> map, Activity activity, final OkCallBack okCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            map.put(key, map.get(key));
        }
        String jsonstr= JSON.toJSONString(map);
        RequestBody requestbody = RequestBody.create(MediaType.parse("form-data"), jsonstr);

//        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .addHeader("Content-Type", "form-data")
                .url(url)
                .post(requestbody)
                .build();

        LogUtil.e("Url："+request.url());
        Call call = mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mokHttpUtil != null) {
                    okCallBack.onFauile(new Exception(e));
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (mokHttpUtil != null) {
                    String json = response.body().string();
                    LogUtil.e(json);
                    okCallBack.onResponse(json);
                    return;
                }
            }
        });
    }


    protected void post_file(final String url, final Map<String, Object> map, File file) {
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(file != null){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("headImage", file.getName(), body);
        }

        Request request = new Request.Builder().url("请求地址").post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("lfq" ,"onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String str = response.body().string();
                    Log.i("lfq", response.message() + " , body " + str);



                } else {
                    Log.i("lfq" ,response.message() + " error : body " + response.body().string());
                }
            }
        });

    }

    public interface OkCallBack {
        void onFauile(Exception e);

        void onResponse(String json);
    }
}

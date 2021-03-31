package com.haitian.servicestaffapp.okutils;

import android.util.Log;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.utils.JSONUtil;
import com.haitian.servicestaffapp.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;


public class OkHttpUtils {

    /**
     * 针对RXJava专用同步网络请求 get
     *
     * @param url
     * @param params
     */
    public static PdfReturnBean initPdfGetHttp(String url, Map<String, Object> params) {
        final String sendJson = JSONUtil.parseMapToJson(params);
        url = url + sendJson;
        if (sendJson == null) {
            return null;
        }
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = DoctorBaseAppliction.sOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                LogUtil.i(TAG, "url :" + url + "\nparams :" + sendJson + "\n返回结果 :" + result);
                PdfReturnBean bean = JSONUtil.parseJsonToBean(result,PdfReturnBean.class);
                return bean;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 针对RXJava专用同步网络请求 get
     *
     * @param url
     * @param params
     */
    public static String initGetHttp(String url, Map<String, Object> params){
        if(params!=null){
            final String sendJson = JSONUtil.parseMapToJson(params);
            url = url + sendJson;
            if (sendJson == null) {
                return null;
            }
        }

        Log.e("okhttp---->>", "initGetHttp: "+url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = DoctorBaseAppliction.sOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
//                LogUtil.d("okhttp---->>" + result);
                return result;
            }else{
                LogUtil.e("okhttp------失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param key_value 参数键值对, key是参数名,value是参数内容, 原先string就直接放进去, 原先是结构体就转成string...
     */
    public static String initPostRequest(String url, Map<String, Object> key_value) {
        if (key_value == null || key_value.isEmpty()) {
            throw new IllegalArgumentException("param is null");
        }
        final String sendJson = JSONUtil.parseMapToJson(key_value);
        RequestBody requestBodyPost = RequestBody.create(MediaType.parse("application/json"), sendJson);;
        Request request = new Request.Builder()
                .url(url)
                .post(requestBodyPost)
                .build();
        try {
            LogUtil.e(TAG, "initPostRequest: "+request);
            Response response = DoctorBaseAppliction.sOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //上传头像
    public static String initUpLoad(String url, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        LogUtil.e("wubj---->>>:网路请求地址:" + url);
        try {
            Response response = DoctorBaseAppliction.sOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static BaseReturnBean optBaseReturnBean(String result) {
        final BaseReturnBean returnBean = new BaseReturnBean();
        try {
            JSONObject proDataJSON = new JSONObject(result);
            returnBean.code = proDataJSON.optString("status");
            returnBean.desc = "";
            String data = "";
            if (proDataJSON.has("data")) {
                data = proDataJSON.optString("data");
            } else if (proDataJSON.has("msgList")) {
                data = proDataJSON.optString("msgList");
            }  else if (proDataJSON.has("result")) {
                data = proDataJSON.optString("result");
            }
            returnBean.data = data;
            LogUtil.e("--------------returnBean.data>>" + data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnBean;
    }



}

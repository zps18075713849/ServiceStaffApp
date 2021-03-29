package com.haitian.servicestaffapp.okutils;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.bean.CodeMessageBean;
import com.haitian.servicestaffapp.bean.DataSettingBean;
import com.haitian.servicestaffapp.utils.LogUtil;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DoctorNetService {

    //
    public static void requestDataSetting(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initPostRequest(url, param);
                Gson gson = new Gson();
                LogUtil.e("接口数据" + result);
                DataSettingBean infoBean = gson.fromJson(result, DataSettingBean.class);
                if(result!=null){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
   //修改密碼
    public static void requestUpdatePw(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initPostRequest(url, param);
                Gson gson = new Gson();
                LogUtil.e("接口数据" + result);
                CodeMessageBean infoBean = gson.fromJson(result, CodeMessageBean.class);
                if(result!=null){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }



}


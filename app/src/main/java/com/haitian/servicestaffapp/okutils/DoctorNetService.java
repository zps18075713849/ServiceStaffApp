package com.haitian.servicestaffapp.okutils;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.utils.LogUtil;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DoctorNetService {

//    //医生详情
//    public static void requestDoctorInfo(final String url, final Map<String, Object> param, final
//    NetWorkRequestInterface requestInterface) {
//        Observable.create(new Observable.OnSubscribe<Object>() {
//            @Override
//            public void call(Subscriber<? super Object> subscriber) {
//                String result = OkHttpUtils.initGetHttp(url, param);
//                Gson gson = new Gson();
//                LogUtil.e("接口数据" + result);
//                DoctorInfoBean infoBean = gson.fromJson(result, DoctorInfoBean.class);
////                if(result!=null || infoBean.getCode().equals("0")){
//                subscriber.onNext(infoBean);
////                }else{
////                    subscriber.onError(new Exception());
////                }
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                requestInterface.onError(throwable);
//            }
//
//            @Override
//            public void onNext(Object info) {
//                requestInterface.onNext(info);
//            }
//        });
//    }



}


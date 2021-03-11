package com.haitian.servicestaffapp.utils;//package com.wd.yiyangyun.utils;
//
//
//import com.wd.patientyyy.app.Constants;
//
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * @Author JenSenLeung.
// * @Time 2018/8/1 下午 5:35.
// * @Description This is RetrofitManager.
// */
//public class RetrofitManager {
//
//    private final Retrofit retrofit;
//
//    private static class SingleHolder {
//        private static final RetrofitManager _INSTANT = new RetrofitManager(Constants.BASE_URL);
//    }
//
//    public static RetrofitManager getDefault() {
//        return SingleHolder._INSTANT;
//    }
//
//
//    public RetrofitManager(String baseUrl) {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
////                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(buildOKHttpClient())
//                .build();
//    }
//
//    private OkHttpClient buildOKHttpClient() {
//        //缓存路径
////        File cacheFile = new File(DoctorBaseAppliction.getAppContext().getCacheDir().getAbsolutePath(), "HttpCache");
////        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);//缓存文件为10MB
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        //公共参数
////        Map<String, String> map = new HashMap<>();
////        map.put("userId", "18");
////        map.put("sessionId", "15320748258726");
////        PublicParamInterceptor publicParamInterceptor = new PublicParamInterceptor(map);
//        return new OkHttpClient.Builder()
//                //日志拦截器
//                .addInterceptor(interceptor)
//                //公共请求参数
////                .addInterceptor(new Interceptor() {
//////                    @Override
//////                    public Response intercept(Chain chain) throws IOException {
//////                        Request original = chain.request();
//////
//////                        // Request customization: add request headers
//////                        Request.Builder requestBuilder = original.newBuilder()
//////                                .addHeader("ak", "0110010010000")
//////                                .addHeader("Content-Type", "application/x-www-form-urlencoded");
//////
//////                        Request request = requestBuilder.build();
//////                        return chain.proceed(request);
//////                    }
//////                })
//                //缓存
////                .addInterceptor(new Interceptor() {
////                    @Override
////                    public Response intercept(Chain chain) throws IOException {
////                        int maxAge = 60 * 60; // 有网络时 设置缓存超时时间1个小时
////                        int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
////                        Request request = chain.request();
////                        if (HttpUtils.isNetworkReachable(DoctorBaseAppliction.getAppContext())) {
////                            request = request.newBuilder()
////                                    .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
////                                    .build();
////                        } else {
////                            request = request.newBuilder()
////                                    .cacheControl(CacheControl.FORCE_CACHE)//无网络时只从缓存中读取
////                                    .build();
////                        }
////                        Response response = chain.proceed(request);
////                        if (HttpUtils.isNetworkReachable(DoctorBaseAppliction.getAppContext())) {
////                            response = response.newBuilder()
////                                    .removeHeader("Pragma")
////                                    .header("Cache-Control", "public, max-age=" + maxAge)
////                                    .build();
////                        } else {
////                            response = response.newBuilder()
////                                    .removeHeader("Pragma")
////                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
////                                    .build();
////                        }
////                        return response;
////                    }
////
////                })
//                .connectTimeout(1, TimeUnit.SECONDS)
////                .cache(cache)
//                .build();
//    }
//
//    public <T> T create(Class<T> tClass) {
//        return retrofit.create(tClass);
//    }
//
//
//
//}

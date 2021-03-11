package com.haitian.servicestaffapp.utils;

public class SynchniceUtil {

//    //请求医生信息
//    public static DoctorInfoBean requestDoctorId(String loginName) {
//        final Map<String, Object> map = new HashMap<>();
//        map.put("loginName", loginName);
//        String json = JSONUtil.parseMapToJson(map);
//        String url = Constants.DOCTOR_ID+json;
//        String a = getSyncBusiness(url);
//        com.google.gson.jpush.Gson gson = new com.google.gson.jpush.Gson();
//        DoctorInfoBean bingchengListBean = gson.fromJson(a,DoctorInfoBean.class);
//        return bingchengListBean;
//    }
//    //请求回复次数
//    public static ReplyNumBean requestReplyNum2(String userID) {
//        final Map<String, Object> map = new HashMap<>();
//        map.put("patientID", DoctorBaseAppliction.spUtil.getString(com.haitian.interenthospitaldoctor.app.Constants.PATIENT_ID,""));
//        map.put("userID", userID);
//        String json = JSONUtil.parseMapToJson(map);
//        String url = Constants.MessageCount+json;
//        String a = getSyncBusiness(url);
//        com.google.gson.jpush.Gson gson = new com.google.gson.jpush.Gson();
//        ReplyNumBean bingchengListBean = gson.fromJson(a,ReplyNumBean.class);
//        return bingchengListBean;
//    }
//
//    public static DutyBean requestDuty() {
//        final Map<String, Object> map = new HashMap<>();
//        String url = Constants.ZHICHENG_LiST;
//        String a = getSyncBusiness(url);
//        Gson gson = new Gson();
//        DutyBean bingchengListBean = gson.fromJson(a,DutyBean.class);
//        return bingchengListBean;
//    }
//
//    public static KeShiBean requestKeShi(String departmentPid) {
//        final Map<String, Object> map = new HashMap<>();
//        map.put("departmentPid", departmentPid);
//        String json = JSONUtil.parseMapToJson(map);
//        String url = Constants.KESHI_LIST+json;
//        String a = getSyncBusiness(url);
//        Gson gson = new Gson();
//        KeShiBean bingchengListBean = gson.fromJson(a,KeShiBean.class);
//        return bingchengListBean;
//    }
//
//    public static KeShiBean requestErJiKeShi(String departmentPid) {
//        final Map<String, Object> map = new HashMap<>();
//        map.put("departmentPid", departmentPid);
//        String json = JSONUtil.parseMapToJson(map);
//        String url = Constants.KESHI_LIST+json;
//        String a = getSyncBusiness(url);
//        Gson gson = new Gson();
//        KeShiBean bingchengListBean = gson.fromJson(a,KeShiBean.class);
//        return bingchengListBean;
//    }
//
//    public static MessageBean requestCount() {
//        final Map<String, Object> map = new HashMap<>();
//        map.put("userType", 1);
//        map.put("userId", DoctorBaseAppliction.spUtil.getString(Constants.DOCTOR_ID,""));
//        map.put("pageNo", "1");
//        String json = JSONUtil.parseMapToJson(map);
//        String url = Constants.GET_MESSAGE+json;
//        String a = getSyncBusiness(url);
//        Gson gson = new Gson();
//        MessageBean bingchengListBean = gson.fromJson(a,MessageBean.class);
//        return bingchengListBean;
//    }
//
//    public static String getSyncBusiness(final String url){
//        try {
//            FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
//
//                @Override
//                public String call() throws Exception {
//                    URL u = new URL(url);
//                    LogUtil.e(TAG, "requestBingChengList: "+u);
//                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
//                    connection.setDoInput(true);
//                    connection.setRequestMethod("GET");
//                    connection.connect();
//                    InputStream in = connection.getInputStream();
//                    BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf8"));
//                    final StringBuilder sb = new StringBuilder();
//                    String line = null;
//                    while((line = br.readLine())!=null){
//                        sb.append(line);
//                    }
//                    return sb.toString();
//                }
//            });
//
//            new Thread(task).start();
//
//            return task.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return url;
//    }
//
//    public static MyPlanListBean myPlanListBean(int pageNo, int pageCount) {
//        final Map<String, Object> map = new HashMap<>();
//        map.put("pageNo", pageNo + "");
//        map.put("pageCount", pageCount + "");
//        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
//
//        String json = JSONUtil.parseMapToJson(map);
//        String url = Constants.SELECT_FOLLOW_PLAN_LIST+json;
//        String a = getSyncBusiness(url);
//        Gson gson = new Gson();
//        BaseReturnBean bean = optBaseReturnBean(a);
//        Log.e("xxxxxxxxxxx", "发布出诊"+a);
//        MyPlanListBean myPlanListBean = gson.fromJson(bean.data,MyPlanListBean.class);
//        return myPlanListBean;
//    }
//
//    public static SelectPatient_Bean requestPatient(String departmentPid) {
//        final Map<String, Object> map = new HashMap<>();
//        map.put("doctorId", departmentPid);
//        String json = JSONUtil.parseMapToJson(map);
//        String url = Constants.SELECTPATIENT+json;
//        String a = getSyncBusiness(url);
//        Gson gson = new Gson();
//        SelectPatient_Bean selectPatient_bean = gson.fromJson(a,SelectPatient_Bean.class);
//        return selectPatient_bean;
//    }

}

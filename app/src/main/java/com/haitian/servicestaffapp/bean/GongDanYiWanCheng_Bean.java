package com.haitian.servicestaffapp.bean;

import java.util.List;

public class GongDanYiWanCheng_Bean {

    /**
     * code : 20041
     * data : [{"startTime":1621825980000,"goodstypeName":"美食配送","xingji":"5","old_phone":"18854806444","fuwu_value":"土豆片","dingdanlaiyuan":"2","waiter_address":"上海静安区i我 那块 13301325631","endTime":1621825980000,"cost":0.01},{"startTime":1684661160000,"goodstypeName":"美食配送","xingji":"5","old_phone":"15588522851","fuwu_value":"炒锅饭","dingdanlaiyuan":"2","waiter_address":"上海静安区i我 那块 13301325631","endTime":1684661160000,"cost":0.01},{"startTime":1684653900000,"goodstypeName":"美食配送","xingji":"5","old_phone":"18854806444","fuwu_value":"炒锅饭","dingdanlaiyuan":"2","waiter_address":"上海静安区i我 那块 13301325631","endTime":1684657500000,"cost":0.01},{"startTime":1622187960000,"goodstypeName":"美食配送","xingji":"5","old_phone":"18854806444","fuwu_value":"炒锅饭","dingdanlaiyuan":"2","waiter_address":"山西长治市襄垣县测试地址 测试1 13611127052","endTime":1622187960000,"cost":0.01},{"startTime":1716631980000,"goodstypeName":"家政保洁","xingji":"5","old_phone":"18854806444","fuwu_value":"打扫卫生","dingdanlaiyuan":"2","waiter_address":"江苏苏州市张家港市要去 张先生 13611127052","endTime":1716631980000,"cost":0.01},{"startTime":1684499640000,"goodstypeName":"美食配送","xingji":"5","old_phone":"13611127052","fuwu_value":"炒锅饭","dingdanlaiyuan":"2","waiter_address":"江苏苏州市张家港市要去 张先生 13611127052","endTime":1684506840000,"cost":0.01},{"startTime":1622018340000,"goodstypeName":"美食配送","xingji":"5","old_phone":"18854806444","fuwu_value":"土豆片","dingdanlaiyuan":"2","waiter_address":"江苏苏州市张家港市要去 张先生 13611127052","endTime":1622018340000,"cost":0.01},{"startTime":1653381300000,"goodstypeName":"美食配送","xingji":"5","old_phone":"18854806444","fuwu_value":"炒锅饭","dingdanlaiyuan":"2","waiter_address":"江苏苏州市张家港市要去 张先生 13611127052","endTime":1653370500000,"cost":0.01}]
     * message : null
     */

    private int code;
    private Object message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * startTime : 1621825980000
         * goodstypeName : 美食配送
         * xingji : 5
         * old_phone : 18854806444
         * fuwu_value : 土豆片
         * dingdanlaiyuan : 2
         * waiter_address : 上海静安区i我 那块 13301325631
         * endTime : 1621825980000
         * cost : 0.01
         */

        private long startTime;
        private String goodstypeName;
        private String xingji;
        private String old_phone;
        private String fuwu_value;
        private String dingdanlaiyuan;
        private String waiter_address;
        private long endTime;
        private double cost;

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public String getGoodstypeName() {
            return goodstypeName;
        }

        public void setGoodstypeName(String goodstypeName) {
            this.goodstypeName = goodstypeName;
        }

        public String getXingji() {
            return xingji;
        }

        public void setXingji(String xingji) {
            this.xingji = xingji;
        }

        public String getOld_phone() {
            return old_phone;
        }

        public void setOld_phone(String old_phone) {
            this.old_phone = old_phone;
        }

        public String getFuwu_value() {
            return fuwu_value;
        }

        public void setFuwu_value(String fuwu_value) {
            this.fuwu_value = fuwu_value;
        }

        public String getDingdanlaiyuan() {
            return dingdanlaiyuan;
        }

        public void setDingdanlaiyuan(String dingdanlaiyuan) {
            this.dingdanlaiyuan = dingdanlaiyuan;
        }

        public String getWaiter_address() {
            return waiter_address;
        }

        public void setWaiter_address(String waiter_address) {
            this.waiter_address = waiter_address;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }
    }
}

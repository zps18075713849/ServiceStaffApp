package com.haitian.servicestaffapp.bean;

import java.util.List;

public class GongDanJinXingZhong_Bean {

    /**
     * code : 20041
     * data : [{"id":"4958358621544d1dac99b6a608e8d3e2","startTime":1685005860000,"goodstypeName":"美食配送","xingji":"5","old_phone":"18854806444","fuwu_value":"炒锅饭","dingdanlaiyuan":"1","waiter_address":"江苏苏州市张家港市要去 张先生 13611127052","endTime":1684998660000,"cost":0.01},{"id":"4434355555444","startTime":1619509912000,"goodstypeName":"超值配送","xingji":"3","old_phone":"18854806444","fuwu_value":"洗脚","dingdanlaiyuan":"1","waiter_address":"泰安市泰山区明堂路60","endTime":1619520659000,"cost":0.01},{"id":"444343456655","startTime":1619509912000,"goodstypeName":"超值配送","xingji":"2","old_phone":"18854806444","fuwu_value":"做饭","dingdanlaiyuan":"1","waiter_address":"泰安市泰山区明堂路60号","endTime":1619520659000,"cost":0.01},{"id":"434456788978766","startTime":1619509912000,"goodstypeName":"超值配送","xingji":"2","old_phone":"18854806444","fuwu_value":"做饭","dingdanlaiyuan":"1","waiter_address":"泰安市泰山区明堂路60号","endTime":1619520659000,"cost":0.01}]
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
         * id : 4958358621544d1dac99b6a608e8d3e2
         * startTime : 1685005860000
         * goodstypeName : 美食配送
         * xingji : 5
         * old_phone : 18854806444
         * fuwu_value : 炒锅饭
         * dingdanlaiyuan : 1
         * waiter_address : 江苏苏州市张家港市要去 张先生 13611127052
         * endTime : 1684998660000
         * cost : 0.01
         */

        private String id;
        private long startTime;
        private String goodstypeName;
        private String xingji;
        private String old_phone;
        private String fuwu_value;
        private String dingdanlaiyuan;
        private String waiter_address;
        private long endTime;
        private double cost;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

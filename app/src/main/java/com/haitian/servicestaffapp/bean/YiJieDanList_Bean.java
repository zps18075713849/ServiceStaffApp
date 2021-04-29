package com.haitian.servicestaffapp.bean;

import java.util.List;

public class YiJieDanList_Bean {

    /**
     * code : 20041
     * data : [{"id":3,"xingji":"3","user_id":7,"fuwutype_id":2,"fuwu_value":"洗脚","startTime":"2021-03-26 15:51:52.0","endTime":"2021-03-26 18:50:59.0","waiter_address":"泰安市泰山区明堂路60","cost":null,"state":"1","creatTime":"2021-03-26 15:01:30.0","wanchengState":"0","pingjiak":null}]
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
         * id : 3
         * xingji : 3
         * user_id : 7
         * fuwutype_id : 2
         * fuwu_value : 洗脚
         * startTime : 2021-03-26 15:51:52.0
         * endTime : 2021-03-26 18:50:59.0
         * waiter_address : 泰安市泰山区明堂路60
         * cost : null
         * state : 1
         * creatTime : 2021-03-26 15:01:30.0
         * wanchengState : 0
         * pingjiak : null
         */

        private int id;
        private String xingji;
        private int user_id;
        private int fuwutype_id;
        private String fuwu_value;
        private String startTime;
        private String endTime;
        private String waiter_address;
        private Object cost;
        private String state;
        private String creatTime;
        private String wanchengState;
        private Object pingjiak;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getXingji() {
            return xingji;
        }

        public void setXingji(String xingji) {
            this.xingji = xingji;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getFuwutype_id() {
            return fuwutype_id;
        }

        public void setFuwutype_id(int fuwutype_id) {
            this.fuwutype_id = fuwutype_id;
        }

        public String getFuwu_value() {
            return fuwu_value;
        }

        public void setFuwu_value(String fuwu_value) {
            this.fuwu_value = fuwu_value;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getWaiter_address() {
            return waiter_address;
        }

        public void setWaiter_address(String waiter_address) {
            this.waiter_address = waiter_address;
        }

        public Object getCost() {
            return cost;
        }

        public void setCost(Object cost) {
            this.cost = cost;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(String creatTime) {
            this.creatTime = creatTime;
        }

        public String getWanchengState() {
            return wanchengState;
        }

        public void setWanchengState(String wanchengState) {
            this.wanchengState = wanchengState;
        }

        public Object getPingjiak() {
            return pingjiak;
        }

        public void setPingjiak(Object pingjiak) {
            this.pingjiak = pingjiak;
        }
    }
}

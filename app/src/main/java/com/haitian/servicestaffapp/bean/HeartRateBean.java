package com.haitian.servicestaffapp.bean;

public class HeartRateBean {

    /**
     * 数据点
     */
    private float data;
    /**
     * 时间
     */
    private String time;

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public HeartRateBean(float data, String time) {
        this.data = data;
        this.time = time;
    }
}

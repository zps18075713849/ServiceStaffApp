package com.haitian.servicestaffapp.bean;

import java.util.List;

public class KeHuGuanLi_Bean {

    /**
     * code : 20041
     * data : ["18266860807"]
     * message : null
     */

    private int code;
    private Object message;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}

package com.haitian.servicestaffapp.bean;

/**
 * Created by Android Studio.
 * User: user
 * Date: 2021/3/23
 * Time: 16:36
 */
public class CodeMessageBean {

    /**
     * code : 20021
     * data : null
     * message : 密码修改成功
     */

    private int code;
    private Object data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

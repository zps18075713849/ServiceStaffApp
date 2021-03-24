package com.haitian.servicestaffapp.bean;

public class Login_Bean {

    /**
     * code : 20041
     * data : {"user_id":68,"user_name":"18266860807","user_nickname":null,"user_card_name":"颜哲童","user_pwd":"329dbc42c8b6096fe6518549e7688baf","user_affiliate":null,"user_role_id":null,"user_dept":null,"messageId":null,"messgae":null,"user_type":"2","user_department":"3","user_zhengjian":"http://localhost:8080/images/25257826-564c-47db-be8d-934e8cf97b27.jpg,http://localhost:8080/images/1ec60be1-073a-4724-9761-babf24c9e2f9.jpg,","newuser_pwd":null,"shouchizhaop":"http://localhost:8080/images/52986d4d-42a3-4415-ae9d-2426d5986594.jpg,http://localhost:8080/images/ae96971d-1d89-4b56-a994-372c16f8aefe.jpg,","huojiangzhengshu":"http://localhost:8080/images/2c07f1bb-befd-4330-bda4-ff4d07f452ba.jpg,http://localhost:8080/images/228f9897-1881-485f-a89b-f0e14af11d04.jpg,","shouchi":null,"huojiangzhengs":null}
     * message : 登陆成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * user_id : 68
         * user_name : 18266860807
         * user_nickname : null
         * user_card_name : 颜哲童
         * user_pwd : 329dbc42c8b6096fe6518549e7688baf
         * user_affiliate : null
         * user_role_id : null
         * user_dept : null
         * messageId : null
         * messgae : null
         * user_type : 2
         * user_department : 3
         * user_zhengjian : http://localhost:8080/images/25257826-564c-47db-be8d-934e8cf97b27.jpg,http://localhost:8080/images/1ec60be1-073a-4724-9761-babf24c9e2f9.jpg,
         * newuser_pwd : null
         * shouchizhaop : http://localhost:8080/images/52986d4d-42a3-4415-ae9d-2426d5986594.jpg,http://localhost:8080/images/ae96971d-1d89-4b56-a994-372c16f8aefe.jpg,
         * huojiangzhengshu : http://localhost:8080/images/2c07f1bb-befd-4330-bda4-ff4d07f452ba.jpg,http://localhost:8080/images/228f9897-1881-485f-a89b-f0e14af11d04.jpg,
         * shouchi : null
         * huojiangzhengs : null
         */

        private int user_id;
        private String user_name;
        private Object user_nickname;
        private String user_card_name;
        private String user_pwd;
        private Object user_affiliate;
        private Object user_role_id;
        private Object user_dept;
        private Object messageId;
        private Object messgae;
        private String user_type;
        private String user_department;
        private String user_zhengjian;
        private Object newuser_pwd;
        private String shouchizhaop;
        private String huojiangzhengshu;
        private Object shouchi;
        private Object huojiangzhengs;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public Object getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(Object user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getUser_card_name() {
            return user_card_name;
        }

        public void setUser_card_name(String user_card_name) {
            this.user_card_name = user_card_name;
        }

        public String getUser_pwd() {
            return user_pwd;
        }

        public void setUser_pwd(String user_pwd) {
            this.user_pwd = user_pwd;
        }

        public Object getUser_affiliate() {
            return user_affiliate;
        }

        public void setUser_affiliate(Object user_affiliate) {
            this.user_affiliate = user_affiliate;
        }

        public Object getUser_role_id() {
            return user_role_id;
        }

        public void setUser_role_id(Object user_role_id) {
            this.user_role_id = user_role_id;
        }

        public Object getUser_dept() {
            return user_dept;
        }

        public void setUser_dept(Object user_dept) {
            this.user_dept = user_dept;
        }

        public Object getMessageId() {
            return messageId;
        }

        public void setMessageId(Object messageId) {
            this.messageId = messageId;
        }

        public Object getMessgae() {
            return messgae;
        }

        public void setMessgae(Object messgae) {
            this.messgae = messgae;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getUser_department() {
            return user_department;
        }

        public void setUser_department(String user_department) {
            this.user_department = user_department;
        }

        public String getUser_zhengjian() {
            return user_zhengjian;
        }

        public void setUser_zhengjian(String user_zhengjian) {
            this.user_zhengjian = user_zhengjian;
        }

        public Object getNewuser_pwd() {
            return newuser_pwd;
        }

        public void setNewuser_pwd(Object newuser_pwd) {
            this.newuser_pwd = newuser_pwd;
        }

        public String getShouchizhaop() {
            return shouchizhaop;
        }

        public void setShouchizhaop(String shouchizhaop) {
            this.shouchizhaop = shouchizhaop;
        }

        public String getHuojiangzhengshu() {
            return huojiangzhengshu;
        }

        public void setHuojiangzhengshu(String huojiangzhengshu) {
            this.huojiangzhengshu = huojiangzhengshu;
        }

        public Object getShouchi() {
            return shouchi;
        }

        public void setShouchi(Object shouchi) {
            this.shouchi = shouchi;
        }

        public Object getHuojiangzhengs() {
            return huojiangzhengs;
        }

        public void setHuojiangzhengs(Object huojiangzhengs) {
            this.huojiangzhengs = huojiangzhengs;
        }
    }
}

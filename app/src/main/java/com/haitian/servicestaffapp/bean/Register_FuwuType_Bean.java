package com.haitian.servicestaffapp.bean;

import java.util.List;

public class Register_FuwuType_Bean {

    /**
     * code : 20041
     * data : [{"id":1,"fuwutype":"家政","fuwutext":null,"creattime":null,"updatetime":null,"delFlag":0,"app_biaoshi":null,"agencyid":1,"createuser":null},{"id":2,"fuwutype":"理疗","fuwutext":null,"creattime":null,"updatetime":null,"delFlag":0,"app_biaoshi":null,"agencyid":1,"createuser":null},{"id":3,"fuwutype":"餐饮","fuwutext":null,"creattime":null,"updatetime":null,"delFlag":0,"app_biaoshi":null,"agencyid":1,"createuser":null}]
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
         * id : 1
         * fuwutype : 家政
         * fuwutext : null
         * creattime : null
         * updatetime : null
         * delFlag : 0
         * app_biaoshi : null
         * agencyid : 1
         * createuser : null
         */

        private int id;
        private String fuwutype;
        private Object fuwutext;
        private Object creattime;
        private Object updatetime;
        private int delFlag;
        private Object app_biaoshi;
        private int agencyid;
        private Object createuser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFuwutype() {
            return fuwutype;
        }

        public void setFuwutype(String fuwutype) {
            this.fuwutype = fuwutype;
        }

        public Object getFuwutext() {
            return fuwutext;
        }

        public void setFuwutext(Object fuwutext) {
            this.fuwutext = fuwutext;
        }

        public Object getCreattime() {
            return creattime;
        }

        public void setCreattime(Object creattime) {
            this.creattime = creattime;
        }

        public Object getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Object updatetime) {
            this.updatetime = updatetime;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public Object getApp_biaoshi() {
            return app_biaoshi;
        }

        public void setApp_biaoshi(Object app_biaoshi) {
            this.app_biaoshi = app_biaoshi;
        }

        public int getAgencyid() {
            return agencyid;
        }

        public void setAgencyid(int agencyid) {
            this.agencyid = agencyid;
        }

        public Object getCreateuser() {
            return createuser;
        }

        public void setCreateuser(Object createuser) {
            this.createuser = createuser;
        }
    }
}

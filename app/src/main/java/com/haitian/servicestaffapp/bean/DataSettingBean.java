package com.haitian.servicestaffapp.bean;

/**
 * Created by Android Studio.
 * User: user
 * Date: 2021/3/25
 * Time: 17:04
 */
public class DataSettingBean {


    /**
     * code : 20041
     * data : {"leixing":{"id":2,"fuwutype":"理疗","fuwutext":null,"creattime":null,"updatetime":null,"delFlag":0,"app_biaoshi":null,"agencyid":1,"createuser":null},"bumen":{"supplier_id":3,"supplier_name":"3332","supplier_type":1,"link_man":"111","phone_number":"a","provinceID":"110000","cityID":"110100","regionID":"","address":"111","scope":5,"isHaveFuwuMan":1,"modified_time":"2019-11-16 13:52:13.0","supplier_status":1,"create_time":"2019-11-16 09:05:58.0"},"user":{"user_id":68,"user_name":"18266860807","user_nickname":null,"user_card_name":"颜哲童","user_pwd":"329dbc42c8b6096fe6518549e7688baf","user_affiliate":null,"user_role_id":null,"user_dept":null,"messageId":null,"messgae":null,"user_type":"2","user_department":"3","user_zhengjian":"http://localhost:8080/images/25257826-564c-47db-be8d-934e8cf97b27.jpg,http://localhost:8080/images/1ec60be1-073a-4724-9761-babf24c9e2f9.jpg,","newuser_pwd":null,"shenfenzheng":null,"shouchizhaop":"http://localhost:8080/images/52986d4d-42a3-4415-ae9d-2426d5986594.jpg,http://localhost:8080/images/ae96971d-1d89-4b56-a994-372c16f8aefe.jpg,","huojiangzhengshu":"http://localhost:8080/images/2c07f1bb-befd-4330-bda4-ff4d07f452ba.jpg,http://localhost:8080/images/228f9897-1881-485f-a89b-f0e14af11d04.jpg,","zhiyezhengshu":null,"shouchi":null,"huojiangzhengs":null,"shenfenzhenglist":null,"zhiyezhengshuList":null,"biaoji":null}}
     * message : null
     */

    private int code;
    private DataBean data;
    private Object message;

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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * leixing : {"id":2,"fuwutype":"理疗","fuwutext":null,"creattime":null,"updatetime":null,"delFlag":0,"app_biaoshi":null,"agencyid":1,"createuser":null}
         * bumen : {"supplier_id":3,"supplier_name":"3332","supplier_type":1,"link_man":"111","phone_number":"a","provinceID":"110000","cityID":"110100","regionID":"","address":"111","scope":5,"isHaveFuwuMan":1,"modified_time":"2019-11-16 13:52:13.0","supplier_status":1,"create_time":"2019-11-16 09:05:58.0"}
         * user : {"user_id":68,"user_name":"18266860807","user_nickname":null,"user_card_name":"颜哲童","user_pwd":"329dbc42c8b6096fe6518549e7688baf","user_affiliate":null,"user_role_id":null,"user_dept":null,"messageId":null,"messgae":null,"user_type":"2","user_department":"3","user_zhengjian":"http://localhost:8080/images/25257826-564c-47db-be8d-934e8cf97b27.jpg,http://localhost:8080/images/1ec60be1-073a-4724-9761-babf24c9e2f9.jpg,","newuser_pwd":null,"shenfenzheng":null,"shouchizhaop":"http://localhost:8080/images/52986d4d-42a3-4415-ae9d-2426d5986594.jpg,http://localhost:8080/images/ae96971d-1d89-4b56-a994-372c16f8aefe.jpg,","huojiangzhengshu":"http://localhost:8080/images/2c07f1bb-befd-4330-bda4-ff4d07f452ba.jpg,http://localhost:8080/images/228f9897-1881-485f-a89b-f0e14af11d04.jpg,","zhiyezhengshu":null,"shouchi":null,"huojiangzhengs":null,"shenfenzhenglist":null,"zhiyezhengshuList":null,"biaoji":null}
         */

        private LeixingBean leixing;
        private BumenBean bumen;
        private UserBean user;

        public LeixingBean getLeixing() {
            return leixing;
        }

        public void setLeixing(LeixingBean leixing) {
            this.leixing = leixing;
        }

        public BumenBean getBumen() {
            return bumen;
        }

        public void setBumen(BumenBean bumen) {
            this.bumen = bumen;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class LeixingBean {
            /**
             * id : 2
             * fuwutype : 理疗
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

        public static class BumenBean {
            /**
             * supplier_id : 3
             * supplier_name : 3332
             * supplier_type : 1
             * link_man : 111
             * phone_number : a
             * provinceID : 110000
             * cityID : 110100
             * regionID :
             * address : 111
             * scope : 5
             * isHaveFuwuMan : 1
             * modified_time : 2019-11-16 13:52:13.0
             * supplier_status : 1
             * create_time : 2019-11-16 09:05:58.0
             */

            private int supplier_id;
            private String supplier_name;
            private int supplier_type;
            private String link_man;
            private String phone_number;
            private String provinceID;
            private String cityID;
            private String regionID;
            private String address;
            private int scope;
            private int isHaveFuwuMan;
            private String modified_time;
            private int supplier_status;
            private String create_time;

            public int getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(int supplier_id) {
                this.supplier_id = supplier_id;
            }

            public String getSupplier_name() {
                return supplier_name;
            }

            public void setSupplier_name(String supplier_name) {
                this.supplier_name = supplier_name;
            }

            public int getSupplier_type() {
                return supplier_type;
            }

            public void setSupplier_type(int supplier_type) {
                this.supplier_type = supplier_type;
            }

            public String getLink_man() {
                return link_man;
            }

            public void setLink_man(String link_man) {
                this.link_man = link_man;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getProvinceID() {
                return provinceID;
            }

            public void setProvinceID(String provinceID) {
                this.provinceID = provinceID;
            }

            public String getCityID() {
                return cityID;
            }

            public void setCityID(String cityID) {
                this.cityID = cityID;
            }

            public String getRegionID() {
                return regionID;
            }

            public void setRegionID(String regionID) {
                this.regionID = regionID;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getScope() {
                return scope;
            }

            public void setScope(int scope) {
                this.scope = scope;
            }

            public int getIsHaveFuwuMan() {
                return isHaveFuwuMan;
            }

            public void setIsHaveFuwuMan(int isHaveFuwuMan) {
                this.isHaveFuwuMan = isHaveFuwuMan;
            }

            public String getModified_time() {
                return modified_time;
            }

            public void setModified_time(String modified_time) {
                this.modified_time = modified_time;
            }

            public int getSupplier_status() {
                return supplier_status;
            }

            public void setSupplier_status(int supplier_status) {
                this.supplier_status = supplier_status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }

        public static class UserBean {
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
             * shenfenzheng : null
             * shouchizhaop : http://localhost:8080/images/52986d4d-42a3-4415-ae9d-2426d5986594.jpg,http://localhost:8080/images/ae96971d-1d89-4b56-a994-372c16f8aefe.jpg,
             * huojiangzhengshu : http://localhost:8080/images/2c07f1bb-befd-4330-bda4-ff4d07f452ba.jpg,http://localhost:8080/images/228f9897-1881-485f-a89b-f0e14af11d04.jpg,
             * zhiyezhengshu : null
             * shouchi : null
             * huojiangzhengs : null
             * shenfenzhenglist : null
             * zhiyezhengshuList : null
             * biaoji : null
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
            private Object shenfenzheng;
            private String shouchizhaop;
            private String huojiangzhengshu;
            private Object zhiyezhengshu;
            private Object shouchi;
            private Object huojiangzhengs;
            private Object shenfenzhenglist;
            private Object zhiyezhengshuList;
            private Object biaoji;

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

            public Object getShenfenzheng() {
                return shenfenzheng;
            }

            public void setShenfenzheng(Object shenfenzheng) {
                this.shenfenzheng = shenfenzheng;
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

            public Object getZhiyezhengshu() {
                return zhiyezhengshu;
            }

            public void setZhiyezhengshu(Object zhiyezhengshu) {
                this.zhiyezhengshu = zhiyezhengshu;
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

            public Object getShenfenzhenglist() {
                return shenfenzhenglist;
            }

            public void setShenfenzhenglist(Object shenfenzhenglist) {
                this.shenfenzhenglist = shenfenzhenglist;
            }

            public Object getZhiyezhengshuList() {
                return zhiyezhengshuList;
            }

            public void setZhiyezhengshuList(Object zhiyezhengshuList) {
                this.zhiyezhengshuList = zhiyezhengshuList;
            }

            public Object getBiaoji() {
                return biaoji;
            }

            public void setBiaoji(Object biaoji) {
                this.biaoji = biaoji;
            }
        }
    }
}

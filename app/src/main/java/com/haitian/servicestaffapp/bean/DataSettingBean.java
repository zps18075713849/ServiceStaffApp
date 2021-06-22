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
     * data : {"leixing":{"id":2,"fuwutype":"理疗","fuwutext":null,"creattime":null,"updatetime":null,"delFlag":0,"app_biaoshi":null,"agencyid":1,"createuser":null},"bumen":{"bumen":{"id":41,"agencyName":"测试2","agencyType":3,"mobile":null,"province":null,"city":null,"region":null,"provinceId":null,"cityId":null,"regionId":null,"addr":null,"createUser":null,"createTime":null,"updateUser":null,"updateTime":null,"remark":null},"shequjigou_biaoshi":1},"user":{"user_id":72,"user_name":"13611127052","user_nickname":null,"user_card_name":"手机","user_pwd":"e10adc3949ba59abbe56e057f20f883e","user_affiliate":null,"user_role_id":null,"user_dept":null,"messageId":null,"messgae":null,"user_type":"2","user_department":"41","user_zhengjian":null,"newuser_pwd":null,"shenfenzheng":"http://111.17.215.37:817/waiter/images/612b0200-9b36-43bd-8bb8-7ab941a835ae.png,http://111.17.215.37:817/waiter/images/656475f0-ae75-445f-8e30-54bcfecec257.png,","shouchizhaop":"http://111.17.215.37:817/waiter/images/18998a9f-abad-4edc-81d2-208cb2fd3590.png,","huojiangzhengshu":null,"zhiyezhengshu":null,"shequjigou_biaoshi":1,"fuwurenyuan_adress":"北京市昌平区","shouchi":null,"huojiangzhengs":null,"shenfenzhenglist":null,"zhiyezhengshuList":null,"biaoji":null}}
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
         * bumen : {"bumen":{"id":41,"agencyName":"测试2","agencyType":3,"mobile":null,"province":null,"city":null,"region":null,"provinceId":null,"cityId":null,"regionId":null,"addr":null,"createUser":null,"createTime":null,"updateUser":null,"updateTime":null,"remark":null},"shequjigou_biaoshi":1}
         * user : {"user_id":72,"user_name":"13611127052","user_nickname":null,"user_card_name":"手机","user_pwd":"e10adc3949ba59abbe56e057f20f883e","user_affiliate":null,"user_role_id":null,"user_dept":null,"messageId":null,"messgae":null,"user_type":"2","user_department":"41","user_zhengjian":null,"newuser_pwd":null,"shenfenzheng":"http://111.17.215.37:817/waiter/images/612b0200-9b36-43bd-8bb8-7ab941a835ae.png,http://111.17.215.37:817/waiter/images/656475f0-ae75-445f-8e30-54bcfecec257.png,","shouchizhaop":"http://111.17.215.37:817/waiter/images/18998a9f-abad-4edc-81d2-208cb2fd3590.png,","huojiangzhengshu":null,"zhiyezhengshu":null,"shequjigou_biaoshi":1,"fuwurenyuan_adress":"北京市昌平区","shouchi":null,"huojiangzhengs":null,"shenfenzhenglist":null,"zhiyezhengshuList":null,"biaoji":null}
         */

        private LeixingBean leixing;
        private BumenBeanX bumen;
        private UserBean user;

        public LeixingBean getLeixing() {
            return leixing;
        }

        public void setLeixing(LeixingBean leixing) {
            this.leixing = leixing;
        }

        public BumenBeanX getBumen() {
            return bumen;
        }

        public void setBumen(BumenBeanX bumen) {
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

        public static class BumenBeanX {
            /**
             * bumen : {"id":41,"agencyName":"测试2","agencyType":3,"mobile":null,"province":null,"city":null,"region":null,"provinceId":null,"cityId":null,"regionId":null,"addr":null,"createUser":null,"createTime":null,"updateUser":null,"updateTime":null,"remark":null}
             * shequjigou_biaoshi : 1
             */

            private BumenBean bumen;
            private int shequjigou_biaoshi;

            public BumenBean getBumen() {
                return bumen;
            }

            public void setBumen(BumenBean bumen) {
                this.bumen = bumen;
            }

            public int getShequjigou_biaoshi() {
                return shequjigou_biaoshi;
            }

            public void setShequjigou_biaoshi(int shequjigou_biaoshi) {
                this.shequjigou_biaoshi = shequjigou_biaoshi;
            }

            public static class BumenBean {
                /**
                 * id : 41
                 * agencyName : 测试2
                 * agencyType : 3
                 * mobile : null
                 * province : null
                 * city : null
                 * region : null
                 * provinceId : null
                 * cityId : null
                 * regionId : null
                 * addr : null
                 * createUser : null
                 * createTime : null
                 * updateUser : null
                 * updateTime : null
                 * remark : null
                 */

                private int id;
                private String agencyName;
                private int agencyType;
                private Object mobile;
                private Object province;
                private Object city;
                private Object region;
                private Object provinceId;
                private Object cityId;
                private Object regionId;
                private Object addr;
                private Object createUser;
                private Object createTime;
                private Object updateUser;
                private Object updateTime;
                private Object remark;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAgencyName() {
                    return agencyName;
                }

                public void setAgencyName(String agencyName) {
                    this.agencyName = agencyName;
                }

                public int getAgencyType() {
                    return agencyType;
                }

                public void setAgencyType(int agencyType) {
                    this.agencyType = agencyType;
                }

                public Object getMobile() {
                    return mobile;
                }

                public void setMobile(Object mobile) {
                    this.mobile = mobile;
                }

                public Object getProvince() {
                    return province;
                }

                public void setProvince(Object province) {
                    this.province = province;
                }

                public Object getCity() {
                    return city;
                }

                public void setCity(Object city) {
                    this.city = city;
                }

                public Object getRegion() {
                    return region;
                }

                public void setRegion(Object region) {
                    this.region = region;
                }

                public Object getProvinceId() {
                    return provinceId;
                }

                public void setProvinceId(Object provinceId) {
                    this.provinceId = provinceId;
                }

                public Object getCityId() {
                    return cityId;
                }

                public void setCityId(Object cityId) {
                    this.cityId = cityId;
                }

                public Object getRegionId() {
                    return regionId;
                }

                public void setRegionId(Object regionId) {
                    this.regionId = regionId;
                }

                public Object getAddr() {
                    return addr;
                }

                public void setAddr(Object addr) {
                    this.addr = addr;
                }

                public Object getCreateUser() {
                    return createUser;
                }

                public void setCreateUser(Object createUser) {
                    this.createUser = createUser;
                }

                public Object getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(Object createTime) {
                    this.createTime = createTime;
                }

                public Object getUpdateUser() {
                    return updateUser;
                }

                public void setUpdateUser(Object updateUser) {
                    this.updateUser = updateUser;
                }

                public Object getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(Object updateTime) {
                    this.updateTime = updateTime;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
                }
            }
        }

        public static class UserBean {
            /**
             * user_id : 72
             * user_name : 13611127052
             * user_nickname : null
             * user_card_name : 手机
             * user_pwd : e10adc3949ba59abbe56e057f20f883e
             * user_affiliate : null
             * user_role_id : null
             * user_dept : null
             * messageId : null
             * messgae : null
             * user_type : 2
             * user_department : 41
             * user_zhengjian : null
             * newuser_pwd : null
             * shenfenzheng : http://111.17.215.37:817/waiter/images/612b0200-9b36-43bd-8bb8-7ab941a835ae.png,http://111.17.215.37:817/waiter/images/656475f0-ae75-445f-8e30-54bcfecec257.png,
             * shouchizhaop : http://111.17.215.37:817/waiter/images/18998a9f-abad-4edc-81d2-208cb2fd3590.png,
             * huojiangzhengshu : null
             * zhiyezhengshu : null
             * shequjigou_biaoshi : 1
             * fuwurenyuan_adress : 北京市昌平区
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
            private Object user_zhengjian;
            private Object newuser_pwd;
            private String shenfenzheng;
            private String shouchizhaop;
            private Object huojiangzhengshu;
            private Object zhiyezhengshu;
            private int shequjigou_biaoshi;
            private String fuwurenyuan_adress;
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

            public Object getUser_zhengjian() {
                return user_zhengjian;
            }

            public void setUser_zhengjian(Object user_zhengjian) {
                this.user_zhengjian = user_zhengjian;
            }

            public Object getNewuser_pwd() {
                return newuser_pwd;
            }

            public void setNewuser_pwd(Object newuser_pwd) {
                this.newuser_pwd = newuser_pwd;
            }

            public String getShenfenzheng() {
                return shenfenzheng;
            }

            public void setShenfenzheng(String shenfenzheng) {
                this.shenfenzheng = shenfenzheng;
            }

            public String getShouchizhaop() {
                return shouchizhaop;
            }

            public void setShouchizhaop(String shouchizhaop) {
                this.shouchizhaop = shouchizhaop;
            }

            public Object getHuojiangzhengshu() {
                return huojiangzhengshu;
            }

            public void setHuojiangzhengshu(Object huojiangzhengshu) {
                this.huojiangzhengshu = huojiangzhengshu;
            }

            public Object getZhiyezhengshu() {
                return zhiyezhengshu;
            }

            public void setZhiyezhengshu(Object zhiyezhengshu) {
                this.zhiyezhengshu = zhiyezhengshu;
            }

            public int getShequjigou_biaoshi() {
                return shequjigou_biaoshi;
            }

            public void setShequjigou_biaoshi(int shequjigou_biaoshi) {
                this.shequjigou_biaoshi = shequjigou_biaoshi;
            }

            public String getFuwurenyuan_adress() {
                return fuwurenyuan_adress;
            }

            public void setFuwurenyuan_adress(String fuwurenyuan_adress) {
                this.fuwurenyuan_adress = fuwurenyuan_adress;
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

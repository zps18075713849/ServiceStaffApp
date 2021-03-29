package com.haitian.servicestaffapp.bean;

import java.util.List;

public class Register_BuMen_Bean {


    /**
     * code : 20041
     * data : [{"id":1,"agencyName":"泰安市医养中心","agencyType":3,"mobile":"18605486666","province":"山东省","city":"泰安市","region":"泰山区","provinceId":"370000","cityId":"370900","regionId":"370902","addr":"碧霞虎大街","createUser":null,"createTime":null,"updateUser":"1","updateTime":"2020-04-28 16:12:53.0","remark":null},{"id":40,"agencyName":"测试1","agencyType":3,"mobile":null,"province":null,"city":null,"region":null,"provinceId":null,"cityId":null,"regionId":null,"addr":null,"createUser":null,"createTime":null,"updateUser":null,"updateTime":null,"remark":null},{"id":41,"agencyName":"测试2","agencyType":3,"mobile":null,"province":null,"city":null,"region":null,"provinceId":null,"cityId":null,"regionId":null,"addr":null,"createUser":null,"createTime":null,"updateUser":null,"updateTime":null,"remark":null}]
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
         * agencyName : 泰安市医养中心
         * agencyType : 3
         * mobile : 18605486666
         * province : 山东省
         * city : 泰安市
         * region : 泰山区
         * provinceId : 370000
         * cityId : 370900
         * regionId : 370902
         * addr : 碧霞虎大街
         * createUser : null
         * createTime : null
         * updateUser : 1
         * updateTime : 2020-04-28 16:12:53.0
         * remark : null
         */

        private int id;
        private String agencyName;
        private int agencyType;
        private String mobile;
        private String province;
        private String city;
        private String region;
        private String provinceId;
        private String cityId;
        private String regionId;
        private String addr;
        private Object createUser;
        private Object createTime;
        private String updateUser;
        private String updateTime;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getRegionId() {
            return regionId;
        }

        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
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

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
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

package com.haitian.servicestaffapp.bean;

import java.util.List;

public class Register_BuMen_Bean {

    /**
     * code : 20041
     * data : [{"supplier_id":1,"supplier_name":"111","supplier_type":1,"link_man":"111","phone_number":"a","provinceID":"110000","cityID":"110100","regionID":"","address":"111","scope":5,"isHaveFuwuMan":1,"modified_time":"2019-11-16 08:58:29.0","supplier_status":0,"create_time":"2019-11-16 08:58:29.0"},{"supplier_id":2,"supplier_name":"222","supplier_type":1,"link_man":"111","phone_number":"a","provinceID":"110000","cityID":"110100","regionID":"","address":"111","scope":5,"isHaveFuwuMan":1,"modified_time":"2019-11-16 09:05:49.0","supplier_status":0,"create_time":"2019-11-16 09:05:49.0"},{"supplier_id":3,"supplier_name":"3332","supplier_type":1,"link_man":"111","phone_number":"a","provinceID":"110000","cityID":"110100","regionID":"","address":"111","scope":5,"isHaveFuwuMan":1,"modified_time":"2019-11-16 13:52:13.0","supplier_status":1,"create_time":"2019-11-16 09:05:58.0"},{"supplier_id":4,"supplier_name":"majian","supplier_type":null,"link_man":"majian1","phone_number":"188","provinceID":"370000","cityID":"370900","regionID":"370911","address":"明堂路","scope":null,"isHaveFuwuMan":1,"modified_time":"2020-07-27 17:04:14.0","supplier_status":null,"create_time":"2020-07-27 17:04:14.0"},{"supplier_id":5,"supplier_name":"majian","supplier_type":null,"link_man":"majian1","phone_number":"188","provinceID":"110001","cityID":"110000","regionID":"110100","address":"明堂路","scope":null,"isHaveFuwuMan":1,"modified_time":"2020-07-27 17:05:36.0","supplier_status":null,"create_time":"2020-07-27 17:05:36.0"}]
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
         * supplier_id : 1
         * supplier_name : 111
         * supplier_type : 1
         * link_man : 111
         * phone_number : a
         * provinceID : 110000
         * cityID : 110100
         * regionID :
         * address : 111
         * scope : 5
         * isHaveFuwuMan : 1
         * modified_time : 2019-11-16 08:58:29.0
         * supplier_status : 0
         * create_time : 2019-11-16 08:58:29.0
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
}

package com.haitian.servicestaffapp.bean;

public class UpdateVersion_Bean {

    /**
     * code : 20040
     * data : {"id":5,"type":"waiter","url":"http://111.17.215.37:816/app/fuwu1.0.2.apk","version":"1.0.2"}
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
         * id : 5
         * type : waiter
         * url : http://111.17.215.37:816/app/fuwu1.0.2.apk
         * version : 1.0.2
         */

        private int id;
        private String type;
        private String url;
        private String version;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}

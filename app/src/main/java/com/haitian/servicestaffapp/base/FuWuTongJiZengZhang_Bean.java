package com.haitian.servicestaffapp.base;

public class FuWuTongJiZengZhang_Bean {

    /**
     * code : 20041
     * data : {"shangzhou":"0","shangyue":"0","shangjidu":"200%"}
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
         * shangzhou : 0
         * shangyue : 0
         * shangjidu : 200%
         */

        private String shangzhou;
        private String shangyue;
        private String shangjidu;

        public String getShangzhou() {
            return shangzhou;
        }

        public void setShangzhou(String shangzhou) {
            this.shangzhou = shangzhou;
        }

        public String getShangyue() {
            return shangyue;
        }

        public void setShangyue(String shangyue) {
            this.shangyue = shangyue;
        }

        public String getShangjidu() {
            return shangjidu;
        }

        public void setShangjidu(String shangjidu) {
            this.shangjidu = shangjidu;
        }
    }
}

package com.haitian.servicestaffapp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZheXianTu_Bean {


    /**
     * code : 20041
     * data : [{"1":1},{"5":1},{"7":1},{"8":1},{"9":3},{"12":1}]
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
         * 1 : 1
         * 5 : 1
         * 7 : 1
         * 8 : 1
         * 9 : 3
         * 12 : 1
         */

        @SerializedName("1")
        private int _$1;
        @SerializedName("2")
        private int _$2;
        @SerializedName("3")
        private int _$3;
        @SerializedName("4")
        private int _$4;
        @SerializedName("5")
        private int _$5;
        @SerializedName("6")
        private int _$6;
        @SerializedName("7")
        private int _$7;
        @SerializedName("8")
        private int _$8;
        @SerializedName("9")
        private int _$9;
        @SerializedName("10")
        private int _$10;
        @SerializedName("11")
        private int _$11;
        @SerializedName("12")
        private int _$12;

        public int get_$1() {
            return _$1;
        }

        public void set_$1(int _$1) {
            this._$1 = _$1;
        }

        public int get_$2() {
            return _$2;
        }

        public void set_$2(int _$2) {
            this._$2 = _$2;
        }

        public int get_$3() {
            return _$3;
        }

        public void set_$3(int _$3) {
            this._$3 = _$3;
        }

        public int get_$4() {
            return _$4;
        }

        public void set_$4(int _$4) {
            this._$4 = _$4;
        }

        public int get_$5() {
            return _$5;
        }

        public void set_$5(int _$5) {
            this._$5 = _$5;
        }

        public int get_$6() {
            return _$6;
        }

        public void set_$6(int _$6) {
            this._$6 = _$6;
        }

        public int get_$7() {
            return _$7;
        }

        public void set_$7(int _$7) {
            this._$7 = _$7;
        }

        public int get_$8() {
            return _$8;
        }

        public void set_$8(int _$8) {
            this._$8 = _$8;
        }

        public int get_$9() {
            return _$9;
        }

        public void set_$9(int _$9) {
            this._$9 = _$9;
        }

        public int get_$10() {
            return _$10;
        }

        public void set_$10(int _$10) {
            this._$10 = _$10;
        }

        public int get_$11() {
            return _$11;
        }

        public void set_$11(int _$11) {
            this._$11 = _$11;
        }

        public int get_$12() {
            return _$12;
        }

        public void set_$12(int _$12) {
            this._$12 = _$12;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "_$1=" + _$1 +
                    ", _$2=" + _$2 +
                    ", _$3=" + _$3 +
                    ", _$4=" + _$4 +
                    ", _$5=" + _$5 +
                    ", _$6=" + _$6 +
                    ", _$7=" + _$7 +
                    ", _$8=" + _$8 +
                    ", _$9=" + _$9 +
                    ", _$10=" + _$10 +
                    ", _$11=" + _$11 +
                    ", _$12=" + _$12 +
                    '}';
        }
    }
}

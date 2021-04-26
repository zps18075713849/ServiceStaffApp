package com.haitian.servicestaffapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Studio.
 * User: user
 * Date: 2021/4/22
 * Time: 9:32
 */
public class Bean {

    /**
     * code : 20041
     * data : [{"1":"2"},{"5":1},{"7":1},{"8":1},{"9":3},{"12":1}]
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
         * 1 : 2
         * 5 : 1
         * 7 : 1
         * 8 : 1
         * 9 : 3
         * 12 : 1
         */

        @SerializedName("1")
        private String _$1;
        @SerializedName("5")
        private int _$5;
        @SerializedName("7")
        private int _$7;
        @SerializedName("8")
        private int _$8;
        @SerializedName("9")
        private int _$9;
        @SerializedName("12")
        private int _$12;

        public DataBean(String _$1, int _$5) {
            this._$1 = _$1;
            this._$5 = _$5;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public int get_$5() {
            return _$5;
        }

        public void set_$5(int _$5) {
            this._$5 = _$5;
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

        public int get_$12() {
            return _$12;
        }

        public void set_$12(int _$12) {
            this._$12 = _$12;
        }
    }
}

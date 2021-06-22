package com.haitian.servicestaffapp.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: user
 * Date: 2021/4/21
 * Time: 16:26
 */
public class PingJiaListBean {

    /**
     * code : 20041
     * data : [{"evaluate":{"content":null,"startTime":"2024-05-25 18:13:00.0","id":"191e43c04d1c4f64a3555e115809241d","xingji":"5","old_phone":"18854806444","fuwu_value":"打扫卫生","pinglunTime":null,"goodsTypeName":"家政保洁","addr":"要去","oldtupian":"upload/head/20210514/171714972.png","endTime":"2024-05-25 18:13:00.0","old_name":"刘守民","cost":"0.01"},"reply":{"reply_content":"","fuwurenyuanuser_id":72}},{"evaluate":{"content":null,"startTime":"2023-05-21 17:26:00.0","id":"09cdf342330c4d64be5391453aa24822","xingji":"5","old_phone":"15588522851","fuwu_value":"炒锅饭","pinglunTime":null,"goodsTypeName":"美食配送","addr":"i我","oldtupian":"","endTime":"2023-05-21 17:26:00.0","old_name":"汤辟邦","cost":"0.01"},"reply":{"reply_content":"","fuwurenyuanuser_id":72}},{"evaluate":{"content":null,"startTime":"2023-05-21 15:25:00.0","id":"0b773f384e6b41dcb0bc4000b1d04485","xingji":"5","old_phone":"18854806444","fuwu_value":"炒锅饭","pinglunTime":null,"goodsTypeName":"美食配送","addr":"i我","oldtupian":"upload/head/20210514/171714972.png","endTime":"2023-05-21 16:25:00.0","old_name":"刘守民","cost":"0.01"},"reply":{"reply_content":"","fuwurenyuanuser_id":72}},{"evaluate":{"content":null,"startTime":"2023-05-19 20:34:00.0","id":"1e9d5be10e834b3f986f8e31cce46613","xingji":"5","old_phone":"13611127052","fuwu_value":"炒锅饭","pinglunTime":null,"goodsTypeName":"美食配送","addr":"要去","oldtupian":"upload/head/20210526/092259610.png","endTime":"2023-05-19 22:34:00.0","old_name":"华","cost":"0.01"},"reply":{"reply_content":"","fuwurenyuanuser_id":72}},{"evaluate":{"content":null,"startTime":"2022-05-24 16:35:00.0","id":"27e23532511642fbb157f83764fe78f0","xingji":"5","old_phone":"18854806444","fuwu_value":"炒锅饭","pinglunTime":null,"goodsTypeName":"美食配送","addr":"要去","oldtupian":"upload/head/20210514/171714972.png","endTime":"2022-05-24 13:35:00.0","old_name":"刘守民","cost":"0.01"},"reply":{"reply_content":"","fuwurenyuanuser_id":72}},{"evaluate":{"content":null,"startTime":"2021-05-28 15:46:00.0","id":"0fb0a974f73f419ca7ca3555587f7c53","xingji":"5","old_phone":"18854806444","fuwu_value":"炒锅饭","pinglunTime":null,"goodsTypeName":"美食配送","addr":"测试地址","oldtupian":"upload/head/20210514/171714972.png","endTime":"2021-05-28 15:46:00.0","old_name":"刘守民","cost":"0.01"},"reply":{"reply_content":"","fuwurenyuanuser_id":72}},{"evaluate":{"content":null,"startTime":"2021-05-26 16:39:00.0","id":"21e4d674f5be4cefb8e3a636fe9edf36","xingji":"5","old_phone":"18854806444","fuwu_value":"土豆片","pinglunTime":null,"goodsTypeName":"美食配送","addr":"要去","oldtupian":"upload/head/20210514/171714972.png","endTime":"2021-05-26 16:39:00.0","old_name":"刘守民","cost":"0.01"},"reply":{"reply_content":"","fuwurenyuanuser_id":72}},{"evaluate":{"content":null,"startTime":"2021-05-24 11:13:00.0","id":"051dfdae62564a03adeec0e8994f4bd4","xingji":"5","old_phone":"18854806444","fuwu_value":"土豆片","pinglunTime":null,"goodsTypeName":"美食配送","addr":"i我","oldtupian":"upload/head/20210514/171714972.png","endTime":"2021-05-24 11:13:00.0","old_name":"刘守民","cost":"0.01"},"reply":{"reply_content":"","fuwurenyuanuser_id":72}},{"evaluate":{"content":null,"startTime":"2021-04-27 15:51:52.0","id":"434456788978766","xingji":"2","old_phone":"18854806444","fuwu_value":"做饭","pinglunTime":null,"goodsTypeName":"超值配送","addr":null,"oldtupian":"upload/head/20210514/171714972.png","endTime":"2021-04-27 18:50:59.0","old_name":"刘守民","cost":"0.01"},"reply":{"reply_content":null,"fuwurenyuanuser_id":72}}]
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
         * evaluate : {"content":null,"startTime":"2024-05-25 18:13:00.0","id":"191e43c04d1c4f64a3555e115809241d","xingji":"5","old_phone":"18854806444","fuwu_value":"打扫卫生","pinglunTime":null,"goodsTypeName":"家政保洁","addr":"要去","oldtupian":"upload/head/20210514/171714972.png","endTime":"2024-05-25 18:13:00.0","old_name":"刘守民","cost":"0.01"}
         * reply : {"reply_content":"","fuwurenyuanuser_id":72}
         */

        private EvaluateBean evaluate;
        private ReplyBean reply;

        public EvaluateBean getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(EvaluateBean evaluate) {
            this.evaluate = evaluate;
        }

        public ReplyBean getReply() {
            return reply;
        }

        public void setReply(ReplyBean reply) {
            this.reply = reply;
        }

        public static class EvaluateBean {
            /**
             * content : null
             * startTime : 2024-05-25 18:13:00.0
             * id : 191e43c04d1c4f64a3555e115809241d
             * xingji : 5
             * old_phone : 18854806444
             * fuwu_value : 打扫卫生
             * pinglunTime : null
             * goodsTypeName : 家政保洁
             * addr : 要去
             * oldtupian : upload/head/20210514/171714972.png
             * endTime : 2024-05-25 18:13:00.0
             * old_name : 刘守民
             * cost : 0.01
             */

            private Object content;
            private String startTime;
            private String id;
            private String xingji;
            private String old_phone;
            private String fuwu_value;
            private Object pinglunTime;
            private String goodsTypeName;
            private String addr;
            private String oldtupian;
            private String endTime;
            private String old_name;
            private String cost;

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getXingji() {
                return xingji;
            }

            public void setXingji(String xingji) {
                this.xingji = xingji;
            }

            public String getOld_phone() {
                return old_phone;
            }

            public void setOld_phone(String old_phone) {
                this.old_phone = old_phone;
            }

            public String getFuwu_value() {
                return fuwu_value;
            }

            public void setFuwu_value(String fuwu_value) {
                this.fuwu_value = fuwu_value;
            }

            public Object getPinglunTime() {
                return pinglunTime;
            }

            public void setPinglunTime(Object pinglunTime) {
                this.pinglunTime = pinglunTime;
            }

            public String getGoodsTypeName() {
                return goodsTypeName;
            }

            public void setGoodsTypeName(String goodsTypeName) {
                this.goodsTypeName = goodsTypeName;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getOldtupian() {
                return oldtupian;
            }

            public void setOldtupian(String oldtupian) {
                this.oldtupian = oldtupian;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getOld_name() {
                return old_name;
            }

            public void setOld_name(String old_name) {
                this.old_name = old_name;
            }

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }
        }

        public static class ReplyBean {
            /**
             * reply_content :
             * fuwurenyuanuser_id : 72
             */

            private String reply_content;
            private int fuwurenyuanuser_id;

            public String getReply_content() {
                return reply_content;
            }

            public void setReply_content(String reply_content) {
                this.reply_content = reply_content;
            }

            public int getFuwurenyuanuser_id() {
                return fuwurenyuanuser_id;
            }

            public void setFuwurenyuanuser_id(int fuwurenyuanuser_id) {
                this.fuwurenyuanuser_id = fuwurenyuanuser_id;
            }
        }
    }
}

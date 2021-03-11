package com.haitian.servicestaffapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式：yyyy-MM-dd
     */
    public static final String FORMAT_5 = "yyyy-MM-dd";
    public static final String FORMAT_7 = "yyyy-MM";
    public static final String FORMAT_6 = "yyyy.MM.dd";

    /**
     * date类型转换为String类型
     *
     * @param data
     * @param format 日期格式 为空指定则采用默认格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateToString(Date data, String format) {
        format = (format == null || format.length() <= 0) ? FORMAT_1 : format;
        return new SimpleDateFormat(format).format(data);
    }

    /**
     * 获取时间
     * /***
     *
     * @param date
     * @return
     */
    public static String getYearTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    /**
     * 获取时间
     * /***
     *
     * @param date
     * @return
     */
    public static String getMonthTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("M");
        return format.format(date);
    }

    /**
     * 获取时间
     * /***
     *
     * @param date
     * @return
     */
    public static String getDayTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("d");
        return format.format(date);
    }

}

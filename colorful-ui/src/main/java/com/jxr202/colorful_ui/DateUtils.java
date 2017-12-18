package com.jxr202.colorful_ui;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Jxr202 on 2017/12/18
 */

public class DateUtils {

    /**
     * 获取当前时间的前一天时间
     * @param calendar c
     * @return cb
     */
    public static Calendar getBeforeDay(Calendar calendar) {
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        return calendar;
    }

    /**
     * 获取当前时间的后一天时间
     * @param calendar c
     * @return ca
     */
    public static Calendar getAfterDay(Calendar calendar) {
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + 1);
        return calendar;
    }

    /**
     * @param date d
     * @param formatStr f
     * @return d
     */
    public static String dateToString(Date date, String formatStr) {
        DateFormat format = new SimpleDateFormat(formatStr, Locale.getDefault());
        String str = format.format(date);
        return str;
    }


    public static String getTodayDate() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(now.getTime());
    }

    public static String getDaysAgoDate(int daysAgo) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, daysAgo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(c.getTime());
    }

    /**
     * 得到yyyy-MM-dd格式的日期
     */
    public static String getDateTime(int year, int month, int day) {
        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day);
    }

    /**
     * 得到yyyy/MM/dd格式的日期
     */
    public static String getDateTime(long times) {
        Date date = new Date(times);
        return DateUtils.dateToString(date, "yyyy/MM/dd");
    }

    public static String getRateFormatTime(long times) {
        Date date = new Date(times);
        return DateUtils.dateToString(date, "yyyy/MM/dd HH:mm");
    }

    public static String getDetailFormatTime(long times) {
        Date date = new Date(times);
        return DateUtils.dateToString(date, "yyyy-MM-dd HH:mm");
    }

    public static String getRateChartFormatTime(long times) {
        Date date = new Date(times);
        return DateUtils.dateToString(date, "HH:mm");
    }

    public static Long getLongFromTimeString(String str) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            dt = sdf.parse(str);
        } catch (ParseException e) {
        }
        return dt.getTime();
    }

    //把字符串转为日期
    public static Date convertToDate(String strDate) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return df.parse(strDate);
    }


    /**
     * 几天前当天开始的时间戳
     *
     * @param daysAgo
     * @return
     */
    public static long getStartTimeOfDaysAgo(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    //当天结束的时间戳
    public static long getEndTimeOfDaysAgo(int daysAgo) {
        long time = getStartTimeOfDaysAgo(daysAgo + 1);
        return time - 1;
    }


    public static int getHourOfDay(long time) {
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    public static int getTotalDay() {
        //从2016年开始
        int total = 0;
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_YEAR) + 1;
        int year = now.get(Calendar.YEAR);
        int interval = year - 2016;
        for (int i = 2016; i < year; i++) {
            if (i % 4 == 0) {
                total++;
            }
        }
        total = total + day + 365 * interval;
        return total;
    }

    public static int getTotalWeek() {
        //从2016开始
        Calendar begin = new GregorianCalendar();
        begin.set(Calendar.YEAR, 2016);
        begin.set(Calendar.MONTH, 0);
        begin.set(Calendar.DATE, 0);
        begin.set(Calendar.HOUR_OF_DAY, 0);

        Calendar now = Calendar.getInstance();
        int count = 1;
        while (begin.before(now)) {
            if (begin.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                count++;
            }
            begin.add(Calendar.DAY_OF_YEAR, 1);
        }
        return count;
    }

    public static int getTotalMonth() {
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH) + 1;
        int year = now.get(Calendar.YEAR);
        return month + (year - 2016) * 12;
    }


    public static List<Map<String, Object>> getDayLabelList(Context context, int dayTotal) {
        List<Map<String, Object>> dayMapList = new ArrayList<>();
        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.YEAR, 2016);
        begin.set(Calendar.MONTH, 0);
        begin.set(Calendar.DATE, 0);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.getDefault());
        for (int i = 0; i < dayTotal + 2; i++) {
            String dayLabel = format.format(begin.getTimeInMillis());
            Map<String, Object> map = new HashMap<>();
            if (i >= dayTotal) {
                map.put("text", "");
            } else {
                if (i == dayTotal - 1) {
                    map.put("text", context.getString(R.string.date_today));
                } else {
                    map.put("text", dayLabel);
                }
            }
            begin.add(Calendar.DAY_OF_YEAR, 1);
            dayMapList.add(map);
        }
        return dayMapList;
    }


    /**
     * 返回指定日期的月的第一天
     *
     * @return
     */
    public static Date getMonthFirst(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar
                        .MONTH), calendar.getActualMinimum(Calendar
                        .DAY_OF_MONTH), 0,
                0, 0);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的第一天
     *
     * @return
     */
    public static Date getNextMonthFirst(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar
                        .MONTH), calendar.getActualMinimum(Calendar
                        .DAY_OF_MONTH), 0,
                0, 0);
        return calendar.getTime();
    }


    /**
     * 得到上一个月的date
     *
     * @param date
     * @return
     */
    public static Date getPreMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 得到下一个月的date
     *
     * @param date
     * @return
     */
    public static Date getNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 得到yyyy-MM格式的日期
     */
    public static String getSectionDate(long times) {
        Date date = new Date(times);
        return DateUtils.dateToString(date, "yyyy-MM");
    }

    /**
     * 得到MM-dd格式的日期
     */
    public static String getSportItemDate(long times) {
        Date date = new Date(times);
        return DateUtils.dateToString(date, "MM-dd");
    }

    /**
     * 得到HH:mm格式的日期
     */
    public static String getSportItemTime(long times) {
        Date date = new Date(times);
        return DateUtils.dateToString(date, "HH:mm");
    }

    public static String getSportTime(long times) {
        Date date = new Date(times);
        return DateUtils.dateToString(date, "yyyy-MM-dd HH:mm");
    }

}

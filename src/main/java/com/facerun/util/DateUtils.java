package com.facerun.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
public class DateUtils {

    private static String[] monthList = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",};

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static Date parseDate(String date) {
        if (StringUtils.isNotBlank(date) && StringUtils.contains(date, "-")) {
            Map<String, String> mMap = new HashMap<>();
            mMap.put("JAN", "01");
            mMap.put("FEB", "02");
            mMap.put("MAR", "03");
            mMap.put("APR", "04");
            mMap.put("MAY", "05");
            mMap.put("JUN", "06");
            mMap.put("JUL", "07");
            mMap.put("AUG", "08");
            mMap.put("SEP", "09");
            mMap.put("OCT", "10");
            mMap.put("NOV", "11");
            mMap.put("DEC", "12");
            String[] dates = date.split("-");
            try {
                date = dates[0] + "-" + mMap.get(dates[1]).toString() + "-" + dates[2];
                return org.apache.commons.lang3.time.DateUtils.parseDate(date, "dd-MM-yy HH.mm.ss.SSS");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd
     */
    public static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getString4Date(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static Date getDate4Str(String strDate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        try {
            Date date=sdf.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDate8Str(String strDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");//小写的mm表示的是分钟
        try {
            Date currentTime = new Date();
            SimpleDateFormat format = new SimpleDateFormat("HH.mm.ss");
            String timeString = format.format(currentTime);
            strDate = strDate +" "+ timeString;
            Date date=sdf.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得指定日期的后5天
     * 遇周六周末顺延至下周一
     *
     * @param date,days
     * @return
     */
    public static Date getSpecifiedDayAfter(Date date) {
        int days = 5;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK);
        if (week == Calendar.MONDAY) {
            days += 2;
        } else if (week == Calendar.TUESDAY) {
            days += 1;
        }
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + days);
        return c.getTime();
    }

    /**
     * 获得指定日期的前几天
     *
     * @param days
     * @return
     */
    public static Date getSpecifiedDayBefore(int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - days);
        return c.getTime();
    }

    /**
     * 获得指定日期的后3天
     * 遇周六周末顺延至下周一
     *
     * @param date,days
     * @return
     */
    public static Date getSpecified3DayAfter(Date date) {
        int days = 3;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK);
        if (week == Calendar.WEDNESDAY) {
            days += 2;
        } else if (week == Calendar.THURSDAY) {
            days += 1;
        }
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + days);
        return c.getTime();
    }

    /**
     * 获得指定日期的后3天
     * 遇周六周末顺延至下周一
     *
     * @param date,days
     * @return
     */
    public static Date getSpecifiedDay(Date date,int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + days);
        return c.getTime();
    }

    /**
     * 得到UTC时间
     * 如果获取失败，返回null
     *
     * @return
     */
    public static Date getUTCDate() {
        StringBuffer UTCTimeBuffer = new StringBuffer();
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        Date UTCDate = cal.getTime();
        return UTCDate;
    }

    /**
     * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm"<br />
     * 如果获取失败，返回null
     *
     * @return
     */
    public static String getUTCTimeStr() {
        StringBuffer UTCTimeBuffer = new StringBuffer();
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day);
        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute);
        try {
            format.parse(UTCTimeBuffer.toString());
            return UTCTimeBuffer.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将UTC时间转换为东八区时间
     *
     * @param UTCTime
     * @return
     */
    public static String getLocalTimeFromUTC(String UTCTime) {
        Date UTCDate = null;
        String localTimeStr = null;
        try {
            UTCDate = format.parse(UTCTime);
            format.setTimeZone(TimeZone.getTimeZone("GMT-8"));
            localTimeStr = format.format(UTCDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return localTimeStr;
    }

    /**
     * 将时间转换为英文月份
     *
     * @param date
     * @return
     */
    public static String getDateEnMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day + " " + monthList[month] + " " + year.substring(year.length() - 2, year.length());
    }

    /**
     * 将时间转换为英文月份
     *
     * @param date
     * @return
     */
    public static String getDateTimeEnMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        return day + " " + monthList[month] + " " + year.substring(year.length() - 2, year.length())+ " "+hour+":"+min+" UTC";
    }

    private static List <Calendar> holidayList;
    private static boolean holidayFlag;

    /**
     * 计算工作日
     * 具体节日包含哪些,可以在HolidayMap中修改
     * @param src 日期(源)
     * @param lessdays 要减的天数
     * @exception throws [违例类型] [违例说明]
     * @version  [s001, 2010-11-19]
     * @author  shenjunjie
     */
    public static int lessDateByWorkDay(Calendar src,int lessdays)
    {
        int days = 0;
        holidayFlag = false;
        for (int i = 0; i < lessdays; i++)
        {
            //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, -1);
            holidayFlag =checkHoliday(src);
            if(holidayFlag)
            {
                i--;
            }
            days++;
        }
        if (days < lessdays)
            days = lessdays;
        return days;
    }

    /**
     * 计算工作日
     * 具体节日包含哪些,可以在HolidayMap中修改
     * @param src 日期(源)
     * @param adddays 要加的天数
     * @exception throws [违例类型] [违例说明]
     * @version  [s001, 2010-11-19]
     * @author  shenjunjie
     */
    public static Calendar addDateByWorkDay(Calendar src,int adddays)
    {
//        Calendar result = null;
        holidayFlag = false;
        for (int i = 0; i < adddays; i++)
        {
            //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, 1);
            holidayFlag =checkHoliday(src);
            if(holidayFlag)
            {
                i--;
            }
        }
        return src;
    }

    /**
     * 计算工作日
     * 具体节日包含哪些,可以在HolidayMap中修改
     * @param src 日期(源)
     * @param predays 要加的天数
     * @exception throws [违例类型] [违例说明]
     * @version  [s001, 2010-11-19]
     * @author  shenjunjie
     */
    public static Calendar preDateByWorkDay(Calendar src,int predays)
    {
//        Calendar result = null;
        holidayFlag = false;
        for (int i = 0; i < predays; i++)
        {
            //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, -1);
            holidayFlag =checkHoliday(src);
            if(holidayFlag)
            {
                i--;
            }
        }
        return src;
    }

    /**
     * 校验指定的日期是否在节日列表中
     * 具体节日包含哪些,可以在HolidayMap中修改
     * @param src 要校验的日期(源)
     * @version  [s001, 2010-11-19]
     * @author  shenjunjie
     */
    public static boolean checkHoliday(Calendar src)
    {
        boolean result = false;
//        if (holidayList == null)
//        {
//            initHolidayList();
//        }
        //先检查是否是周六周日(有些国家是周五周六)
        if (src.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || src.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
        {
            return true;
        }
//        for (Calendar c : holidayList)
//        {
//            if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH)
//                    && src.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH))
//            {
//                result = true;
//            }
//        }
        return result;
    }

    /**
     * 初始化节日List,如果需要加入新的节日,请在这里添加
     * 加的时候请尽量使用Calendar自带的常量而不是魔鬼数字
     * 注:年份可以随便写,因为比的时候只比月份和天
     * @version  [s001, 2010-11-19]
     * @author  shenjunjie
     */
    private static void initHolidayList()
    {
        holidayList = new ArrayList();

        //五一劳动节
        Calendar may1 = Calendar.getInstance();
        may1.set(Calendar.MONTH,Calendar.MAY);
        may1.set(Calendar.DAY_OF_MONTH,1);
        holidayList.add(may1);

        Calendar may2 = Calendar.getInstance();
        may2.set(Calendar.MONTH,Calendar.MAY);
        may2.set(Calendar.DAY_OF_MONTH,2);
        holidayList.add(may2);

        Calendar may3 = Calendar.getInstance();
        may3.set(Calendar.MONTH,Calendar.MAY);
        may3.set(Calendar.DAY_OF_MONTH,3);
        holidayList.add(may3);

        Calendar h3 = Calendar.getInstance();
        h3.set(2000, 1, 1);
        holidayList.add(h3);

        Calendar h4 = Calendar.getInstance();
        h4.set(2000, 12, 25);
        holidayList.add(h4);

        //中国母亲节：五月的第二个星期日
        Calendar may5 = Calendar.getInstance();
        //设置月份为5月
        may5.set(Calendar.MONTH,Calendar.MAY);
        //设置星期:第2个星期
        may5.set(Calendar.DAY_OF_WEEK_IN_MONTH,2);
        //星期日
        may5.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
//        System.out.println(may5.getTime());

        holidayList.add(may5);
    }

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }
}

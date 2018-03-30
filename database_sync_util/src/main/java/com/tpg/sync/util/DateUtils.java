package com.tpg.sync.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author reph
 * @date 2018/3/26
 */
public class DateUtils {
    private final static String SIMPLE_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    private final static String SIMPLE_DAY_FORMAT="yyyy-MM-dd";
    private final static SimpleDateFormat DATE_FORMAT=new SimpleDateFormat(SIMPLE_DATE_FORMAT);
    private final static SimpleDateFormat DAY_FORMAT =new SimpleDateFormat(SIMPLE_DAY_FORMAT);

    public synchronized static String formatDate(Date date){
        return DATE_FORMAT.format(date);
    }
    public synchronized static String formatDay(Date date){
        return DAY_FORMAT.format(date);
    }
}

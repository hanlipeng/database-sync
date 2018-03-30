package com.tpg.sync.util;

import com.tpg.sync.constant.NormalConstant;
import com.tpg.sync.sync.SyncField;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fengshang
 * Created by nikolasw on 2018/1/24.
 * 数据处理工具类
 */
public class DataDealUtil {

    /**
     * 不同类型的value空值判断
     * @return 是空的 返回true  否则返回false
     */
    public static boolean isEmputyValue(Object value){
        boolean flag = true;
        if (value == null){
            return flag;
        }
        //根据不同类型进行判断
        if (value instanceof String){
            if (!"".equals(value) && !"0".equals(value) && !"0.0".equals(value)){
                flag = false;
            }
        }else if (value instanceof Float){
            if (0f != (float) value ){
                flag = false;
            }
        }else if (value instanceof Double){
            if (0d != (double) value){
                flag = false;
            }
        }else if (value instanceof Integer){
            if (0 != (int) value){
                flag = false;
            }
        }else {
            //其他情况默认为空
            flag = true;
        }
        return flag;
    }

    /**
     * 正则表达式截取字符串中的指定子串
     * @param regexString  正则表达式字符串
     * @param groupIndex  目标子串分组序号
     * @param dealString 待处理字符串
     * @return  指定字符串
     */
    public static String getPatternValue(String regexString,int groupIndex,String dealString){
        String result = "";
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(dealString);
        if (matcher.find()){
            result = matcher.group(groupIndex);
        }
        return result;
    }

    /**
     * 判断是否为数字字符串
     * @param dealString  待判断数字字符串
     * @return  是数字字符串返回true   否则返回false
     */
    public static boolean isNumberString(String dealString){
        boolean flag = true;
        String regEx = "^\\d+$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(dealString);
        boolean flagMatcher = matcher.matches();
        if (!flagMatcher) {
            flag = false;
        }
        return flag;
    }

    /**
     * 去除全角  半角空格 -等特殊字符
     * @param dealString 待处理字符串
     * @return 没有空格的字符串
     */
    public static String clearSpecilChar(String dealString){
        return dealString.replaceAll(NormalConstant.HALF_MINUS_SIGN,NormalConstant.EMPTY_STRING)
                .replaceAll(NormalConstant.FULL_MINUS_SIGN,NormalConstant.EMPTY_STRING)
                .replaceAll(NormalConstant.HALF_WIDTH_SPACES,NormalConstant.EMPTY_STRING)
                .replaceAll(NormalConstant.FULL_WIDTH_SPACES,NormalConstant.EMPTY_STRING);
    }
}

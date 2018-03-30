package com.tpg.sync.constant;

/**
 * @Author reph
 * @Date 2017/11/20
 */
public class TableSpecialKeyConstant {
    /**
     * 判断值true
     */
    public static final String TRUE = "1";
    /**
     * 判断值false
     */
    public static final String FALSE = "0";
    /**
     * 用于存储主键增量同步数据
     */
    public static final String PRIMARY_VALUE = "primaryValue";
    /**
     * 在同步结束后运行的sql
     */
    public static final String END_RUN = "E";
    /**
     * 在同步之前运行的sql
     */
    public static final String START_RUN = "S";


    public static final String ONCE_FLAG = "ONCE";
}

package com.tpg.sync.constant;

/**
 * @Author reph
 * @Date 2017/11/20
 */
public class TableSpecialKeyConstant {
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
    /**
     * 用于请求特殊URL来触发保存数据等类型的操作
     */
    public static final String POST="POST";
    /**
     * 用于判断是否需要时间同步
     */
    public static final String TIME_SET="TIME";

    public static final String TIME_SET_IGNORE="false";
}

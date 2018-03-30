package com.tpg.sync.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 工具类
 *
 * @author reph
 *         两个配置信息对象
 *         加解密方法
 */
public class Utils {
    private static Properties config = new Properties();
    private static Properties sqlrule = new Properties();
    private static Properties tableType = new Properties();
    private static Properties threadConfig = new Properties();
    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static Properties getConfig() {
        if (config.isEmpty()) {
            try {
                config.load(Utils.class.getClassLoader().getResourceAsStream("dbsyncconfig.properties"));
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
        return config;
    }

    public static Properties getSqlRule() {
        if (sqlrule.isEmpty()) {
            try {
                sqlrule.load(Utils.class.getClassLoader().getResourceAsStream("newsqlrule.properties"));
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
        return sqlrule;
    }
    public static Properties getThreadConfig() {
        if (sqlrule.isEmpty()) {
            try {
                threadConfig.load(Utils.class.getClassLoader().getResourceAsStream("threadConfig.properties"));
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
        return threadConfig;
    }

    public static Properties getTableType() {
        if (tableType.isEmpty()) {
            try {
                tableType.load(Utils.class.getClassLoader().getResourceAsStream("tabletype.properties"));
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
        return tableType;
    }

    public static String getDatetime() {
        return getConfig().getProperty("sync.time");
    }

    public static String mapToString(Map<String, Object> map) {
        return new Gson().toJson(map);
    }

    public static String emptyString(String value) {

        return null == value ? "" : value;
    }
    public static Object nullString(Object value) {
        return "".equals(value) ? null : value;
    }

    public static int stringToInt(String value) {
        return value == null || "".equals(value) ? 0 : Integer.valueOf(value);
    }
}

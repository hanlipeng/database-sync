package com.tpg.sync.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 工具类
 *
 * @author reph
 *         两个配置信息对象
 *         加解密方法
 */
public class Utils {
    private static Properties config = new Properties();
    private static Properties sqlRule = new Properties();
    private static Properties tableType = new Properties();
    private static Properties threadConfig = new Properties();
    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static Properties getConfig() {
        return config;
    }

    public static Properties getSqlRule() {
        return sqlRule;
    }
    public static Properties getThreadConfig() {
        return threadConfig;
    }

    public static Properties getTableType() {
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

    public static void loadConfig(File resourceConfigFile, File sqlRuleFile, File tableTypeFile, File threadConfigFile) throws IOException {
        config.load(new FileReader(resourceConfigFile));
        sqlRule.load(new FileReader(sqlRuleFile));
        threadConfig.load(new FileReader(threadConfigFile));
        tableType.load(new FileReader(tableTypeFile));
    }


    public static HashMap<String, Object> jsonToMap(String object) {
        HashMap<String, Object> res;
        try {
            res = new Gson().fromJson(object, new TypeToken<HashMap<String, Object>>() {
            }.getType());
            if (res == null) {
                res = new HashMap<>(1);
            }
        } catch (Exception e) {
            System.out.println(object);
            throw e;
        }
        return res;

    }
}

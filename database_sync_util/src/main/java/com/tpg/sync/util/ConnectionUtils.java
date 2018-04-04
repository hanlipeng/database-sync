package com.tpg.sync.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author reph
 * @date 2017/6/16
 * 查询同步信息数据库的工具类
 */
public class ConnectionUtils {
    private static Connection conn;
    private final static String DRIVER_TYPE = "class.";
    private final static String URL = "url.";
    private final static String INFO_DB_TYPE = "mysql";
    private final static Properties SQL_RULE = Utils.getSqlRule();

    private ConnectionUtils() {
    }

    /**
     * 获取信息数据库连接
     *
     * @return *
     * @throws SQLException *
     * @throws ClassNotFoundException *
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn == null) {
            synchronized (ConnectionUtils.class) {
                if (conn == null) {
                    Properties config = Utils.getConfig();
                    String url = config.getProperty("db.info.mysql.url");
                    String user = config.getProperty("db.info.mysql.user");
                    String pwd = config.getProperty("db.info.mysql.pwd");
                    String dbName = config.getProperty("db.info.mysql.dbname");
                    String driverPath = SQL_RULE.getProperty(DRIVER_TYPE + INFO_DB_TYPE);
                    Class.forName(driverPath);
                    String urlTmp = SQL_RULE.getProperty(URL + INFO_DB_TYPE);
                    url = urlTmp.replace("#{url}", url).replace("#{user}", user).replace("#{pwd}", pwd)
                            .replace("#{dbname}", dbName);
                    try {
                        conn = DriverManager.getConnection(url);
                    } catch (SQLException e) {
                        throw new SQLException("配置数据库连接错误,请检查配置",e);
                    }
                }
            }
        }
        return conn;
    }
}

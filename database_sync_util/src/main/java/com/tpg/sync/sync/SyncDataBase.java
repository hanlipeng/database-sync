package com.tpg.sync.sync;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tpg.sync.constant.SqlConstant;
import com.tpg.sync.util.ConnectionUtils;
import com.tpg.sync.util.Utils;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author reph
 * @date 2018/3/20
 */
public class SyncDataBase {
    private String id;
    public DataBase srcDataBase;
    public DataBase destDataBase;
    private static Map<String, SyncDataBase> dataBaseMap = new HashMap<>();

    public static SyncDataBase getSyncDataBase(String id) throws Exception {
        SyncDataBase syncDataBase = dataBaseMap.get(id);
        if (syncDataBase == null) {
            syncDataBase = new SyncDataBase(id);
            dataBaseMap.put(id, syncDataBase);
        }
        return syncDataBase;
    }

    private SyncDataBase(String id) throws Exception {
        this.id = id;
        init();
    }

    private void init() throws SQLException, ClassNotFoundException, PropertyVetoException {
        //构造SyncDataBase;
        Connection conn = ConnectionUtils.getConnection();
        PreparedStatement pre = conn.prepareStatement(SqlConstant.getDataBaseConfigSql());
        pre.setObject(1, id);
        ResultSet res = pre.executeQuery();
        if (res.next()) {
            String srcUrl = res.getString("src_dburl");
            String srcUser = res.getString("src_dbuser");
            String srcDbPwd = res.getString("src_dbpwd");
            String srcType = res.getString("src_dbtype");
            String srcDbName = res.getString("src_dbname");
            String destUrl = res.getString("dest_dburl");
            String destUser = res.getString("dest_dbuser");
            String destPwd = res.getString("dest_dbpwd");
            String destDbType = res.getString("dest_dbtype");
            String destDbName = res.getString("dest_dbname");
            srcDataBase = new DataBase(srcUrl, srcDbName, srcUser, srcDbPwd, srcType);
            destDataBase = new DataBase(destUrl, destDbName, destUser, destPwd, destDbType);
        }
    }
}

class DataBase {
    private String url;
    private String databaseName;
    private String userName;
    private String pwd;
    private String type;
    private final static String DRIVER_TYPE = "class.";
    private final static String URL = "url.";
    private final Properties SQL_RULE = Utils.getSqlRule();
    private ComboPooledDataSource dataSource;

    DataBase(String url, String databaseName, String userName, String pwd, String type) throws PropertyVetoException {
        this.url = url;
        this.databaseName = databaseName;
        this.userName = userName;
        this.pwd = pwd;
        this.type = type;
//        init();
    }

    private void init() throws PropertyVetoException {
        buildDateSource();
    }

    private void buildDateSource() throws PropertyVetoException {
        String driverPath = SQL_RULE.getProperty(DRIVER_TYPE + type);
        String url = SQL_RULE.getProperty(URL + type);
        url = url.replace("#url", this.url).replace("#dbname", databaseName);
        dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUser(userName);
        dataSource.setPassword(pwd);
        dataSource.setDriverClass(driverPath);
        dataSource.setMaxStatementsPerConnection(180);
        dataSource.setMaxIdleTime(60);
        dataSource.setPreferredTestQuery("select 10000");
    }

    public synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        return buildConnection();
    }

    private Connection buildConnection() throws ClassNotFoundException, SQLException {
        String driverPath = SQL_RULE.getProperty(DRIVER_TYPE + type);
        Class.forName(driverPath);
        String url = SQL_RULE.getProperty(URL + type);
        url = url.replace("#{url}", this.url).replace("#{user}", this.userName).replace("#{pwd}", this.pwd)
                .replace("#{dbname}", this.databaseName);
        return DriverManager.getConnection(url);
    }

    public String getType() {
        return type;
    }

    public String getDatabaseName() {
        return databaseName;
    }
}
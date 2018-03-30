package com.tpg.sync.tableinfo;


import com.tpg.sync.constant.SqlConstant;

import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncTable;
import com.tpg.sync.util.ConnectionUtils;
import com.tpg.sync.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


/**
 * Create by reph on 2017/9/8
 *
 * @author reph
 */
public class InfoGetter {

    private static final Logger logger = LoggerFactory.getLogger(InfoGetter.class);
    private static final Properties CONFIG = Utils.getConfig();

    public void getTableInfo() throws Exception {
        String insertInfoSql = SqlConstant.getInsertFieldInfo();
        String getTableListSql = SqlConstant.getTableInfoSql();
        // 获取表的结构信息
        Connection infoConn = ConnectionUtils.getConnection();
        List<SyncTable> syncTableList = new LinkedList<>();
        ResultSet res = infoConn.createStatement().executeQuery(getTableListSql);
        while (res.next()) {
            int id = res.getInt("id");
            String srcTableName = res.getString("src_table");
            String destTableName = res.getString("dest_table");
            String databaseId = res.getString("db_id");
            syncTableList.add(new SyncTable(id, srcTableName, destTableName, databaseId));
        }
        PreparedStatement pre = infoConn.prepareStatement(insertInfoSql);
        infoConn.setAutoCommit(false);
        try {
            for (SyncTable table : syncTableList) {
                table.buildFieldInfo();
                List<SyncField> fieldList = table.getFieldList();
                for (SyncField f : fieldList) {
                    pre.setObject(1, f.getSrcFieldName());
                    pre.setObject(2, f.getSrcFieldName());
                    pre.setObject(3, "datetime".equals(f.getTime()) ? 120 : null);
                    pre.setObject(4, f.isPrimary());
                    pre.setObject(5, f.getTableId());
                    pre.addBatch();
                }
                logger.info(table.getSrcTableName() + "has end");
            }
            res.close();
            pre.executeBatch();
            infoConn.commit();
        } catch (SQLException e) {
            infoConn.rollback();
            throw e;
        } finally {
            infoConn.close();
        }
    }

    public static void main(String[] args) throws Exception {
        InfoGetter infoGetter = new InfoGetter();
        infoGetter.getTableInfo();
    }
}

package com.tpg.sync.util;

import com.tpg.sync.constant.SqlConstant;
import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author reph
 */
public class TableUtil {
    public static void deleteInfoByPrimaryKeyInGroupTable(SyncTable table,  SyncField syncField) throws SQLException {
        String deleteOldSqlTemp = "delete from #{tableName} where #{fieldName} = '#{value}'";
        Connection destConn=table.getDestConn();
        String sql = deleteOldSqlTemp
                .replace(getReplaceRegex("tableName"), table.getDestTableName())
                .replace(getReplaceRegex("fieldName"), syncField.getDestFieldName())
                .replace(getReplaceRegex("value"), syncField.getDestValue().toString());
        destConn.createStatement().executeUpdate(sql);
    }

    private static String getReplaceRegex(String mark) {
        return String.format("#{%s}", mark);
    }

    public static void updateTableSyncTime(SyncTable... syncTables) throws SQLException, ClassNotFoundException {
        String timeSql = SqlConstant.getTimeUpdateSql();
        PreparedStatement pre = ConnectionUtils.getConnection().prepareStatement(timeSql);
        for(SyncTable table:syncTables) {
            pre.setObject(1, table.getSyncTime());
            pre.setObject(2, table.getId());
            pre.addBatch();
        }
        pre.executeBatch();
    }
}

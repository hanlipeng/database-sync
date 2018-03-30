package com.tpg.sync.syncbuilder.impl;

import com.tpg.sync.constant.NormalConstant;
import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncPreparedStatement;
import com.tpg.sync.sync.SyncTable;
import com.tpg.sync.syncbuilder.SyncMethod;
import com.tpg.sync.util.ConnectionUtils;
import com.tpg.sync.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Create by reph on 2017/9/29
 * @author fengshang
 */
public class OnceSyncMethod implements SyncMethod {

    private static final String INFO_UPDATE_TEMP_SQL = "update #table_name set runable=0 where table_id=?";

    private static final String INFO_TABLE_NAME = Utils.getConfig().getProperty("db.tableinfo.name");

    private static final String TABLE_NAME_REGEX= "#{table_name}";

    @Override
    public void dealData(SyncPreparedStatement pre, List<List<SyncField>> value, SyncTable syncTable) throws SQLException, ClassNotFoundException {
        for (List<SyncField> syncFieldList : value){
            for (SyncField syncField : syncFieldList){
                Object destValue = syncField.getSrcValue();
                if (destValue != null && NormalConstant.EMPTY_STRING.equals(destValue)){
                    destValue = null;
                }
                syncField.setDestValue(destValue);
                pre.setObject(syncField);
            }
            pre.addBatch();
            pre.clearParam();
        }
        Connection infoConn = ConnectionUtils.getConnection();
        PreparedStatement preparedStatement = infoConn.prepareStatement(INFO_UPDATE_TEMP_SQL.replace(TABLE_NAME_REGEX,INFO_TABLE_NAME));
        preparedStatement.setInt(1,syncTable.getId());
        preparedStatement.execute();
    }

}

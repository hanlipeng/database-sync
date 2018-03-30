package com.tpg.sync.syncbuilder;

import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncPreparedStatement;
import com.tpg.sync.sync.SyncTable;

import java.sql.SQLException;
import java.util.List;

/**
 * @author reph
 */
public interface SyncMethod {

    /**
     * 数据处理
     * @param pre SyncPreparedStatement
     * @param value 所有原始数据
     * @param syncTable syncTable
     * @throws SQLException SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    void dealData(SyncPreparedStatement pre, List<List<SyncField>> value, SyncTable syncTable) throws Exception;

}

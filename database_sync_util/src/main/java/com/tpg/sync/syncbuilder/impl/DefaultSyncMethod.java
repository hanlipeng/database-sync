package com.tpg.sync.syncbuilder.impl;


import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncPreparedStatement;
import com.tpg.sync.sync.SyncTable;
import com.tpg.sync.syncbuilder.SyncMethod;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author reph
 */
public class DefaultSyncMethod  implements SyncMethod {

    @Override
    public void dealData(SyncPreparedStatement pre, List<List<SyncField>> value, SyncTable syncTable) throws SQLException {
        for (List<SyncField> list : value){
            for (SyncField field : list){
                field.setDestValue(field.getSrcValue());
                pre.setObject(field);
            }
            pre.addBatch();
            pre.clearParam();
        }
    }

}

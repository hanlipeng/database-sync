package com.tpg.sync.sync;



import com.tpg.sync.syncbuilder.SyncMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author reph
 * @date 2018/3/23
 */
public class SyncDataProcessor extends SyncSonThread {
    private ValueBuilder valueBuilder;
    private SyncMethod method;
    private SyncTable syncTable;
    public SyncDataProcessor(ValueBuilder valueBuilder, SyncMethod method, SyncTable syncTable) {
        this.syncTable=syncTable;
        this.valueBuilder = valueBuilder;
        this.method=method;
    }

    @Override
    void mainRun() throws Exception {
        List<List<SyncField>> value= valueBuilder.getValue();
        Connection destConn = syncTable.getDestConn();
        PreparedStatement pre = destConn.prepareStatement(syncTable.getDestSql());
        SyncPreparedStatement syncPre = new SyncPreparedStatement(pre);
        method.dealData(syncPre,value,syncTable);
        pre.executeBatch();
    }

    @Override
    boolean couldRun() {
        return valueBuilder.hasNext();
    }


}

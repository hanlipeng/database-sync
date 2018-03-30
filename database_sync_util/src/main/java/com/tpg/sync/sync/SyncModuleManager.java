package com.tpg.sync.sync;

import com.tpg.sync.constant.SqlConstant;
import com.tpg.sync.util.ConnectionUtils;
import com.tpg.sync.util.Utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author reph
 * @date 2018/3/27
 */
public class SyncModuleManager implements SyncParentThread {
    private List<SyncModule> moduleList;
    private int listIndex = -1;
    private long countTime;

    public SyncModuleManager(String[] args) throws Exception {
        init(args);
    }

    private void init(String[] args) throws Exception {
        Connection conn = ConnectionUtils.getConnection();
        ResultSet res = conn.createStatement().executeQuery(SqlConstant.getModuleConfigSql(args));
        moduleList=new LinkedList<>();
        while (res.next()){
            int id = res.getInt(1);
            boolean runnable = res.getInt("runnable") == 1;
            String specialKey = res.getString("special_key");
            boolean moduleTransaction = res.getInt("module_transaction") == 1;
            int order = res.getInt("order");
            SyncModule syncModule = new SyncModule(id, runnable, Utils.jsonToMap(specialKey), moduleTransaction, order);
            moduleList.add(syncModule);
        }
    }

    @Override
    public int getMaxNumberOfThreads() {
        return SyncThreadManger.getMaxNumberOfThreadsByType(SyncThreadManger.MODULE_CONFIG_PREFIX);
    }

    @Override
    public void beforeStartThread() {

    }

    @Override
    public void afterEndThread(boolean errorState) {

    }

    @Override
    public SyncSonThread getThread() {
        return moduleList.get(++listIndex);
    }

    @Override
    public boolean hasNext(boolean errorState) {
        return 1+listIndex<moduleList.size();
    }

    @Override
    public boolean needWait(boolean running) {
        return hasNext(true)&&moduleList.get(listIndex+1).getOrder()>moduleList.get(listIndex).getOrder()&&running;
    }

    @Override
    public String getStartLog() {
        countTime=System.currentTimeMillis();
        return "start com.tpg.sync.sync";
    }

    @Override
    public String getEndLog() {
        return "end com.tpg.sync.sync;\ntotal time is "+(System.currentTimeMillis()-countTime)/1000+"s";
    }

    @Override
    public String getErrorLog() {
        return null;
    }

    public List<SyncModule> getModuleList() {
        return moduleList;
    }
}

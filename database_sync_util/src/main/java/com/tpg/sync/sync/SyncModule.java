package com.tpg.sync.sync;

import com.tpg.sync.constant.SqlConstant;
import com.tpg.sync.util.ConnectionUtils;
import com.tpg.sync.util.TableUtil;
import com.tpg.sync.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author reph
 * @date 2018/3/20
 */
public class SyncModule extends SyncSonThread implements SyncParentThread {

    /**
     * 该模块下的同步表
     */
    private List<SyncTable> tableList = new LinkedList<>();

    /**
     * module id
     */
    private int id;

    /**
     * 是否运行
     */
    private boolean runnable;

    /**
     * module特殊字段处理
     */
    private Map<String, Object> specialKey;

    /**
     * 是否需要开启module级别的事物
     */
    private boolean transactionFlag;

    /**
     * module级开启事物的数据库连接
     */
    private Map<String, Connection> transactionConnMaps;

    /**
     * 当前处理的table序号
     */
    private int dealTableIndex = -1;

    /**
     * module的运行顺序;
     */
    private int order;
    private long countTime;

    /**
     * 日志打印工具;
     */

    public SyncModule(int id, boolean runnable, Map<String, Object> specialKey, boolean transactionFlag, int order) throws Exception {
        this.id = id;
        this.runnable = runnable;
        this.specialKey = specialKey;
        this.transactionFlag = transactionFlag;
        this.order = order;
        init();
    }

    private void init() throws Exception {
        buildTableList();
    }

    private void buildTableList() throws Exception {
        //根据moduleId配置所属tableList;
        Connection connection = ConnectionUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.getTableConfigSql());
        preparedStatement.setObject(1, this.id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int tableId = resultSet.getInt("table_id");
            String srcTableName = resultSet.getString("src_table");
            String destTableName = resultSet.getString("dest_table");
            Map<String, Object> specialKey = Utils.jsonToMap(resultSet.getString("special_key"));
            String dataBaseId = resultSet.getString("db_id");
            boolean runnable = false;
            if (resultSet.getInt("runnable") == 1) {
                runnable = true;
            }
            int order = resultSet.getInt("order");
            String tableType = resultSet.getString("table_type");
            String syncTime = resultSet.getString("sync_time");
            SyncTable syncTable = new SyncTable(tableId, srcTableName, destTableName, specialKey, runnable, dataBaseId, this, order, tableType, syncTime);
            tableList.add(syncTable);
        }
        tableList.sort(Comparator.comparingInt(SyncTable::getOrder));
    }

    public Object getSpecialKey(String key) {
        if (specialKey == null) {
            return null;
        }
        return specialKey.get(key);
    }


    @Override
    public int getMaxNumberOfThreads() {
        return SyncThreadManger.getMaxNumberOfThreadsByType(SyncThreadManger.TABLE_CONFIG_PREFIX);
    }

    @Override
    public void beforeStartThread() throws Exception {
        if (transactionFlag) {
            //整合所有table需要的conn,统一管理事务,一般只有一个;
            transactionConnMaps=new HashMap<>(5);
            for (SyncTable table : tableList) {
                String dataBaseId = table.getDataBaseId();
                if (transactionConnMaps.get(dataBaseId) == null) {
                    Connection destConn = SyncDataBase.getSyncDataBase(dataBaseId).destDataBase.getConnection();
                    destConn.setAutoCommit(false);
                    transactionConnMaps.put(dataBaseId, destConn);
                }
            }
        }
    }

    @Override
    public void afterEndThread(boolean errorState) throws Exception {
        if (transactionFlag) {
            Set<String> keySet = transactionConnMaps.keySet();
            for (String key : keySet) {
                Connection transactionConn = transactionConnMaps.get(key);
                if (!errorState) {
                    transactionConn.commit();
                    TableUtil.updateTableSyncTime(tableList.toArray(new SyncTable[tableList.size()]));
                } else {
                    transactionConn.rollback();
                }
                if (!transactionConn.isClosed()) {
                    transactionConn.close();
                }
            }
        }
    }

    @Override
    public SyncSonThread getThread() {

        SyncTable syncTable;
        if (++dealTableIndex < tableList.size()) {
            syncTable = tableList.get(dealTableIndex);
        } else {
            syncTable = null;
        }
        return syncTable;
    }

    @Override
    void mainRun() throws Exception {
        SyncThreadManger syncThreadManger = new SyncThreadManger(this);
        syncThreadManger.startThread();
    }

    @Override
    public boolean hasNext(boolean errorState) {
        //返回是否有下一个需要同步的表 ,
        // 后面的errorState||!transactionFlag的这个判断是说,如果这个module是module级事务,则其中一个表失败,之后的同步就不在进行
        //如果不是.则忽略errorState的判断;
        return dealTableIndex+1 < tableList.size() && (!transactionFlag || !errorState);
    }

    @Override
    public boolean needWait(boolean running) {
        //如果有顺序执行的table时
        //则判断是否之前的table是否都跑完 boolean running,且下一个table的order大于这个table的order
        return hasNext(true)&&tableList.get(dealTableIndex).getOrder() < tableList.get(dealTableIndex + 1).getOrder() && running;
    }

    @Override
    public String getStartLog() {
        countTime=System.currentTimeMillis();
        return "moduleId: "+id+" start com.tpg.sync.sync";
    }

    @Override
    public String getEndLog() {
        return "moduleId: "+id+" has been end\ntotal time is "+(System.currentTimeMillis()-countTime)/1000+"s";
    }

    @Override
    boolean couldRun() {
        return runnable;
    }

    @Override
    public String getErrorLog() {
        return null;
    }

    public boolean isTransactionFlag() {
        return transactionFlag;
    }

    public Connection getTransactionConn(String databaseId) {
        return transactionConnMaps.get(databaseId);
    }

    public int getOrder() {
        return order;
    }

    public List<SyncTable> getTableList() {
        return tableList;
    }

    public void setTableList(List<SyncTable> tableList) {
        this.tableList = tableList;
    }
}

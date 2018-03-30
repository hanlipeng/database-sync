package com.tpg.sync.sync;

import com.google.gson.Gson;
import com.tpg.sync.constant.SqlConstant;
import com.tpg.sync.constant.TableSpecialKeyConstant;
import com.tpg.sync.factory.TableFactory;
import com.tpg.sync.sqlbuilder.SqlBuilder;
import com.tpg.sync.syncbuilder.SyncMethod;
import com.tpg.sync.util.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.tpg.sync.constant.TableSpecialKeyConstant.*;

/**
 * @author reph
 * @date 2018/3/20
 */
public class SyncTable extends SyncSonThread implements SyncParentThread {
    private String syncTime;
    private int id;
    private String srcTableName;
    private String destTableName;
    private List<SyncField> fieldList;
    private String srcSql;
    private String destSql;
    private Map<String, Object> specialKey;
    private String tableType;
    private boolean runnable;
    private SyncModule module;
    private String dataBaseId;
    private SyncDataBase syncDataBase;
    private int order;
    private SyncMethod syncMethod;
    private SyncField primaryField;
    private Connection destConn;
    private ValueBuilder valueBuilder;
    private long countTime;


    public SyncTable(int id, String srcTableName, String destTableName, String dataBaseId) throws Exception {
        this.id = id;
        this.srcTableName = srcTableName;
        this.destTableName = destTableName;
        this.dataBaseId = dataBaseId;
        chooseDataBase();
    }

    public SyncTable(int id, String srcTableName,
                     String destTableName, Map<String, Object> specialKey,
                     boolean runnable, String dataBaseId, SyncModule module,
                     int order, String tableType, String syncTime) throws Exception {
        this.id = id;
        this.srcTableName = srcTableName;
        this.destTableName = destTableName;
        this.specialKey = specialKey;
        this.runnable = runnable;
        this.dataBaseId = dataBaseId;
        this.module = module;
        this.order = order;
        this.tableType = tableType;
        this.syncTime = syncTime;
        init();
    }

    public static SyncTable getTableBaseInfoByTableId(int id) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.getTableConfigByTableIdSql());
        preparedStatement.setObject(1, id);
        SyncTable syncTable = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int tableId = resultSet.getInt("table_id");
            String srcTableName = resultSet.getString("src_table");
            String destTableName = resultSet.getString("dest_table");
            String dataBaseId = resultSet.getString("db_id");
            syncTable = new SyncTable(tableId, srcTableName, destTableName, dataBaseId);
            syncTable.buildField();
        }
        return syncTable;
    }

    private void init() throws Exception {
        syncMethod = TableFactory.getSyncMethodInstanceByTableType(tableType);
        chooseDataBase();
        buildField();
        buildSql();
    }

    private void buildValueBuilder() throws SQLException, ClassNotFoundException {
        valueBuilder = new ValueBuilder(this);
    }

    private void chooseDataBase() throws Exception {
        syncDataBase = SyncDataBase.getSyncDataBase(dataBaseId);
    }

    private void buildField() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        PreparedStatement pre = conn.prepareStatement(SqlConstant.getFieldConfigSql());
        pre.setObject(1, id);
        ResultSet res = pre.executeQuery();
        int position = 1;
        fieldList = new LinkedList<>();
        while (res.next()) {
            int fieldId = res.getInt("field_id");
            String srcFieldName = res.getString("src_field_name");
            String destFieldName = res.getString("dest_field_name");
            boolean isPrimary = res.getInt("is_primary") == 1;
            String time = res.getString("is_time");
            String joinFieldId = res.getString("join_field_id");
            Map<String, Object> specialKey = Utils.jsonToMap(res.getString("special_key"));
            SyncField syncField = new SyncField(fieldId, srcFieldName, destFieldName, position++, specialKey, isPrimary, id, time, joinFieldId);
            if (isPrimary) {
                this.primaryField = syncField;
            }
            fieldList.add(syncField);
        }
        fieldList.sort(Comparator.comparingInt(SyncField::getPosition));
    }

    private void buildSql() throws Exception {
        SqlBuilder srcSqlBuilder = TableFactory.getSrcSqlBuilderInstanceByTableType(tableType);
        SqlBuilder destSqlBuilder = TableFactory.getDestSqlBuilderInstanceByTableType(tableType);
        srcSql = srcSqlBuilder.buildSql(this);
        destSql = destSqlBuilder.buildSql(this);
    }

    public int getOrder() {
        return order;
    }

    public String getSrcDataBaseType() {
        return syncDataBase.srcDataBase.getType();
    }

    public String getDestDataBaseType() {
        return syncDataBase.destDataBase.getType();
    }

    public List<SyncField> getFieldList() {
        return fieldList;
    }

    public String getSrcTableName() {
        return srcTableName;
    }

    public String getDestTableName() {
        return destTableName;
    }

    public String getSyncTime() {
        return syncTime;
    }

    public Object getSpecialKey(String key) {
        return specialKey == null ? null : specialKey.get(key);
    }

    public SyncField getPrimaryField() {
        return primaryField;
    }

    public int getId() {
        return id;
    }

    @Override
    void mainRun() throws Exception {
        SyncThreadManger syncThreadManger = new SyncThreadManger(this);
        syncThreadManger.startThread();
//        System.out.println("table Start com.tpg.sync.sync");
//        Thread.sleep(10000);
//        System.out.println("table End com.tpg.sync.sync");
    }

    @Override
    boolean couldRun() {
        return runnable;
    }

    public Connection getDestConn() {
        return destConn;
    }

    public String getDestSql() {
        return destSql;
    }

    public String getSrcSql() {
        return srcSql;
    }

    public Connection getSrcConn() throws SQLException, ClassNotFoundException {
        return syncDataBase.srcDataBase.getConnection();
    }

    @Override
    public int getMaxNumberOfThreads() {
        return SyncThreadManger.getMaxNumberOfThreadsByType(SyncThreadManger.DEAL_DATA_CONFIG_PREFIX);
    }

    @Override
    public void beforeStartThread() throws Exception {
        buildValueBuilder();
        syncTime = DateUtils.formatDate(new Date());
        if (module.isTransactionFlag()) {
            destConn = module.getTransactionConn(dataBaseId);
        } else {
            destConn = syncDataBase.destDataBase.getConnection();
            destConn.setAutoCommit(false);
        }
        startRun();
    }

    @Override
    public void afterEndThread(boolean errorState) throws Exception {
        if (!module.isTransactionFlag()) {
            if (!errorState) {
                endRun();
                destConn.commit();
            } else {
                destConn.rollback();
            }
            destConn.close();
        } else {
            if (errorState) {
                threadManage.changeErrorState();
            }
        }
        System.gc();
    }

    @Override
    public SyncSonThread getThread() {
        return new SyncDataProcessor(valueBuilder, syncMethod, this);
    }

    @Override
    public boolean hasNext(boolean errorState) {
        return valueBuilder.hasNext() && !errorState;
    }

    @Override
    public boolean needWait(boolean running) {
        return false;
    }

    @Override
    public String getStartLog() {
        countTime = System.currentTimeMillis();
        String s = "srcTable: " + srcTableName + " -->destTable: " + destTableName + " start com.tpg.sync.sync";
        if (DEBUG) {
            s = s + "\nsrcsql: " + srcSql + "\ndestSql: " + destSql + "\n";
        }
        return s;
    }

    @Override
    public String getEndLog() {
        return "srcTable:" + srcTableName + "-->destTable:" + destTableName + "has been com.tpg.sync.sync\ntotal time is " + (System.currentTimeMillis() - countTime) / 1000 + "s"
                + "\n total data is : " + valueBuilder.getTotal();
    }

    @Override
    public String getErrorLog() {
        return "srcTable:" + srcTableName + "-->destTable:" + destTableName + "has error" + "\nsrcsql:" + srcSql + "\ndestSql:" + destSql + "\n";
    }

    private void endRun() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        if (specialKey.get(END_RUN) != null) {
            sqlRun(specialKey.get(END_RUN), destConn);
        }
        if (specialKey.get(TableSpecialKeyConstant.PRIMARY_VALUE) != null) {
            String value = "0";
            String maxKeySelectTemp = "Select max(#primary) from #tableName";
            String maxSql = maxKeySelectTemp.replace("#primary", primaryField.getSrcFieldName()).replace("#tableName", srcTableName);
            ResultSet res = getSrcConn().createStatement().executeQuery(maxSql);
            if (res.next()) {
                value = res.getString(1);
            }
            String updateSql = "update " + Utils.getConfig().getProperty("db.tableinfo.name") + " set special_key = ? where table_id = ?";
            PreparedStatement pre = conn.prepareStatement(updateSql);
            pre.setObject(2, id);
            specialKey.put(PRIMARY_VALUE, value);
            pre.setObject(1, new Gson().toJson(specialKey));
            pre.execute();
        }
        if (!module.isTransactionFlag()) {
            TableUtil.updateTableSyncTime(this);
        }
    }

    private void startRun() throws Exception {
        Double d = 101.0;
        if (specialKey.get(POST) != null) {
            String post = HttpUtil.post(specialKey.get(POST).toString(), null);
            HashMap<String, Object> res = Utils.jsonToMap(post);
            boolean code = d.equals(res.get("code"));
            if (!code) {
                throw new Exception("post has error url:" + specialKey.get(POST).toString());
            }
        }
        if (specialKey.get(START_RUN) != null) {
            sqlRun(specialKey.get(START_RUN), destConn);
        }
    }

    private void sqlRun(Object o, Connection conn) throws SQLException {
        if (o != null) {
            if (o instanceof List) {
                for (Object sql : (List) o) {
                    conn.createStatement().executeUpdate(sql.toString());
                }
            } else {
                conn.createStatement().executeUpdate(o.toString());
            }
        }
    }

    public String getDataBaseId() {
        return dataBaseId;
    }

    public SyncDataBase getSyncDataBase() {
        return syncDataBase;
    }

    public void buildFieldInfo() throws SQLException, ClassNotFoundException {
        String tableInfoSql = Utils.getSqlRule().getProperty("fieldinfo." + syncDataBase.srcDataBase.getType());
        Connection conn = syncDataBase.srcDataBase.getConnection();
        PreparedStatement infoSta = conn.prepareStatement(tableInfoSql);
        infoSta.setString(1, srcTableName);
        if (Objects.equals(syncDataBase.srcDataBase.getType(), "mysql")) {
            infoSta.setString(2, syncDataBase.srcDataBase.getDatabaseName());
        }
        ResultSet res = infoSta.executeQuery();
        while (res.next()) {
            String fieldName = res.getString("field_name");
            boolean identify = res.getBoolean("is_identify");
            SyncField field = new SyncField(0, fieldName, fieldName, 0, null, identify, id, null, null);
            fieldList.add(field);
        }
    }
}

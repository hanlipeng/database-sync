package com.tpg.sync.sync;

import com.tpg.sync.constant.SqlConstant;
import com.tpg.sync.util.ConnectionUtils;
import com.tpg.sync.util.Utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author reph
 */
public class SyncField {

    /**
     * fieldId
     */
    private int id;

    /**
     * 老系统目标字段名
     */
    private String srcFieldName;

    /**
     * 新数据库字段名
     */
    private String destFieldName;

    /**
     * 位置
     */
    private int position;

    /**
     * filed specialKey
     */
    private Map<String,Object> specialKey;

    /**
     * 主键标志
     */
    private boolean isPrimary;

    /**
     * 时间
     */
    private String time;

    /**
     * 关联filedId
     */
    private String joinFieldId;

    /**
     * tableId
     */
    private int tableId;

    /**
     * 源filed数据
     */
    private Object srcValue;

    /**
     * 目标filed数据
     */
    private Object destValue;

    /**
     * 特殊的pre.setObject时使用
     * @param position
     * @param destValue
     */
    public SyncField(int position, Object destValue) {
        this.position = position;
        this.destValue = destValue;
    }

    public SyncField(SyncField syncField){
        this.id = syncField.id;
        this.srcFieldName = syncField.srcFieldName;
        this.destFieldName = syncField.destFieldName;
        this.position = syncField.position;
        this.specialKey = syncField.specialKey;
        this.isPrimary = syncField.isPrimary;
        this.time = syncField.time;
        this.joinFieldId = syncField.joinFieldId;
        this.tableId = syncField.tableId;
        this.srcValue = syncField.srcValue;
        this.destValue = syncField.destValue;
    }


    public SyncField(int id, String srcFieldName, String destFieldName, int position, Map<String, Object> specialKey, boolean isPrimary,int tableId, String time, String joinFieldId) {
        this.id = id;
        this.srcFieldName = srcFieldName;
        this.destFieldName = destFieldName;
        this.position = position;
        this.specialKey = specialKey;
        this.joinFieldId=joinFieldId;
        this.isPrimary=isPrimary;
        this.time=time;
        this.tableId=tableId;
    }
    public static SyncField getSyncFieldByFieldId(int id) throws SQLException, ClassNotFoundException {
        SyncField syncField=null;
        Connection conn = ConnectionUtils.getConnection();
        PreparedStatement pre = conn.prepareStatement(SqlConstant.getFieldConfigByFieldIdSql());
        pre.setObject(1,id);
        ResultSet res = pre.executeQuery();
        while (res.next()){
            int fieldId = res.getInt("field_id");
            String srcFieldName = res.getString("src_field_name");
            String destFieldName = res.getString("dest_field_name");
            boolean isPrimary = res.getInt("is_primary") == 1;
            String time = res.getString("is_time");
            int tableId = res.getInt("table_id");
            String joinFieldId = res.getString("join_field_id");
            Map<String,Object> specialKey= Utils.jsonToMap(res.getString("special_key"));
            syncField = new SyncField(fieldId, srcFieldName, destFieldName, 0, specialKey,isPrimary,tableId,time,joinFieldId);
        }
        return syncField;
    }

    public String getSrcFieldName() {
        return srcFieldName;
    }

    public String getDestFieldName() {
        return destFieldName;
    }

    public Integer getPosition() {
        return position;
    }

    public Object getSpecialKey(String key) {
        if(specialKey==null){
            return null;
        }
        return specialKey.get(key);
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public String getTime() {
        return time;
    }

    public String getJoinFieldId() {
        return joinFieldId;
    }

    public int getTableId() {
        return tableId;
    }

    @Override
    public SyncField clone(){
        return new SyncField(this);
    }

    public Object getSrcValue() {
        return srcValue;
    }

    public void setSrcValue(Object srcValue) {
        this.srcValue = srcValue;
    }

    public Object getDestValue() {
        return destValue;
    }

    public void setDestValue(Object destValue) {
        this.destValue = destValue;
    }

    public SyncField getEmptyValueField(){
        SyncField syncField = new SyncField(this);
        syncField.srcValue=null;
        syncField.destValue=null;
        return syncField;
    }

}

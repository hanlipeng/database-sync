package com.tpg.sync.sqlbuilder.srcsqlimpl;

import com.mysql.jdbc.StringUtils;
import com.tpg.sync.sqlbuilder.SqlBuilder;
import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncTable;

import java.sql.SQLException;
import java.util.List;

/**
 * @author reph
 * @date 2017/6/23
 */
public class TimeJoinSrcBuilder implements SqlBuilder {
    /**
     * 构键时间键联合查询的sql语句
     *
     * @param syncTable 被构建的表
     * @return 构建好的sql语句
     * @throws SQLException           Connection 的异常抛出
     * @throws ClassNotFoundException Connection 的异常抛出
     */
    @Override
    public String buildSql(SyncTable syncTable) throws Exception {
        //常量及所需变量的定义
        String srcDataBaseType = syncTable.getSrcDataBaseType();
        String selectTemp = getSqlModule(srcDataBaseType, JOIN_SQL_PREFIX);
        String joinTemp = getSqlModule(srcDataBaseType, JOIN_TABLE_PREFIX);
        String dateTemp = getSqlModule(srcDataBaseType, JOIN_DATE_PREFIX);
        String fieldTemp = getSqlModule(srcDataBaseType, FIELD_PREFIX);
        StringBuilder fieldValue = new StringBuilder();
        List<SyncField> fields = syncTable.getFieldList();
        StringBuilder timeCondition = new StringBuilder();
        String mainFieldName = "";
        SyncField joinFieldInfo = null;
        SyncTable joinTableInfo = null;

        for (SyncField field : fields) {
            fieldValue.append(fieldTemp.replace(FIELD_NAME_REG, field.getSrcFieldName()));
            //联合查询键判断
            if (field.getJoinFieldId() != null) {
                mainFieldName = field.getSrcFieldName();
                joinFieldInfo = SyncField.getSyncFieldByFieldId(Integer.parseInt(field.getJoinFieldId()));
                joinTableInfo = SyncTable.getTableBaseInfoByTableId(joinFieldInfo.getTableId());
            }
        }
        if(joinFieldInfo==null){
            throw new Exception("can't find join field");
        }
        if(joinTableInfo==null){
            throw new Exception("can't find join table");
        }
        //生成判断语句
        for (SyncField field : joinTableInfo.getFieldList()) {
            if (!StringUtils.isNullOrEmpty(field.getTime())) {
                String data = dateTemp.replace(TABLE_NAME_REG, "b").replace(FIELD_TIME_REG, field.getSrcFieldName())
                        .replace(TIME_CONDITION_REG, getDatetimeOfDay(syncTable)).
                        replace(TIME_TYPE_REG, field.getTime());
                timeCondition.append(" or ").append(data);
            }
        }
        //生成join语句
        String joinSql = joinTemp.replace(JOIN_TABLE_NAME_REG, joinTableInfo.getSrcTableName()).
                replace(JOIN_FIELD_NAME_REG, joinFieldInfo.getSrcFieldName()).replace(MAIN_FIELD_NAME_REG, mainFieldName);
        //删除最后都多余的','
        fieldValue.deleteCharAt(fieldValue.length() - 1);
        return selectTemp.replace(FIELD_LIST_REG, fieldValue.toString()).replace(TABLE_NAME_REG, syncTable.getSrcTableName()).
                replace(CONDITION_REG, "AND (" + timeCondition.substring(3) + ")").replace(JOIN_TABLE_SQL_REG, joinSql);
    }
}

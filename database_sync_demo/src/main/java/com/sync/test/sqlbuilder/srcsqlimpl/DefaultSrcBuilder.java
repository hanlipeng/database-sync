package com.sync.test.sqlbuilder.srcsqlimpl;

import com.sync.test.sqlbuilder.BaseSqlBuilder;
import com.tpg.sync.exception.NoTimeException;
import com.tpg.sync.sqlbuilder.SqlBuilder;
import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncTable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author reph
 * @date 2017/6/20
 */
public class DefaultSrcBuilder extends BaseSqlBuilder implements SqlBuilder {


    @Override
    public String buildSql(SyncTable syncTable) throws NoTimeException, ParseException {
        String sqlModel = getSqlModule(syncTable.getSrcDataBaseType(), SQL_PREFIX);
        String primary = syncTable.getPrimaryField().getSrcFieldName();
        String fields = buildFields( syncTable);
        String timeCondition = buildTimeCondition( syncTable);
        List<String> conditions = new ArrayList<>();
        if (timeCondition.length() > 0) {
            conditions.add("and (" + timeCondition + ")");
        } else {
            throw new NoTimeException("tableinfo:" + syncTable.getSrcTableName() + " has no timefield");
        }
        return sqlModel.replace(FIELD_LIST_REG, fields)
                .replace(CONDITION_REG, buildCondition(conditions))
                .replace(PRIMARY_REG, primary)
                .replace(TABLE_NAME_REG, syncTable.getSrcTableName());
    }

    String buildFields(SyncTable table) {
        String fieldTemp = getSqlModule(table.getSrcDataBaseType(), FIELD_PREFIX);
        StringBuilder fields = new StringBuilder();
        List<SyncField> fieldList = table.getFieldList();
        for (SyncField field : fieldList) {
            fields.append(fieldTemp.replace(FIELD_NAME_REG, field.getSrcFieldName()));
        }
        return fields.deleteCharAt(fields.length() - 1).toString();
    }

    String buildTimeCondition( SyncTable table) throws ParseException {
        StringBuilder timeCondition = new StringBuilder();
        String timeTemp = getSqlModule(table.getSrcDataBaseType(),DATA_PREFIX );
        boolean timeFlag = true;
        List<SyncField> fieldList = table.getFieldList();
        for (SyncField field : fieldList) {
            if (field.getTime() != null) {
                timeFlag = false;
                timeCondition.append(" or ").append(timeTemp.replace(FIELD_TIME_REG, field.getSrcFieldName())
                        .replace(TIME_CONDITION_REG, getDatetimeOfDay(table)).replace(TIME_TYPE_REG, field.getTime()));
            }
        }
        if (timeFlag) {
            return "";
        }
        return timeCondition.substring(3);
    }


    String buildCondition(List<String> conditions) {
        StringBuilder condition = new StringBuilder();
        for (String c : conditions) {
            condition.append(c);
        }
        return condition.toString();
    }
}

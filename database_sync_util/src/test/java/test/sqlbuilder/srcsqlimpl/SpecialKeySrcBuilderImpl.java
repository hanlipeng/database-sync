package test.sqlbuilder.srcsqlimpl;

import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncTable;
import com.tpg.sync.util.Utils;
import test.exception.NoTimeException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static test.constant.FieldSpecialKeyConstant.*;
import static test.constant.TableSpecialKeyConstant.TIME_SET;
import static test.constant.TableSpecialKeyConstant.TIME_SET_IGNORE;

/**
 * Create by reph on 2017/10/26
 *
 * @author reph
 */
public class SpecialKeySrcBuilderImpl extends DefaultSrcBuilder {
    @Override
    public String buildSql(SyncTable syncTable) throws NoTimeException, ParseException {
        String sqlModel = getSqlModule(syncTable.getSrcDataBaseType(), JOIN_SQL_PREFIX);
        ArrayList<String> conditions = new ArrayList<>();
        String primary = syncTable.getPrimaryField()!=null?syncTable.getPrimaryField().getSrcFieldName():"";
        String fields = buildFields(syncTable);
        String timeCondition = buildTimeCondition(syncTable);
        if (!TIME_SET_IGNORE.equals(syncTable.getSpecialKey(TIME_SET))) {
            if (timeCondition.length() > 0) {
                conditions.add("and (" + timeCondition + ")");
            } else {
                throw new NoTimeException("tableinfo:" + syncTable.getSrcTableName() + " has no timefield");
            }
        }
        //C是追加的特殊限制条件
        String c = (String) syncTable.getSpecialKey("C");

        conditions.add(Utils.emptyString(c).replace(TIME_CONDITION_REG, getDatetimeOfDay(syncTable)));
        //J是联合查询的句子
        return sqlModel.replace(JOIN_TABLE_SQL_REG, Utils.emptyString((String) syncTable.getSpecialKey("J")))
                .replace(FIELD_LIST_REG, fields)
                .replace(CONDITION_REG, buildCondition(conditions))
                .replace(PRIMARY_REG, primary)
                .replace(TABLE_NAME_REG, syncTable.getSrcTableName());
    }

    @Override
    String buildFields(SyncTable syncTable) {
        String fieldTemp = getSqlModule(syncTable.getSrcDataBaseType(), FIELD_PREFIX);
        String joinFieldTemp = getSqlModule(syncTable.getSrcDataBaseType(), JOIN_FIELD_PREFIX);
        StringBuilder fields = new StringBuilder();
        List<SyncField> fieldList = syncTable.getFieldList();
        for (SyncField field : fieldList) {
            String fieldSql = fieldTemp.replace(FIELD_NAME_REG, field.getSrcFieldName());
            if (Objects.equals(field.getSpecialKey(SRC_FIELD_IGNORE_KEY), FIELD_IGNORE)) {
                continue;
            }
            if (Objects.equals(field.getSpecialKey(JOIN_FIELD_KEY), JOIN_FIELD_FLAG)) {
                fieldSql = joinFieldTemp.replace(FIELD_NAME_REG, field.getSrcFieldName());
            }
            fields.append(fieldSql);
        }
        return fields.deleteCharAt(fields.length() - 1).toString();
    }
}

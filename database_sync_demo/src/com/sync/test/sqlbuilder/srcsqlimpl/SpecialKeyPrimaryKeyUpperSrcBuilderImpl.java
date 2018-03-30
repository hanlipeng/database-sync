package com.sync.test.sqlbuilder.srcsqlimpl;

import com.tpg.sync.exception.NoTimeException;
import com.tpg.sync.sync.SyncTable;
import com.tpg.sync.util.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.tpg.sync.constant.TableSpecialKeyConstant.PRIMARY_VALUE;


/**
 * Create by reph on 2017/10/31
 *
 * @author reph
 */
public class SpecialKeyPrimaryKeyUpperSrcBuilderImpl extends SpecialKeySrcBuilderImpl {

    @Override
    public String buildSql(SyncTable syncTable) throws NoTimeException,   ParseException {
        String primaryTemp = getSqlModule(syncTable.getSrcDataBaseType(),PRIMARY_KEY_UPPER_PREFIX  );
        String maxPrimary = getMaxPrimary(syncTable);
        String primarySql = primaryTemp.replace(VALUE_REG, maxPrimary);
        String sqlModel = getSqlModule(syncTable.getSrcDataBaseType(),SQL_PREFIX );
        String primary = syncTable.getPrimaryField().getSrcFieldName();
        String fields = buildFields(syncTable);
        String timeCondition = buildTimeCondition(syncTable);
        List<String> conditions = new ArrayList<>();
        String c = (String) syncTable.getSpecialKey("C");
        conditions.add(Utils.emptyString(c));
        if (timeCondition.length() > 0) {
            conditions.add("and (" + timeCondition + primarySql + ")");
        } else {
            throw new NoTimeException("tableinfo:" + syncTable.getSrcTableName() + " has no timefield");
        }

        return sqlModel
                .replace(FIELD_LIST_REG, fields)
                .replace(CONDITION_REG, buildCondition(conditions))
                .replace(PRIMARY_REG, primary)
                .replace(TABLE_NAME_REG, syncTable.getSrcTableName());
    }

    private String getMaxPrimary(SyncTable syncTable) {
        String res = "0";
        Object primaryKey = syncTable.getSpecialKey(PRIMARY_VALUE);
        if (primaryKey != null && primaryKey instanceof String) {
            res = (String) primaryKey;
        }
        return res;
    }

}

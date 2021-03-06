package test.sqlbuilder.destsqlimpl;

import com.tpg.sync.sqlbuilder.SqlBuilder;
import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncTable;
import test.sqlbuilder.BaseSqlBuilder;

import java.util.List;

import static test.constant.FieldSpecialKeyConstant.*;


/**
 * Create by reph on 2017/10/14
 *
 * @author reph
 */
public class SpecialKeyDestSqlBuilder extends BaseSqlBuilder implements SqlBuilder {
    @Override
    public String buildSql(SyncTable syncTable) {
        String fieldTemp = getSqlModule(syncTable.getDestDataBaseType(),UPDATE_FIELD_TMP_PREFIX);
        List<SyncField> fieldList = syncTable.getFieldList();
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (SyncField field : fieldList) {
            if (FIELD_IGNORE.equals(field.getSpecialKey(DEST_FIELD_IGNORE))) {
                continue;
            }
            fields.append(fieldTemp.replace(FIELD_NAME_REG, field.getDestFieldName()));
            values.append(TEMP_VALUE_REG);
        }
        fields.deleteCharAt(fields.length() - 1);
        values.deleteCharAt(values.length() - 1);
        String updateSql = getSqlModule(syncTable.getDestDataBaseType(),UPDATE_SQL_PREFIX);
        return updateSql.replace(FIELD_LIST_REG, fields.toString())
                .replace(TABLE_NAME_REG, syncTable.getDestTableName())
                .replace(DEST_VALUE_LIST_REG, values.toString());
    }
}

package test.sqlbuilder.destsqlimpl;

import com.tpg.sync.sqlbuilder.SqlBuilder;
import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncTable;
import test.sqlbuilder.BaseSqlBuilder;

import java.util.List;

/**
 *
 * @author reph
 */
public class DefaultDestSqlBuilder extends BaseSqlBuilder implements SqlBuilder {
    @Override
    public String buildSql(SyncTable syncTable) {
        String fieldTemp = getSqlModule(syncTable.getDestDataBaseType(), UPDATE_FIELD_TMP_PREFIX);
        List<SyncField> fieldList = syncTable.getFieldList();
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (SyncField field : fieldList) {
            fields.append(fieldTemp.replace(FIELD_NAME_REG, field.getDestFieldName()));
            values.append(TEMP_VALUE_REG);
        }
        fields.deleteCharAt(fields.length() - 1);
        values.deleteCharAt(values.length() - 1);
        String updateSql = getSqlModule(syncTable.getDestDataBaseType(), UPDATE_SQL_PREFIX);
        return updateSql.replace(FIELD_LIST_REG, fields.toString())
                .replace(TABLE_NAME_REG, syncTable.getDestTableName())
                .replace(DEST_VALUE_LIST_REG, values.toString());
    }
}

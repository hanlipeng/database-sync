package com.tpg.sync.sqlbuilder.srcsqlimpl;


import com.tpg.sync.sync.SyncTable;

/**
 *
 * @author reph
 * @date 2017/6/22
 */
public class NoTimeSrcBuilder extends DefaultSrcBuilder {
    @Override
    public String buildSql(SyncTable syncTable) {
        String sqlModel = getSqlModule(syncTable.getSrcDataBaseType(),"select." );
        String fields = buildFields(syncTable);
        return sqlModel
                .replace(FIELD_LIST_REG, fields)
                .replace(CONDITION_REG, "")
                .replace(PRIMARY_REG, "")
                .replace(TABLE_NAME_REG,syncTable.getSrcTableName());
    }
}

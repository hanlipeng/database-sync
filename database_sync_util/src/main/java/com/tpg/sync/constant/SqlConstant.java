package com.tpg.sync.constant;



import com.tpg.sync.util.Utils;

import java.util.Properties;

/**
 * Created by nikolasw on 2018/3/20.
 */
public class SqlConstant {

    /**
     * 获取根据moduleid获取tableSQl
     */
    private final static String DATABASE_SQL_TMP = "select * from #dataBaseInfo where db_id=?";
    private final static String FIELD_SQL_TMP = "Select * from #fieldinfo where table_id=?";
    private final static String ONE_FIELD_SQL_TMP = "Select * from #fieldinfo where field_id=?";
    private final static String TABLE_SQL_TMP = "SELECT * FROM #tableinfo a where module_id=?";
    private final static String TABLE_INFO_SQL_TMP = "SELECT * FROM #tableinfo a where get_info=1";
    private final static String ONE_TABLE_SQL_TMP = "SELECT * FROM #tableinfo a where table_id=?";
    private final static String MODULE_SQL_TMP = "select * from #moduleinfo where 1=1 #condition ";
    private final static String TIME_UPDATE_SQL = "update #table_name set sync_time = ? where table_id = ?";
    private final static String INSERT_FIELD_INFO_SQL = "REPLACE INTO `#fieldInfo` (`src_field_name`, `dest_field_name`, `field_type`, `field_length`, `is_time`, `m2m`, `is_primary`, `join_table_id`, `table_id`) " +
            "VALUES (?, ?, ?, ?, ?, NULL, ?, NULL, ?);";

    private final static Properties CONFIG = Utils.getConfig();

    public static String getDataBaseConfigSql() {
        String property = CONFIG.getProperty("db.dbinfo.name");
        return DATABASE_SQL_TMP.replace("#dataBaseInfo", property);
    }

    public static String getTableConfigSql() {
        String property = CONFIG.getProperty("db.tableinfo.name");
        return TABLE_SQL_TMP.replace("#tableinfo", property);
    }

    public static String getTableConfigByTableIdSql() {
        String property = CONFIG.getProperty("db.tableinfo.name");
        return ONE_TABLE_SQL_TMP.replace("#tableinfo", property);
    }

    public static String getModuleConfigSql(String[] args) {
        String property = CONFIG.getProperty("db.moduleinfo.name");
        String condition = "";
        if (args!=null&&args.length > 0) {
            StringBuilder conditionBuilder = new StringBuilder();
            for (String s : args) {
                conditionBuilder.append(',').append(s);
            }
            condition = String.format("and module_id in (%s)", conditionBuilder.substring(1));
        }
        return MODULE_SQL_TMP.replace("#moduleinfo", property).replace("#condition", condition);
    }

    public static String getFieldConfigSql() {
        String property = CONFIG.getProperty("db.fieldinfo.name");
        return FIELD_SQL_TMP.replace("#fieldinfo", property);
    }

    public static String getFieldConfigByFieldIdSql() {
        String property = CONFIG.getProperty("db.fieldinfo.name");
        return ONE_FIELD_SQL_TMP.replace("#fieldinfo", property);
    }

    public static String getTimeUpdateSql() {
        String tableName = CONFIG.getProperty("db.tableinfo.name");
        return TIME_UPDATE_SQL.replace("#table_name", tableName);
    }
    public static String getTableInfoSql(){
        String tableName = CONFIG.getProperty("db.tableinfo.name");
        return TABLE_INFO_SQL_TMP.replace("#tableinfo",tableName);
    }
    public static String getInsertFieldInfo(){
        String property = CONFIG.getProperty("db.fieldinfo.name");
        return INSERT_FIELD_INFO_SQL.replace("#fieldinfo",property);
    }
}

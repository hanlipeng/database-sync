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
    private final static String INSERT_FIELD_INFO_SQL = "REPLACE INTO `#fieldInfo` (`src_field_name`, `dest_field_name`, `is_time`,  `is_primary`,  `table_id`) " +
            "VALUES (?, ?, ?, ?, ?);";
    private final static String UPDATE_TABLE_RUN_ABLE_SQL = "update #table_name set runable=0 where table_id=?";

    private final static Properties CONFIG = Utils.getConfig();
    private final static String TABLE_NAME=CONFIG.getProperty("db.tableinfo.name");
    private final static String DB_NAME=CONFIG.getProperty("db.dbinfo.name");
    private final static String MODULE_NAME=CONFIG.getProperty("db.moduleinfo.name");
    private final static String FIELD_NAME=CONFIG.getProperty("db.fieldinfo.name");

    public static String getDataBaseConfigSql() {
        return DATABASE_SQL_TMP.replace("#dataBaseInfo", DB_NAME);
    }

    public static String getTableConfigSql() {
        return TABLE_SQL_TMP.replace("#tableinfo", TABLE_NAME);
    }

    public static String getTableConfigByTableIdSql() {
        return ONE_TABLE_SQL_TMP.replace("#tableinfo", TABLE_NAME);
    }

    public static String getModuleConfigSql(String[] args) {
        String condition = "";
        if (args!=null&&args.length > 0) {
            StringBuilder conditionBuilder = new StringBuilder();
            for (String s : args) {
                conditionBuilder.append(',').append(s);
            }
            condition = String.format("and module_id in (%s)", conditionBuilder.substring(1));
        }
        return MODULE_SQL_TMP.replace("#moduleinfo", MODULE_NAME).replace("#condition", condition);
    }

    public static String getFieldConfigSql() {
        return FIELD_SQL_TMP.replace("#fieldinfo", FIELD_NAME);
    }

    public static String getFieldConfigByFieldIdSql() {
        return ONE_FIELD_SQL_TMP.replace("#fieldinfo", FIELD_NAME);
    }

    public static String getTimeUpdateSql() {
        return TIME_UPDATE_SQL.replace("#table_name", TABLE_NAME);
    }
    public static String getTableInfoSql(){
        return TABLE_INFO_SQL_TMP.replace("#tableinfo",TABLE_NAME);
    }
    public static String getInsertFieldInfo(){
        return INSERT_FIELD_INFO_SQL.replace("#fieldInfo", FIELD_NAME);
    }
    public static String getUpdateTableRunAbleSql(){
        return UPDATE_TABLE_RUN_ABLE_SQL.replace("#table_name",TABLE_NAME);
    }
}

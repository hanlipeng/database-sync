package com.tpg.sync.sqlbuilder;

import com.mysql.jdbc.StringUtils;
import com.tpg.sync.exception.NoTimeException;
import com.tpg.sync.sync.SyncTable;
import com.tpg.sync.util.Utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


/**
 * @author reph
 */
public interface SqlBuilder {
    Properties SQL_RULE = Utils.getSqlRule();
    String DATA_PREFIX = "date.";
    String SQL_PREFIX = "select.";
    String JOIN_SQL_PREFIX = "selectjoin.";
    String FIELD_PREFIX = "field.";
    String JOIN_FIELD_PREFIX = "joinfield.";
    String JOIN_TABLE_PREFIX="jointable.";
    String JOIN_DATE_PREFIX = "joindate.";
    String UPDATE_TABLE_PREFIX = "updatetable.";
    String UPDATE_FIELD_PREFIX = "updatefield.";
    String UPDATE_CONDITION_PREFIX = "updatecondition.";
    String UPDATE_FIELD_TMP_PREFIX = "update.field.";
    String UPDATE_SQL_PREFIX = "update.";
    String PRIMARY_KEY_UPPER_PREFIX = "primaryKeyUpper.";
    String TABLE_NAME_REG = "#{table}";
    String FIELD_NAME_REG = "#{field_name}";
    String FIELD_TIME_REG = "#{fieldtime}";
    String FIELD_LIST_REG = "#{fields}";
    String TIME_CONDITION_REG="#{time}";
    String CONDITION_REG="#{condition}";
    String PRIMARY_REG = "#{primary}";
    String TIME_TYPE_REG = "#{typetime}";
    String VALUE_REG = "#{value}";
    String DEST_VALUE_LIST_REG = "#{values}";
    String JOIN_TABLE_NAME_REG = "#{jointablename}";
    String JOIN_FIELD_NAME_REG = "#{joinfieldname}";
    String JOIN_TABLE_SQL_REG = "#{jointable}";
    String MAIN_FIELD_NAME_REG = "#{mainfieldname}";
    String STATE_FIELD_REG = "`state`,";
    String ONE_VALUE_REG = "1,";
    String TEMP_VALUE_REG = "?,";
    /**
     * 用于构建sql语句的接口,根据表信息自定义构造方法,实现接口,并在newsqlrule.properties中进行配置
     *
     * @param table 表信息
     * @return sql语句
     * @throws NoTimeException        遍历fieldList时如果没找到时间字段会抛出次异常(只在需要增量同步的表时使用)
     * @throws SQLException           sqlException
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws Exception exception
     */
    String buildSql(SyncTable table) throws Exception;

    /** 获取同步的时间值  (优先级:config中设定的时间>syc_table表中的sync_time>当天时间)
     * @param table 使用table.getSyncTime方法获取上次同步时间
     * @return 时间日期
     */
    default String getDatetime(SyncTable table) {
        String datetime = Utils.getDatetime();
        if (StringUtils.isNullOrEmpty(datetime)) {
            if(StringUtils.isNullOrEmpty(table.getSyncTime())) {
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, -1);
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
            }else {
                return table.getSyncTime();
            }
        } else {
            return datetime;
        }
    }

    /**获取天日期格式(yyyy-MM-dd)
     * @param table 调用 {@link SqlBuilder}.getDateTime(Table table)时使用参数
     * @return 返回时间
     * @throws  ParseException 时间转换异常
     */
    default String getDatetimeOfDay(SyncTable table) throws ParseException {
        String datetime = getDatetime(table);
        Date parse;
        try {
            parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime);
        } catch (ParseException e) {
            return datetime;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(parse);
    }

    /**
     * 通过数据库类型和前缀回去sql语句魔板
     * @param dataBaseType 数据库类型
     * @param prefix 前缀
     * @return sql语句魔板
     */
    default String getSqlModule(String dataBaseType, String prefix){
        return SQL_RULE.getProperty(prefix + dataBaseType);
    }
}


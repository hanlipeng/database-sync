package test.sqlbuilder;

import com.mysql.jdbc.StringUtils;
import com.tpg.sync.sync.SyncTable;
import com.tpg.sync.util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * @author reph
 * @date 2018/3/30
 */
public class BaseSqlBuilder {
    Properties SQL_RULE = Utils.getSqlRule();
    protected final static String DATA_PREFIX = "date.";
    protected final static String SQL_PREFIX = "select.";
    protected final static String JOIN_SQL_PREFIX = "selectjoin.";
    protected final static String FIELD_PREFIX = "field.";
    protected final static String JOIN_FIELD_PREFIX = "joinfield.";
    protected final static String JOIN_TABLE_PREFIX = "jointable.";
    protected final static String JOIN_DATE_PREFIX = "joindate.";
    protected final static String UPDATE_FIELD_TMP_PREFIX = "update.field.";
    protected final static String UPDATE_SQL_PREFIX = "update.";
    protected final static String PRIMARY_KEY_UPPER_PREFIX = "primaryKeyUpper.";
    protected final static String TABLE_NAME_REG = "#{table}";
    protected final static String FIELD_NAME_REG = "#{field_name}";
    protected final static String FIELD_TIME_REG = "#{fieldtime}";
    protected final static String FIELD_LIST_REG = "#{fields}";
    protected final static String TIME_CONDITION_REG = "#{time}";
    protected final static String CONDITION_REG = "#{condition}";
    protected final static String PRIMARY_REG = "#{primary}";
    protected final static String TIME_TYPE_REG = "#{typetime}";
    protected final static String VALUE_REG = "#{value}";
    protected final static String DEST_VALUE_LIST_REG = "#{values}";
    protected final static String JOIN_TABLE_NAME_REG = "#{jointablename}";
    protected final static String JOIN_FIELD_NAME_REG = "#{joinfieldname}";
    protected final static String JOIN_TABLE_SQL_REG = "#{jointable}";
    protected final static String MAIN_FIELD_NAME_REG = "#{mainfieldname}";
    protected final static String TEMP_VALUE_REG = "?,";

    /**
     * 获取同步的时间值  (优先级:config中设定的时间>syc_table表中的sync_time>当天时间)
     *
     * @param table 使用table.getSyncTime方法获取上次同步时间
     * @return 时间日期
     */
    String getDatetime(SyncTable table) {
        String datetime = Utils.getDatetime();
        if (StringUtils.isNullOrEmpty(datetime)) {
            if (StringUtils.isNullOrEmpty(table.getSyncTime())) {
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, -1);
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
            } else {
                return table.getSyncTime();
            }
        } else {
            return datetime;
        }
    }

    /**
     * 获取天日期格式(yyyy-MM-dd)
     *
     * @param table 调用 getDateTime(Table table)时使用参数
     * @return 返回时间
     * @throws ParseException 时间转换异常
     */
    protected String getDatetimeOfDay(SyncTable table) throws ParseException {
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
     *
     * @param dataBaseType 数据库类型
     * @param prefix       前缀
     * @return sql语句魔板
     */
    protected String getSqlModule(String dataBaseType, String prefix) {
        return SQL_RULE.getProperty(prefix + dataBaseType);
    }
}

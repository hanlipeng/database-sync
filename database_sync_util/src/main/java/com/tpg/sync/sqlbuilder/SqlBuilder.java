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

    /**
     * 用于构建sql语句的接口,根据表信息自定义构造方法,实现接口,并在newsqlrule.properties中进行配置
     *
     * @param table 表信息
     * @return sql语句
     * @throws Exception exception
     */
    String buildSql(SyncTable table) throws Exception;


}


package test.constant;

/**
 * @author reph
 * @date 2017/11/21
 */
public class FieldSpecialKeyConstant {
    /**
     * 判断值true
     */
    public static final String TRUE = "1";
    /**
     * 判断值false
     */
    public static final String FALSE = "0";
    /**
     * 常用的种别key写做{"T":*}用于区别字段
     */
    public static final String TYPE_KEY="T";
    /**
     * 用于FormOperation同步是拆分字段
     */
    public static final String POSITION_KEY = "P";
    /**
     * 生成语句时忽略的表示
     */
    public static final String FIELD_IGNORE = "ignore";
    /**
     * srcsql生成语句时忽略的表示
     */
    public static final String SRC_FIELD_IGNORE_KEY = "SRC";
    /**
     * 生成语句时忽略的表示
     */
    public static final String DEST_FIELD_IGNORE = "D";

    /**
     * 标明是join表中的字段,在拼接字段时用b.#field 拼接, {"JOIN_FIELD":"joinField"}
     */
    public static final String JOIN_FIELD_FLAG = "joinField";

    /**
     * 标明是join表中的key,在拼接字段时用b.#field 拼接, {"JOIN_FIELD":"joinField"}
     */
    public static final String JOIN_FIELD_KEY = "JOIN_FIELD";

    /**
     * 多对多拆分
     * {"M2M":reg}
     */
    public static final String MORE_TO_MORE_KEY = "M2M";

    /**
     * 会在目标表运行"delete from #{dest_table} where #{field_name} = #{destValue}"
     * {"T":"delete"}
     */
    public static final String DELETE_FLAG="delete";
}
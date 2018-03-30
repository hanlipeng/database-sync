package com.tpg.sync.sync;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author reph
 * @date 2018/3/23
 */
public class ValueBuilder {

    /**
     * 数据查询结果集
     */
    private ResultSet res;

    private boolean next = true;

    /**
     * syncTable
     */
    private SyncTable syncTable;
    private static final int MAX_NUMBER_OF_COLUMN = SyncThreadManger.getMaxNumberOfColumn();
    private int total=0;

    public ValueBuilder(SyncTable syncTable) {
        this.syncTable = syncTable;
    }

    public synchronized List<List<SyncField>> getValue() throws SQLException, ClassNotFoundException {
        if(res==null) {
            String srcSql = syncTable.getSrcSql();
            Connection srcConn = syncTable.getSrcConn();
            res = srcConn.createStatement().executeQuery(srcSql);
        }
        List<SyncField> fieldList = syncTable.getFieldList();
        LinkedList<List<SyncField>> valuesList = new LinkedList<>();
        int count = 0;
        while ((next = res.next())) {
            total++;
            LinkedList<SyncField> fieldValues = new LinkedList<>();
            for (SyncField field : fieldList) {
                Object object = res.getObject(field.getSrcFieldName());
                SyncField clone = field.clone();
                clone.setSrcValue(object);
                fieldValues.add(clone);
            }
            valuesList.add(fieldValues);
            if(++count==MAX_NUMBER_OF_COLUMN){
                break;
            }
        }
        return valuesList;
    }

    public boolean hasNext() {
        return next;
    }

    public int getTotal() {
        return total;
    }
}

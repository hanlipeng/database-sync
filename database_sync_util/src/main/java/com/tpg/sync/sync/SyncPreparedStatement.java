package com.tpg.sync.sync;

import com.tpg.sync.constant.FieldSpecialKeyConstant;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author reph
 * @date 2018/3/23
 */
public class SyncPreparedStatement {
    private PreparedStatement pre;
    private SortedMap<Integer,SyncField> valueMap;
    public SyncPreparedStatement(PreparedStatement pre){
        this.pre=pre;
        valueMap=new TreeMap<>();
    }

    /**
     * 设置结果;会储存起field;之后调用addBatch时会遍历存储结果插入PreparedStatement;如果传入值为空会抛出异常
     * @param field *
     */
    public void setObject(SyncField field){
        if(field==null){
            throw new RuntimeException("field is null when pre.setObject");
        }
        setObj(field,null);
    }

    /**
     * 功能同上,但是传入的是空,会跳过
     * @param field *
     */
    public void setObjectIgnoreNull(SyncField field){
        if(field==null){
            return;
        }
        setObj(field,null);
    }

    /**
     * 功能同上.但是可以指定插入的位置,用于在group同步时使用
     * @param position 位置
     * @param field *
     */
    public void setObject(Integer position,SyncField field){
        if(field==null){
            throw new RuntimeException("field is null when pre.setObject");
        }
        setObj(field,position);
    }

    /**
     * 功能同上,忽略空字段
     * @param position 位置
     * @param field *
     */
    public void setObjectIgnoreNull(Integer position,SyncField field){
        if(field==null){
            return;
        }
        setObj(field,position);
    }
    private void setObj(SyncField field,Integer position){
        valueMap.put(position==null?field.getPosition():position,field);
    }
    public void clearParam() throws SQLException {
        valueMap.clear();
        pre.clearParameters();
    }
    public void addBatch() throws SQLException {
        int position=1;
        Set<Integer> sortKey = valueMap.keySet();
        for (Integer key:sortKey){
            String specialPosition = (String) valueMap.get(key).getSpecialKey(FieldSpecialKeyConstant.POSITION_KEY);
            if(specialPosition!=null){
                pre.setObject(Integer.parseInt(specialPosition),valueMap.get(key).getDestValue());
            }else {
                pre.setObject(position++, valueMap.get(key).getDestValue());
            }
        }
        pre.addBatch();
    }

}

package test.syncmethod.impl;

import com.tpg.sync.constant.NormalConstant;
import com.tpg.sync.sync.SyncField;
import com.tpg.sync.sync.SyncPreparedStatement;
import com.tpg.sync.sync.SyncTable;
import com.tpg.sync.syncbuilder.SyncMethod;
import com.tpg.sync.util.TableUtil;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static test.constant.FieldSpecialKeyConstant.*;


/**
 * Created by reph on 2017/6/22.
 *
 * @author fengshang
 */
public class M2MSyncMethod implements SyncMethod {

    @Override
    public void dealData(SyncPreparedStatement pre, List<List<SyncField>> value, SyncTable syncTable) throws SQLException {
        for (List<SyncField> syncFieldList : value) {
            List<List<SyncField>> m2mField = new LinkedList<>();
            String m2mValue;
            int maxLen = 0;
            //遍历一行数据的所有值
            for (SyncField syncField : syncFieldList) {
                //如果是需要拆分的字段.getSpecialKey会不为null;
                if (syncField.getSpecialKey(MORE_TO_MORE_KEY) != null) {
                    m2mValue = syncField.getSrcValue().toString();
                    //如果拆分值为空.则在这个位置加入一个destValue为空的Field;来保证最后设置Field时splitField的0位有一个可以调用getEmptyValueField的对象
                    if (m2mValue == null || NormalConstant.EMPTY_STRING.equals(m2mValue)) {
                        SyncField emptyValueField = syncField.getEmptyValueField();
                        List<SyncField> emptySplitField = new LinkedList<>();
                        emptySplitField.add(emptyValueField);
                        m2mField.add(emptySplitField);
                        continue;
                    }
                    //拆分字段,得到拆分结果;
                    String[] split = m2mValue.split(syncField.getSpecialKey(MORE_TO_MORE_KEY).toString());
                    LinkedList<SyncField> splitField = new LinkedList<>();
                    //将拆分的结果放入clone出的field,供插入数据;
                    for (String s : split) {
                        SyncField clone = syncField.clone();
                        clone.setDestValue(s);
                        splitField.add(clone);
                    }
                    //将拆分的Field集加入拆分集
                    m2mField.add(splitField);
                    //更改最大Field集长度;
                    if (maxLen < split.length) {
                        maxLen = split.length;
                    }
                } else {
                    //如果没有拆分键就直接设置;
                    Object valueTemp = syncField.getSrcValue();
                    syncField.setDestValue(valueTemp);
                    if (NormalConstant.EMPTY_STRING.equals(valueTemp)) {
                        syncField.setDestValue(null);
                    }
                    if (DELETE_FLAG.equals(syncField.getSpecialKey(TYPE_KEY))) {
                        TableUtil.deleteInfoByPrimaryKeyInGroupTable(syncTable, syncField);
                    }
                    pre.setObject(syncField);
                }
            }
            //到此未知,不需要拆分的字段已经设置成功,开始遍历拆分的字段;
            for (int i = 0; i < maxLen; i++) {
                //maxLen表示最终会插入几行
                //maxLen表示最终会插入几行
                for (List<SyncField> splitField : m2mField) {
                    //循环到splitField的长度小于maxLen的时候,此时应设置一个destValue值为null的Field;
                    pre.setObject(splitField.size() - 1 < i ? splitField.get(0).getEmptyValueField() : splitField.get(i));
                }
                pre.addBatch();
            }
            pre.clearParam();
        }
    }

}

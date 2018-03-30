package com.tpg.sync.sync;

import com.tpg.sync.tableinfo.InfoGetter;
import com.tpg.sync.util.Utils;

import java.io.File;
import java.io.IOException;

/**
 * @author reph
 * @date 2018/3/27
 */
public class SyncMainMethod {
    public SyncMainMethod(File resourceConfigFile, File sqlRuleFile, File tableTypeFile, File threadConfigFile) throws IOException {
        Utils.loadConfig(resourceConfigFile, sqlRuleFile, tableTypeFile, threadConfigFile);
    }

    public void startSync(String[] args) {
        try {
            SyncThreadManger syncThreadManger = new SyncThreadManger(new SyncModuleManager(args));
            syncThreadManger.startThread();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void getTableInfo() throws Exception {
        new InfoGetter().getTableInfo();
    }
}

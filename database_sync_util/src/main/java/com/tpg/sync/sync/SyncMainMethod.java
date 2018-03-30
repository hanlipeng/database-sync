package com.tpg.sync.sync;

import com.tpg.sync.util.Utils;

import java.io.File;

/**
 * @author reph
 * @date 2018/3/27
 */
public class SyncMainMethod {
    public static void startSync(String [] args, File resourceConfigFile,File sqlRuleFile,File tableTypeFile,File threadConfigFile) {
        try {
            Utils.loadConfig(resourceConfigFile,sqlRuleFile,tableTypeFile,threadConfigFile);
            SyncThreadManger syncThreadManger = new SyncThreadManger(new SyncModuleManager(args));
            syncThreadManger.startThread();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

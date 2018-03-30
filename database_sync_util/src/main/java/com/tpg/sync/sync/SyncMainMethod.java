package com.tpg.sync.sync;

/**
 * @author reph
 * @date 2018/3/27
 */
public class SyncMainMethod {
    public static void startSync(String [] args) throws Exception {
        SyncThreadManger syncThreadManger = new SyncThreadManger(new SyncModuleManager(args));
        syncThreadManger.startThread();

    }
}

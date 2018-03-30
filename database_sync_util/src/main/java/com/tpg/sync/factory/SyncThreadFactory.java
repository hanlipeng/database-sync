package com.tpg.sync.factory;

import java.util.concurrent.ThreadFactory;

/**
 * @author reph
 * @date 2018/3/19
 */
public class SyncThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("syncThread"+thread.getName());
        return thread;
    }
}

package com.tpg.sync.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author reph
 * @date 2018/3/22
 */
public abstract class SyncSonThread implements Runnable {
    ThreadManage threadManage;

    @Override
    public void run() {
        try {
            mainRun();
        } catch (Exception e) {
            threadManage.changeErrorState();
            threadManage.setError(e);

        }
        try {
            threadManage.removeThreadMark();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 作为子线程的时候进行的操作
     *
     * @throws Exception 方便更改写总线程的errorState
     */
    abstract void mainRun() throws Exception;

    /**
     * 子线程是否需要运行,不需要判断直接返回true即可,
     * 一般做module和table的runnable判断用.不需要直接返回true
     * @return *
     */
    abstract boolean couldRun();

    void setThreadManage(ThreadManage threadManage) {
        this.threadManage = threadManage;
    }


}

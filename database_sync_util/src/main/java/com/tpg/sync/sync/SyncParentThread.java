package com.tpg.sync.sync;


import com.tpg.sync.util.Utils;

/**
 * @author reph
 * @date 2018/3/22
 */
public interface SyncParentThread {
    boolean DEBUG= "1".equals(Utils.getConfig().getProperty("DEBUG"));
    /**
     * 返回这个类作为父线程可以开启子线程线程的最大数量
     * 实现这个类只需将最大的数字返回即可
     *
     * @return 开启线程的最大数量
     */
    int getMaxNumberOfThreads();

    /**
     * 在开启子线程时的时候需要初始化的操作
     */
    void beforeStartThread() throws Exception;

    /**
     * 在子线程结束之后进行的操作
     * 例如conn提交和回滚.
     *
     * @param errorState 如果其中一个子线程报错.会将errorState改成true;否则为false
     */
    void afterEndThread(boolean errorState) throws Exception;

    /**
     * 获取下一个子线程,如果没有则返回null;
     * @return *
     */
    SyncSonThread getThread();

    /**
     * 父线程开启子线程时,判断是否还有可运行的线程
     *
     * @param errorState 当前线程运行中是否有异常
     * @return errorState
     */
    boolean hasNext(boolean errorState);

    /**
     * 父线程开启子线程时,判断是否还需要等待.
     * 通常用在order的等待上;
     * 如果不需要判断直接返回false;
     * @param running 传入这个参数是判断之前的运行是否结束的状态.
     * @return *
     */
    boolean needWait(boolean running);

    /** 获取子线程开始时需要打印的线程.一般做计时用;
     * @return
     */
    String getStartLog();

    /** 获取子线程结束时需要打印的线程
     * @return
     */
    String getEndLog();
}
